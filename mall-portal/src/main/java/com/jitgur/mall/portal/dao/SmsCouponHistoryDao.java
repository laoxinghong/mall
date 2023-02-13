package com.jitgur.mall.portal.dao;

import com.jitgur.mall.mbg.model.SmsCoupon;

import java.util.List;

/**
 * 优惠券管理dao
 * Created by jitgur on 20230213
 */
public interface SmsCouponHistoryDao {

    /**
     * 获取当前用户优惠券列表
     */
    List<SmsCoupon> getCouponList(Long memberId, Integer userStatus);

}
