package com.jitgur.mall.portal.dao;

import com.jitgur.mall.mbg.model.OmsOrderItem;
import com.jitgur.mall.portal.domain.OmsOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单管理dao
 * Created by jitgur on 20230219
 */
public interface OmsPortalOrderDao {

    /**
     * 释放商品库存
     */
    void releaseProductLockStock(@Param("list") List<OmsOrderItem> orderItemList);


    /**
     * 获取超时订单详情
     */
    List<OmsOrderDetail> getTimeOutOrderDetailList(@Param("overtime") Integer overtime);


    /**
     * 批量更新订单状态
     */
    void updateOrderStatus(@Param("list") List<Long> orderIdList, @Param("targetStatus") Integer targetStatus);


    /**
     * 更新商品库存
     */
    int updateProductSkuStock(@Param("list") List<OmsOrderItem> orderItemList);


    /**
     * 根据id获取订单详情
     */
    OmsOrderDetail getOrderDetail(@Param("orderId") Long orderId);


    /**
     * 分页获取用户订单详情
     */
    List<OmsOrderDetail> listPage(@Param("memberId") Long memberId, @Param("status") Integer status);


}
