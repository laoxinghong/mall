package com.jitgur.mall.portal.dao;

import com.jitgur.mall.mbg.model.CmsSubject;
import com.jitgur.mall.mbg.model.PmsBrand;
import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.portal.domain.SmsFlashPromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 首页内容管理dao
 * Created by jitgur on 20230213
 */
public interface SmsHomeDao {

    /**
     * 分页获取推荐品牌
     */
    List<PmsBrand> recommendBrandList(@Param("offset") Integer offset, @Param("limit") Integer limit);


    /**
     * 首页热门商品推荐
     */
    List<PmsProduct> hotProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);


    /**
     * 首页新品商品推荐
     */
    List<PmsProduct> newProductList(@Param("offset") Integer offset, @Param("limit") Integer limit);


    /**
     * 首页专题推荐
     */
    List<CmsSubject> homeSubjectList(@Param("offset") Integer offset, @Param("limit") Integer limit);


    /**
     * 获取秒杀商品信息
     */
    List<SmsFlashPromotionProduct> getFlashPromotionProductList(@Param("flashPromotionId") Long flashPromotionId, @Param("sessionId") Long sessionId);

}
