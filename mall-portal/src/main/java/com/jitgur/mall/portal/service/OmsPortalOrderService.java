package com.jitgur.mall.portal.service;

import com.jitgur.mall.portal.domain.OmsOrderConfirmation;
import com.jitgur.mall.portal.domain.OmsOrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单管理Service
 * Created by jitgur on 20230217
 */
public interface OmsPortalOrderService {

    /**
     * 根据购物车信息生成确认单
     */
    OmsOrderConfirmation generateOrderConfirmation(List<Long> cartItemIdList);


    /**
     * 根据提交信息生成订单
     */
    @Transactional
    Map<String, Object> generateOrder(OmsOrderParam orderParam);


    /**
     * 计算会员积分抵扣金额
     *
     * @param ownIntegration 会员持有积分数
     * @param useIntegration 订单使用积分数
     * @param useCoupon      是否使用优惠券
     * @param totalAmount    商品总金额
     */
    BigDecimal calcIntegrationAmount(Integer ownIntegration, Integer useIntegration, boolean useCoupon, BigDecimal totalAmount);


    /**
     * 取消订单
     */
    @Transactional
    void cancelOrder(Long orderId);


    /**
     * 定时任务取消超时订单
     */
    @Transactional
    int cancelTimeOutOrder();


    /**
     * 支付成功后回调
     */
    @Transactional
    Integer paySuccess(Long orderId,Integer payType);


    /**
     * 确认收货
     */
    void confirmOrderReceive(Long orderId);

}
