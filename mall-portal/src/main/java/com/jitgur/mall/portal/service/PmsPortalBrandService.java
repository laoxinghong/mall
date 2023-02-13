package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.PmsBrand;
import com.jitgur.mall.mbg.model.PmsProduct;

import java.util.List;

/**
 * 门户品牌管理service
 * Created by jitgur on 20230212
 */
public interface PmsPortalBrandService {

    /**
     * 分页获取推荐品牌
     */
    List<PmsBrand> recommendBrandList(Integer pageNum, Integer pageSize);


    /**
     * 获取品牌详情
     */
    PmsBrand detail(Long brandId);


    /**
     * 分页获取品牌关联商品
     */
    List<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize);

}
