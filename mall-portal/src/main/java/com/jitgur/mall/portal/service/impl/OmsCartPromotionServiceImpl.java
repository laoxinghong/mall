package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jitgur.mall.common.exception.Asserts;
import com.jitgur.mall.mbg.model.OmsCartItem;
import com.jitgur.mall.mbg.model.PmsProductDiscountLadder;
import com.jitgur.mall.mbg.model.PmsProductFullReduction;
import com.jitgur.mall.mbg.model.PmsSkuStock;
import com.jitgur.mall.portal.dao.OmsCartPromotionDao;
import com.jitgur.mall.portal.domain.OmsCartPromotionItem;
import com.jitgur.mall.portal.domain.OmsPromotionProduct;
import com.jitgur.mall.portal.service.OmsCartPromotionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * 购物车促销信息管理service实现类
 * Created by jitgur on 20230216
 */
@Service
public class OmsCartPromotionServiceImpl implements OmsCartPromotionService {

    @Autowired
    private OmsCartPromotionDao cartPromotionDao;


    @Override
    public List<OmsCartPromotionItem> calcPromotion(List<OmsCartItem> cartItemList) {
        // 根据商品id对购物车商品进行分组
        Map<Long, List<OmsCartItem>> cartItemMap = groupCartItemListBySpu(cartItemList);
        // 获取购物车商品相关促销优惠信息
        List<OmsPromotionProduct> promotionProductList = getProductPromotion(new ArrayList<>(cartItemMap.keySet()));
        List<OmsCartPromotionItem> cartPromotionItemList = new ArrayList<>();
        for (Map.Entry<Long, List<OmsCartItem>> entry : cartItemMap.entrySet()) {
            Long productId = entry.getKey();
            List<OmsCartItem> cartItemList1 = entry.getValue();
            // 根据商品id获取相关商品促销优惠信息
            OmsPromotionProduct promotionProduct = getPromotionProductById(productId, promotionProductList);
            Integer promotionType = promotionProduct.getPromotionType();
            // 单品促销
            if (promotionType == 1) {
                for (OmsCartItem cartItem : cartItemList1) {
                    OmsCartPromotionItem cartPromotionItem = new OmsCartPromotionItem();
                    BeanUtils.copyProperties(cartItem, cartPromotionItem);
                    cartPromotionItem.setPromotionMessage("单品促销");
                    // 获取sku库存信息
                    PmsSkuStock skuStock = getSkuStockById(cartItem.getProductSkuId(), promotionProduct.getSkuStockList());
                    if (skuStock == null) {
                        Asserts.fail("当前sku无库存");
                    }
                    cartPromotionItem.setPrice(skuStock.getPrice());
                    cartPromotionItem.setReducePrice(skuStock.getPrice().subtract(skuStock.getPromotionPrice()));
                    cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
                    cartPromotionItem.setGiftGrowth(promotionProduct.getGiftGrowth());
                    cartPromotionItem.setGiftIntegration(promotionProduct.getGiftPoint());
                    cartPromotionItemList.add(cartPromotionItem);
                }
            } else if (promotionType == 2) {// 折扣
                // 计算商品总数量
                int count = calcProductAmount(cartItemList1);
                // 计算商品折扣
                PmsProductDiscountLadder ladder = getDiscountLadder(count, promotionProduct.getLadderList());
                if (ladder != null) {
                    for (OmsCartItem cartItem : cartItemList1) {
                        OmsCartPromotionItem cartPromotionItem = new OmsCartPromotionItem();
                        BeanUtils.copyProperties(cartItem, cartPromotionItem);
                        cartPromotionItem.setPromotionMessage(getLadderMessage(ladder));
                        // 获取sku库存信息
                        PmsSkuStock skuStock = getSkuStockById(cartItem.getProductSkuId(), promotionProduct.getSkuStockList());
                        if (skuStock == null) {
                            Asserts.fail("当前sku无库存");
                        }
                        cartPromotionItem.setPrice(skuStock.getPrice());
                        cartPromotionItem.setReducePrice(skuStock.getPrice().subtract(skuStock.getPrice().multiply(ladder.getDiscount())));
                        cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
                        cartPromotionItem.setGiftIntegration(promotionProduct.getGiftPoint());
                        cartPromotionItem.setGiftGrowth(promotionProduct.getGiftGrowth());
                        cartPromotionItemList.add(cartPromotionItem);
                    }
                } else {
                    handleNoPromotion(cartPromotionItemList, cartItemList1, promotionProduct);
                }
            } else if (promotionType == 3) {// 满减
                // 计算购物车商品总价格
                BigDecimal amount = calcCartItemAmount(cartItemList, promotionProductList);
                // 获取满减优惠
                PmsProductFullReduction fullReduction = getFullReduction(amount, promotionProduct.getFullReductionList());
                if (fullReduction != null) {
                    for (OmsCartItem cartItem : cartItemList1) {
                        OmsCartPromotionItem cartPromotionItem = new OmsCartPromotionItem();
                        BeanUtils.copyProperties(cartItem, cartPromotionItem);
                        cartPromotionItem.setPromotionMessage(getFullReductionMessage(fullReduction));
                        // 获取sku库存
                        PmsSkuStock skuStock = getSkuStockById(cartItem.getProductSkuId(), promotionProduct.getSkuStockList());
                        if (skuStock == null) {
                            Asserts.fail("当前商品无库存");
                        }
                        cartPromotionItem.setPrice(skuStock.getPrice());
                        cartPromotionItem.setReducePrice(skuStock.getPrice().multiply(fullReduction.getReducePrice()).divide(amount, RoundingMode.HALF_EVEN));
                        cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
                        cartPromotionItem.setGiftGrowth(promotionProduct.getGiftGrowth());
                        cartPromotionItem.setGiftIntegration(promotionProduct.getGiftPoint());
                        cartPromotionItemList.add(cartPromotionItem);
                    }
                } else {
                    handleNoPromotion(cartPromotionItemList, cartItemList1, promotionProduct);
                }
            } else {
                handleNoPromotion(cartPromotionItemList, cartItemList1, promotionProduct);
            }
        }
        return cartPromotionItemList;
    }


