package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.SmsCoupon;
import com.jitgur.mall.mbg.model.SmsCouponHistory;
import com.jitgur.mall.portal.domain.OmsCartPromotionItem;
import com.jitgur.mall.portal.domain.SmsCouponUpdateParam;
import com.jitgur.mall.portal.domain.SmsCouponHistoryDetail;
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
     * 获取购物车商品可用优惠券
     *
     * @param cartPromotionItemList 购物车商品列表
     * @param type                  0>不可用优惠券 1>可用优惠券
     */
    List<SmsCouponHistoryDetail> listCartCoupon(List<OmsCartPromotionItem> cartPromotionItemList, Integer type);


    /**
     * 获取当前商品相关优惠券
     */
    List<SmsCoupon> listByProduct(Long productId);


    /**
     * 获取当前用户优惠券列表
     */
    List<SmsCoupon> list(Integer useStatus);


    /**
     * 更新优惠券使用状态
     */
    void updateCoupon(SmsCouponUpdateParam updateParam);

}
