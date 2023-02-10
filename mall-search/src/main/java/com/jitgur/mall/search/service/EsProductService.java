package com.jitgur.mall.search.service;

import com.jitgur.mall.search.domain.EsProduct;
import com.jitgur.mall.search.domain.EsProductRelatedInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品搜索系统管理service
 * Created by jitgur on 20230209
 */
public interface EsProductService {

    /**
     * 将数据库数据导入es
     */
    int importAll();


    /**
     * 新增es商品
     */
    EsProduct create(Long id);


    /**
     * 删除指定es商品
     */
    void delete(Long id);


    /**
     * 批量删除商品
     */
    void deleteAll(List<Long> ids);


    /**
     * 简单商品搜索
     * 搜索商品名称  标题  关键字中包含keyword的商品
     */
    Page<EsProduct> search(String keyword,Integer pageNum,Integer pageSize);


    /**
     * 综合商品搜索
     * 涉及到筛选、不同字段匹配权重不同、排序
     */
    Page<EsProduct> search(String keyword,Long brandId,Long productCategoryId,Integer pageNum,Integer pageSize,Integer sort);


    /**
     * 相关商品推荐
     * 根据商品id推荐相关商品
     */
    Page<EsProduct> recommend(Long id,Integer pageNum, Integer pageSize);


    /**
     * 聚合搜索商品相关信息
     * 根据关键字搜索相关商品
     * 再聚合搜索这些商品获得相关品牌、分类、属性信息
     */
    EsProductRelatedInfo searchRelatedInfo(String keyword);

}
