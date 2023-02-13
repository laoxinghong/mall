package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.SmsCoupon;
import com.jitgur.mall.mbg.model.SmsCouponHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 会员优惠券管理Service
 * Created by jitgur on 20230212
 */
public interface UmsMemberCouponService {

    /**
     * 会员添加优惠券
     */
    @Transactional
    void add(Long couponId);


    /**
     * 获取优惠券历史列表
     */
    List<SmsCouponHistory> listHistory(Integer useStatus);


    /**
     * 根据购物车促销项目获取可用优惠券
     */
//    List<SmsCouponHistoryDetail> listCart(List<CartPromotionItem>  cartItemList,Integer type);


    /**
     * 获取当前商品相关优惠券
     */
    List<SmsCoupon> listByProduct(Long productId);


    /**
     * 获取当前用户优惠券列表
     */
    List<SmsCoupon> list(Integer useStatus);
}
