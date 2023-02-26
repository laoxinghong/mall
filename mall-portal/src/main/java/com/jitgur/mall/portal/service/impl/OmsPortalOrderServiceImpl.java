package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.common.service.RedisService;
import com.jitgur.mall.mbg.mapper.OmsOrderItemMapper;
import com.jitgur.mall.mbg.mapper.OmsOrderMapper;
import com.jitgur.mall.mbg.mapper.OmsOrderSettingMapper;
import com.jitgur.mall.mbg.mapper.UmsMemberIntegrationConsumeSettingMapper;
import com.jitgur.mall.mbg.model.*;
import com.jitgur.mall.portal.component.OrderTtlCancelSender;
import com.jitgur.mall.portal.dao.OmsPortalOrderDao;
import com.jitgur.mall.portal.dao.OmsPortalOrderItemDao;
import com.jitgur.mall.portal.dao.SmsCouponHistoryDao;
import com.jitgur.mall.portal.domain.*;
import com.jitgur.mall.portal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单管理service实现类
 * Created by jitgur on 20230217
 */
@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {

    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberReceiveAddressService receiveAddressService;
    @Autowired
    private UmsMemberCouponService couponService;
    @Autowired
    private UmsMemberIntegrationConsumeSettingMapper integrationConsumeSettingMapper;
    @Autowired
    private SmsCouponHistoryDao couponHistoryDao;
    @Autowired
    private OmsOrderSettingMapper orderSettingMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OmsPortalOrderDao orderDao;
    @Autowired
    private OmsPortalOrderItemDao orderItemDao;
    @Autowired
    private OmsOrderItemMapper orderItemMapper;
    @Autowired
    private OrderTtlCancelSender orderTtlCancelSender;
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.orderId}")
    private String REDIS_KEY_ORDER_ID;


    @Override
    public OmsOrderConfirmation generateOrderConfirmation(List<Long> cartItemIdList) {
        OmsOrderConfirmation confirmation = new OmsOrderConfirmation();
        //获取当前用户
        UmsMember currentMember = memberService.getCurrentMember();
        // 获取购物车商品促销优惠
        List<OmsCartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(currentMember.getId(), cartItemIdList);
        confirmation.setCartPromotionItemList(cartPromotionItemList);
        // 获取用户收货地址列表
        List<UmsMemberReceiveAddress> addressList = receiveAddressService.list(currentMember.getId());
        confirmation.setAddressesList(addressList);
        // 获取可用优惠券列表
        List<SmsCouponHistoryDetail> couponHistoryDetailList = couponService.listCartCoupon(cartPromotionItemList, 1);
        confirmation.setCouponHistoryDetailList(couponHistoryDetailList);
        // 获取会员积分数
        confirmation.setIntegration(currentMember.getIntegration());
        // 获取积分使用规则
        UmsMemberIntegrationConsumeSetting integrationConsumeSetting = integrationConsumeSettingMapper.selectByPrimaryKey(1L);
        confirmation.setIntegrationConsumeSetting(integrationConsumeSetting);
        // 计算金额信息
        confirmation.setAmountCalculation(getAmountCalculation(cartPromotionItemList));

        return confirmation;
    }


    /**
     * 计算购物车商品金额信息
     */
    public OmsOrderConfirmation.AmountCalculation getAmountCalculation(List<OmsCartPromotionItem> cartPromotionItemList) {
        OmsOrderConfirmation.AmountCalculation amountCalculation = new OmsOrderConfirmation.AmountCalculation();
        amountCalculation.setFreightAmount(new BigDecimal(0));
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal promotionAmount = new BigDecimal(0);
        for (OmsCartPromotionItem cartPromotionItem : cartPromotionItemList) {
            totalAmount = totalAmount.add(cartPromotionItem.getPrice());
            promotionAmount = promotionAmount.add(cartPromotionItem.getReducePrice());
        }
        amountCalculation.setTotalAmount(totalAmount);
        amountCalculation.setPromotionAmount(promotionAmount);
        amountCalculation.setPayableAmount(totalAmount
                .add(amountCalculation.getFreightAmount())
                .subtract(promotionAmount));
        return amountCalculation;
    }


    @Override
    public Map<String, Object> generateOrder(OmsOrderParam orderParam) {
        UmsMember currentMember = memberService.getCurrentMember();
        // 获取商品促销信息
        List<OmsCartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(currentMember.getId(), orderParam.getCartItemIdList());
        // 转换为订单商品
        List<OmsOrderItem> orderItemList = new ArrayList<>();
        for (OmsCartPromotionItem cartPromotionItem : cartPromotionItemList) {
            OmsOrderItem orderItem = new OmsOrderItem();
            orderItem.setProductId(cartPromotionItem.getProductId());
            orderItem.setProductName(cartPromotionItem.getProductName());
            orderItem.setProductAttr(cartPromotionItem.getProductAttr());
            orderItem.setProductBrand(cartPromotionItem.getProductBrand());
            orderItem.setProductCategoryId(cartPromotionItem.getProductCategoryId());
            orderItem.setProductPrice(cartPromotionItem.getPrice());
            orderItem.setProductQuantity(cartPromotionItem.getQuantity());
            orderItem.setProductSkuId(cartPromotionItem.getProductSkuId());
            orderItem.setProductSkuCode(cartPromotionItem.getProductSkuCode());
            orderItem.setProductSn(cartPromotionItem.getProductSn());
            orderItem.setPromotionName(cartPromotionItem.getPromotionMessage());
            orderItem.setPromotionAmount(cartPromotionItem.getReducePrice());
            orderItem.setGiftGrowth(cartPromotionItem.getGiftGrowth());
            orderItem.setGiftIntegration(cartPromotionItem.getGiftIntegration());
            orderItemList.add(orderItem);
        }

        // 判断商品是否都有库存
        if (!hasStock(cartPromotionItemList)) {
            Asserts.fail("对不起，当前商品库存不足");
        }

        // 计算优惠券抵扣金额
        if (orderParam.getCouponId() == null) {
            for (OmsOrderItem orderItem : orderItemList) {
                orderItem.setCouponAmount(new BigDecimal(0));
            }
        } else {
            SmsCouponHistoryDetail couponHistoryDetail = couponHistoryDao.getCouponHistoryDetailById(currentMember.getId(), orderParam.getCouponId());
            if (couponHistoryDetail == null) {
                Asserts.fail("当前优惠券不可用");
            }
            apportionCoupon(couponHistoryDetail, orderItemList);
        }

        // 计算积分抵扣金额
        if (orderParam.getUseIntegration() == null) {
            for (OmsOrderItem orderItem : orderItemList) {
                orderItem.setIntegrationAmount(new BigDecimal(0));
            }
        } else {
            BigDecimal totalAmount = calcProductAmount(orderItemList);
            BigDecimal integrationAmount = calcIntegrationAmount(currentMember.getIntegration(),
                    orderParam.getUseIntegration(), orderParam.getCouponId() != null, totalAmount);

            if (integrationAmount.subtract(new BigDecimal(0)).intValue() > 0) {
                for (OmsOrderItem orderItem : orderItemList) {
                    orderItem.setIntegrationAmount(orderItem.getProductPrice()
                            .divide(totalAmount, 2, RoundingMode.HALF_EVEN)
                            .multiply(integrationAmount));
                }
            }
        }

        // 计算实际支付金额
        for (OmsOrderItem orderItem : orderItemList) {
            orderItem.setRealAmount(orderItem.getProductPrice()
                    .subtract(orderItem.getPromotionAmount())
                    .subtract(orderItem.getCouponAmount())
                    .subtract(orderItem.getIntegrationAmount()));
        }

        // 生成订单
        OmsOrder order = new OmsOrder();
        order.setCreateTime(new Date());
        order.setMemberId(currentMember.getId());
        order.setMemberUsername(currentMember.getUsername());
        order.setTotalAmount(calcProductAmount(orderItemList));
        order.setFreightAmount(new BigDecimal(0));
        order.setDiscountAmount(new BigDecimal(0));
        if (orderParam.getCouponId() == null) {
            order.setCouponAmount(new BigDecimal(0));
        } else {
            order.setCouponId(orderParam.getCouponId());
            order.setCouponAmount(calcOrderCouponAmount(orderItemList));
        }
        if (orderParam.getUseIntegration() == null) {
            order.setIntegrationAmount(new BigDecimal(0));
        } else {
            order.setUseIntegration(orderParam.getUseIntegration());
            order.setIntegrationAmount(calcOrderIntegrationAmount(orderItemList));
        }
        order.setPromotionAmount(calcOrderPromotionAmount(orderItemList));
        order.setPayAmount(calcOrderPayAmount(order));
        order.setPayType(orderParam.getPayType());
        order.setSourceType(1);
        order.setStatus(0);
        order.setOrderType(0);
        // 自动确认天数
        OmsOrderSetting orderSetting = orderSettingMapper.selectByPrimaryKey(1L);
        if (orderSetting != null) {
            order.setAutoConfirmDay(orderSetting.getConfirmOvertime());
        }
        order.setIntegration(calcOrderGiftIntegration(orderItemList));
        order.setGrowth(calcOrderGiftGrowth(orderItemList));
        // 设置收货信息
        UmsMemberReceiveAddress address = receiveAddressService.getItem(orderParam.getAddressId());
        if (address != null) {
            order.setReceiverName(address.getName());
            order.setReceiverPhone(address.getPhoneNumber());
            order.setReceiverPostCode(address.getPostCode());
            order.setReceiverProvince(address.getProvince());
            order.setReceiverCity(address.getCity());
            order.setReceiverRegion(address.getRegion());
            order.setReceiverDetailAddress(address.getDetailAddress());
        }
        order.setDeleteStatus(0);
        order.setConfirmStatus(0);
        order.setOrderSn(generateOrderSn(order));
        // TODO:2022/12/28 bill_*,delivery_*

        orderMapper.insert(order);
        for (OmsOrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order.getId());
            orderItem.setOrderSn(order.getOrderSn());
        }
        orderItemDao.insertAll(orderItemList);

        // 更新优惠券使用状态
        if (order.getCouponId() != null) {
            SmsCouponUpdateParam param = new SmsCouponUpdateParam(currentMember.getId(),
                    order.getCouponId(), order.getId(), 1, new Date(), order.getOrderSn());
            couponService.updateCoupon(param);
        }

        // 更新会员积分
        if (order.getUseIntegration() != null) {
            memberService.updateMemberIntegration(currentMember.getId(),
                    currentMember.getIntegration() - order.getUseIntegration());
        }

        // 删除购物车商品
        List<Long> cartItemIdList = cartPromotionItemList.stream()
                .map(OmsCartPromotionItem::getId)
                .collect(Collectors.toList());
        cartItemService.delete(currentMember.getId(), cartItemIdList);

        // 发送延迟取消订单的消息
        orderTtlCancelSender.sendMessage(order.getId(),
                orderSettingMapper.selectByPrimaryKey(1L).getNormalOrderOvertime() * 60 * 1000);

        HashMap<String, Object> result = new HashMap<>();
        result.put("order", order);
        result.put("orderItemList", orderItemList);
        return result;
    }


    /**
     * 分摊优惠券
     */
    public void apportionCoupon(SmsCouponHistoryDetail couponHistoryDetail, List<OmsOrderItem> orderItemList) {
        // 优惠券金额
        BigDecimal couponAmount = couponHistoryDetail.getCoupon().getAmount();
        // 使用类型
        Integer useType = couponHistoryDetail.getCoupon().getUseType();
        if (useType == 0) {
            calcCouponAmount(couponAmount, orderItemList);
        } else if (useType == 1) {
            // 指定分类
            List<SmsCouponProductCategoryRelation> categoryRelationList = couponHistoryDetail.getCategoryRelationList();
            calcCateCouponAmount(categoryRelationList, couponAmount, orderItemList);
        } else if (useType == 2) {
            // 指定商品
            List<SmsCouponProductRelation> productRelationList = couponHistoryDetail.getProductRelationList();
            calcProductCouponAmount(productRelationList, couponAmount, orderItemList);
        }
    }


    /**
     * 计算指定分类商品优惠券分摊金额
     */
    public void calcCateCouponAmount(List<SmsCouponProductCategoryRelation> categoryRelationList,
                                     BigDecimal couponAmount, List<OmsOrderItem> orderItemList) {
        // 获取指定商品分类的订单商品
        Set<Long> cateIds = categoryRelationList.stream()
                .map(SmsCouponProductCategoryRelation::getProductCategoryId)
                .collect(Collectors.toSet());
        List<OmsOrderItem> enableList = orderItemList.stream()
                .filter(item -> cateIds.contains(item.getProductCategoryId()))
                .collect(Collectors.toList());
        List<OmsOrderItem> disableList = orderItemList.stream()
                .filter(item -> !cateIds.contains(item.getProductCategoryId()))
                .collect(Collectors.toList());
        for (OmsOrderItem orderItem : disableList) {
            orderItem.setCouponAmount(new BigDecimal(0));
        }
        calcCouponAmount(couponAmount, enableList);
    }


    /**
     * 计算指定商品优惠券分摊金额
     */
    public void calcProductCouponAmount(List<SmsCouponProductRelation> productRelationList,
                                        BigDecimal couponAmount, List<OmsOrderItem> orderItemList) {
        // 获取指定商品的订单商品
        Set<Long> productIds = productRelationList.stream()
                .map(SmsCouponProductRelation::getProductId)
                .collect(Collectors.toSet());
        List<OmsOrderItem> enableList = orderItemList.stream()
                .filter(item -> productIds.contains(item.getProductId()))
                .collect(Collectors.toList());
        List<OmsOrderItem> disableList = orderItemList.stream()
                .filter(item -> !productIds.contains(item.getProductId()))
                .collect(Collectors.toList());
        for (OmsOrderItem orderItem : disableList) {
            orderItem.setCouponAmount(new BigDecimal(0));
        }
        calcCouponAmount(couponAmount, enableList);
    }


    /**
     * 计算优惠券分摊金额
     */
    public void calcCouponAmount(BigDecimal couponAmount, List<OmsOrderItem> orderItemList) {
        BigDecimal totalAmount = calcProductAmount(orderItemList);
        for (OmsOrderItem orderItem : orderItemList) {
            BigDecimal amount = orderItem.getProductPrice()
                    .divide(totalAmount, 2, RoundingMode.HALF_EVEN).multiply(couponAmount);
            orderItem.setCouponAmount(amount);
        }
    }


    /**
     * 获取订单商品总金额
     */
    public BigDecimal calcProductAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            amount = amount.add(orderItem.getProductPrice().multiply(new BigDecimal(orderItem.getProductQuantity())));
        }
        return amount;
    }


    /**
     * 判断商品是否都有库存
     */
    public boolean hasStock(List<OmsCartPromotionItem> cartPromotionItemList) {
        for (OmsCartPromotionItem cartPromotionItem : cartPromotionItemList) {
            if (cartPromotionItem.getRealStock() == null || cartPromotionItem.getRealStock() < cartPromotionItem.getQuantity()) {
                return false;
            }
        }
        return true;
    }


    @Override
    public BigDecimal calcIntegrationAmount(Integer ownIntegration, Integer useIntegration, boolean useCoupon, BigDecimal totalAmount) {
        UmsMemberIntegrationConsumeSetting consumeSetting = integrationConsumeSettingMapper.selectByPrimaryKey(1L);
        if (ownIntegration.compareTo(useIntegration) < 0) {
            // 使用积分数超过用户持有积分数
            return new BigDecimal(-1);
        }

        if (useIntegration.compareTo(consumeSetting.getUseUnit()) < 0) {
            // 使用积分数小于最小使用单位
            return new BigDecimal(-2);
        }

        if (consumeSetting.getCouponStatus() == 0 && useCoupon) {
            // 不能与优惠券一起使用
            return new BigDecimal(-3);
        }

        BigDecimal integrationAmount = new BigDecimal(useIntegration).divide(
                new BigDecimal(consumeSetting.getDeductionPerAmount()), 2, RoundingMode.HALF_EVEN);
        BigDecimal maxAmount = totalAmount.multiply(
                new BigDecimal(consumeSetting.getMaxPercentPerOrder()).divide(
                        new BigDecimal(100), 2, RoundingMode.HALF_EVEN));
        if (maxAmount.subtract(integrationAmount).intValue() < 0) {
            // 超过最大使用百分比
            return new BigDecimal(-4);
        }

        return integrationAmount;
    }


    /**
     * 计算订单优惠券抵扣金额
     */
    public BigDecimal calcOrderCouponAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            amount = amount.add(orderItem.getCouponAmount());
        }
        return amount;
    }


    /**
     * 计算订单促销优惠金额
     */
    public BigDecimal calcOrderPromotionAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            amount = amount.add(orderItem.getPromotionAmount());
        }
        return amount;
    }


    /**
     * 计算订单积分抵扣金额
     */
    public BigDecimal calcOrderIntegrationAmount(List<OmsOrderItem> orderItemList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsOrderItem orderItem : orderItemList) {
            amount = amount.add(orderItem.getIntegrationAmount());
        }
        return amount;
    }


    /**
     * 计算订单应付金额
     */
    public BigDecimal calcOrderPayAmount(OmsOrder order) {
        return order.getTotalAmount()
                .add(order.getFreightAmount())
                .subtract(order.getCouponAmount())
                .subtract(order.getPromotionAmount())
                .subtract(order.getIntegrationAmount())
                .subtract(order.getDiscountAmount());
    }


    /**
     * 计算订单赠送积分数
     */
    public Integer calcOrderGiftIntegration(List<OmsOrderItem> orderItemList) {
        int count = 0;
        for (OmsOrderItem orderItem : orderItemList) {
            count += orderItem.getGiftIntegration();
        }
        return count;
    }


    /**
     * 计算订单赠送成长值
     */
    public Integer calcOrderGiftGrowth(List<OmsOrderItem> orderItemList) {
        int count = 0;
        for (OmsOrderItem orderItem : orderItemList) {
            count += orderItem.getGiftGrowth();
        }
        return count;
    }


    /**
     * 生成订单编号：8位日期+2位平台号码+2位支付方式+6位以上自增id
     */
    public String generateOrderSn(OmsOrder order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        sb.append(date);
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ORDER_ID + ":" + date;
        Long incrId = redisService.incr(key, 1);
        String incrStr = incrId.toString();
        if (incrStr.length() <= 6) {
            sb.append(String.format("%06d", incrId));
        } else {
            sb.append(incrStr);
        }

        return sb.toString();
    }


    @Override
    public void cancelOrder(Long orderId) {
        OmsOrderExample example = new OmsOrderExample();
        example.createCriteria()
                .andIdEqualTo(orderId)
                .andStatusEqualTo(0)
                .andDeleteStatusEqualTo(0);
        List<OmsOrder> orderList = orderMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(orderList)) {
            OmsOrder order = orderList.get(0);
            order.setStatus(4);
            // 更新订单状态
            orderMapper.updateByPrimaryKeySelective(order);
            // 获取订单商品
            OmsOrderItemExample orderItemExample = new OmsOrderItemExample();
            orderItemExample.createCriteria()
                    .andOrderIdEqualTo(orderId);
            List<OmsOrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
            // 释放商品库存
            orderDao.releaseProductLockStock(orderItemList);
            // 更新对应优惠券状态
            if (order.getCouponId() != null) {
                SmsCouponUpdateParam updateParam = new SmsCouponUpdateParam(
                        order.getMemberId(), order.getCouponId(), null, 0, null, null);
                couponService.updateCoupon(updateParam);
            }
            // 更新会员积分
            if (order.getUseIntegration() != null) {
                UmsMember member = memberService.getMemberById(order.getMemberId());
                memberService.updateMemberIntegration(member.getId(), member.getIntegration() + order.getUseIntegration());
            }
        }
    }


    @Override
    public int cancelTimeOutOrder() {
        List<OmsOrderDetail> orderDetailList = orderDao.getTimeOutOrderDetailList(
                orderSettingMapper.selectByPrimaryKey(1L).getNormalOrderOvertime());
        if (CollUtil.isNotEmpty(orderDetailList)) {
            List<Long> orderIdList = orderDetailList.stream().map(OmsOrderDetail::getId).collect(Collectors.toList());
            orderDao.updateOrderStatus(orderIdList, 4);
            for (OmsOrderDetail orderDetail : orderDetailList) {
                // 释放商品库存
                orderDao.releaseProductLockStock(orderDetail.getOrderItemList());
                // 更新优惠券使用状态
                if (orderDetail.getCouponId() != null) {
                    SmsCouponUpdateParam updateParam = new SmsCouponUpdateParam(orderDetail.getMemberId(),
                            orderDetail.getCouponId(), null, 0, null, null);
                    couponService.updateCoupon(updateParam);
                }
                // 更新会员积分
                if (orderDetail.getUseIntegration() != null) {
                    UmsMember member = memberService.getMemberById(orderDetail.getMemberId());
                    memberService.updateMemberIntegration(member.getId(), member.getIntegration() + orderDetail.getUseIntegration());
                }
            }
        }
        return orderDetailList.size();
    }


    @Override
    public Integer paySuccess(Long orderId, Integer payType) {
        OmsOrder order = new OmsOrder();
        order.setId(orderId);
        order.setPaymentTime(new Date());
        order.setStatus(1);
        order.setPayType(payType);
        // 更新订单状态
        orderMapper.updateByPrimaryKeySelective(order);
        // 更新商品库存
        OmsOrderDetail orderDetail = orderDao.getOrderDetail(orderId);
        return orderDao.updateProductSkuStock(orderDetail.getOrderItemList());
    }


    @Override
    public void confirmOrderReceive(Long orderId) {
        UmsMember currentMember = memberService.getCurrentMember();
        OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
        if (!currentMember.getId().equals(order.getMemberId())) {
            Asserts.fail("不能为他人确认收货");
        }
        if (order.getStatus() != 2) {
            Asserts.fail("该订单还未发货");
        }
        order.setStatus(3);
        order.setConfirmStatus(1);
        order.setReceiverTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }


    @Override
    public List<OmsOrderDetail> listPage(Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMember currentMember = memberService.getCurrentMember();
        return orderDao.listPage(currentMember.getId(), status);
    }


    @Override
    public OmsOrderDetail getOrderDetail(Long orderId) {
        return orderDao.getOrderDetail(orderId);
    }


    @Override
    public void delete(Long orderId) {
        UmsMember currentMember = memberService.getCurrentMember();
        OmsOrder order = orderMapper.selectByPrimaryKey(orderId);
        if (!currentMember.getId().equals(order.getMemberId())) {
            Asserts.fail("不能删除他人的订单");
        }
        if (order.getStatus() == 3 || order.getStatus() == 4) {
            order.setDeleteStatus(1);
            orderMapper.updateByPrimaryKeySelective(order);
        } else {
            Asserts.fail("对不起，当前订单还未完成");
        }
    }

}
