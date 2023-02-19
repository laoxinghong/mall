package com.jitgur.mall.portal.dao;

import com.jitgur.mall.mbg.model.OmsOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单商品管理dao
 * Created by jitgur on 20230219
 */
public interface OmsPortalOrderItemDao {

    /**
     * 批量插入订单商品
     */
    int insertAll(@Param("list") List<OmsOrderItem> orderItemList);

}
