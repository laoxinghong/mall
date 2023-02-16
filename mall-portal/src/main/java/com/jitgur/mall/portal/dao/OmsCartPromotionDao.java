package com.jitgur.mall.portal.dao;

import com.jitgur.mall.portal.domain.OmsPromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 购物车促销信息管理dao
 * Created by jitgur on 20230216
 */
public interface OmsCartPromotionDao {

    /**
     * 获取购物车商品相关促销优惠信息
     */
    List<OmsPromotionProduct> getProductPromotion(@Param("productIdList") List<Long> productIdList);


}
