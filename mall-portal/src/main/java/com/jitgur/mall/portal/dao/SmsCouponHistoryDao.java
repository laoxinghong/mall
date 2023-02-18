package com.jitgur.mall.portal.dao;

import com.jitgur.mall.mbg.model.SmsCoupon;
import com.jitgur.mall.portal.domain.SmsCouponHistoryDetail;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;

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


    /**
     * 获取会员优惠券详情列表
     */
    List<SmsCouponHistoryDetail> getCouponHistoryDetailList(@Param("memberId") Long memberId);


    /**
     * 获取指定优惠券详情
     */
    SmsCouponHistoryDetail getCouponHistoryDetailById(@Param("memberId")Long memberId,@Param("couponId")Long couponId);

}