    /**
     * 根据spu单位对购物车商品进行分组
     */
    public Map<Long, List<OmsCartItem>> groupCartItemListBySpu(List<OmsCartItem> cartItemList) {
        Map<Long, List<OmsCartItem>> map = new TreeMap<>();

        for (OmsCartItem cartItem : cartItemList) {
            List<OmsCartItem> list = map.get(cartItem.getProductId());
            if (CollUtil.isEmpty(list)) {
                list = new ArrayList<>();
                list.add(cartItem);
                map.put(cartItem.getProductId(), list);
            } else {
                list.add(cartItem);
            }
        }
        return map;
    }


    /**
     * 获取购物车商品相关促销优惠信息
     */
    public List<OmsPromotionProduct> getProductPromotion(List<Long> productIdList) {
        return cartPromotionDao.getProductPromotion(productIdList);
    }


    /**
     * 根据商品id获取相关商品促销优惠信息
     */
    public OmsPromotionProduct getPromotionProductById(Long productId, List<OmsPromotionProduct> promotionProductList) {
        for (OmsPromotionProduct promotionProduct : promotionProductList) {
            if (promotionProduct.getId().equals(productId)) {
                return promotionProduct;
            }
        }
        return null;
    }


    /**
     * 根据sku id获取相关库存信息
     */
    public PmsSkuStock getSkuStockById(Long skuId, List<PmsSkuStock> skuStockList) {
        for (PmsSkuStock skuStock : skuStockList) {
            if (skuStock.getId().equals(skuId)) {
                return skuStock;
            }
        }
        return null;
    }


    /**
     * 计算商品总件数
     */
    public int calcProductAmount(List<OmsCartItem> cartItemList) {
        int count = 0;
        for (OmsCartItem cartItem : cartItemList) {
            count += cartItem.getQuantity();
        }
        return count;
    }


