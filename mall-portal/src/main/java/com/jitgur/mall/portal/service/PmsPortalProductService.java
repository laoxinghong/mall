package com.jitgur.mall.portal.service;

import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.portal.domain.PmsPortalProductDetail;
import com.jitgur.mall.portal.domain.PmsProductCategoryNode;

import java.util.List;

/**
 * 门户商品管理service
 * Created by jitgur on 20230213
 */
public interface PmsPortalProductService {

    /**
     * 综合搜索商品
     */
    List<PmsProduct> search(String keyword, Long brandId, Long categoryId, Integer pageNum, Integer pageSize, Integer sort);


    /**
     * 商品分类树
     */
    List<PmsProductCategoryNode> productCategoryTreeList();


    /**
     * 返回门户商品详情
     */
    PmsPortalProductDetail detail(Long productId);

}
