package com.jitgur.mall.portal.dao;

import com.jitgur.mall.portal.domain.OmsCartProduct;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车商品管理dao
 * Created by jitgur on 20230217
 */
public interface OmsCartItemDao {

    /**
     * 获取用于选择商品规格的商品信息
     */
    OmsCartProduct cartProduct(@Param("productId") Long productId);

}