    /**
     * 计算获得的折扣
     */
    public PmsProductDiscountLadder getDiscountLadder(int count, List<PmsProductDiscountLadder> ladderList) {
        ladderList.sort(new Comparator<PmsProductDiscountLadder>() {
            @Override
            public int compare(PmsProductDiscountLadder o1, PmsProductDiscountLadder o2) {
                return o2.getCount() - o1.getCount();
            }
        });

        for (PmsProductDiscountLadder ladder : ladderList) {
            if (count >= ladder.getCount()) {
                return ladder;
            }
        }
        return null;
    }


    /**
     * 获取折扣信息
     */
    public String getLadderMessage(PmsProductDiscountLadder ladder) {
        StringBuilder sb = new StringBuilder();
        sb.append("满");
        sb.append(ladder.getCount());
        sb.append("件，打");
        sb.append(ladder.getDiscount());
        sb.append("折");
        return sb.toString();
    }


    /**
     * 处理无促销优惠的商品
     */
    public void handleNoPromotion(List<OmsCartPromotionItem> cartPromotionItemList,
                                  List<OmsCartItem> cartItemList, OmsPromotionProduct promotionProduct) {
        for (OmsCartItem cartItem : cartItemList) {
            OmsCartPromotionItem cartPromotionItem = new OmsCartPromotionItem();
            BeanUtils.copyProperties(cartItem, cartPromotionItem);
            cartPromotionItem.setPromotionMessage("无促销优惠");
            // 获取sku库存
            PmsSkuStock skuStock = getSkuStockById(cartItem.getProductSkuId(), promotionProduct.getSkuStockList());
            if (skuStock == null) {
                Asserts.fail("当前商品无库存");
            }
            cartPromotionItem.setPrice(skuStock.getPrice());
            cartPromotionItem.setReducePrice(new BigDecimal(0));
            cartPromotionItem.setRealStock(skuStock.getStock() - skuStock.getLockStock());
            cartPromotionItem.setGiftIntegration(promotionProduct.getGiftPoint());
            cartPromotionItem.setGiftGrowth(promotionProduct.getGiftGrowth());
            cartPromotionItemList.add(cartPromotionItem);
        }
    }


    /**
     * 计算购物车商品总金额
     */
    public BigDecimal calcCartItemAmount(List<OmsCartItem> cartItemList, List<OmsPromotionProduct> promotionProductList) {
        BigDecimal amount = new BigDecimal(0);
        for (OmsCartItem cartItem : cartItemList) {
            OmsPromotionProduct promotionProduct = getPromotionProductById(cartItem.getProductId(), promotionProductList);
            PmsSkuStock skuStock = getSkuStockById(cartItem.getProductSkuId(), promotionProduct.getSkuStockList());
            if (skuStock == null) {
                Asserts.fail("当前商品无库存");
            }
            amount = amount.add(skuStock.getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        }
        return amount;
    }


    /**
     * 计算满减优惠
     */
    public PmsProductFullReduction getFullReduction(BigDecimal amount, List<PmsProductFullReduction> fullReductionList) {
        fullReductionList.sort(new Comparator<PmsProductFullReduction>() {
            @Override
            public int compare(PmsProductFullReduction o1, PmsProductFullReduction o2) {
                return o2.getFullPrice().subtract(o1.getFullPrice()).intValue();
            }
        });

        for (PmsProductFullReduction fullReduction : fullReductionList) {
            if (amount.subtract(fullReduction.getFullPrice()).intValue() >= 0) {
                return fullReduction;
            }
        }
        return null;
    }


    /**
     * 获取折扣信息
     */
    public String getFullReductionMessage(PmsProductFullReduction fullReduction) {
        StringBuilder sb = new StringBuilder();
        sb.append("满");
        sb.append(fullReduction.getFullPrice());
        sb.append("元，减");
        sb.append(fullReduction.getReducePrice());
        sb.append("元");
        return sb.toString();
    }

}
