package com.jitgur.mall.search.service;

/**
 * 商品搜索系统管理service
 * Created by jitgur on 20230209
 */
public interface EsProductService {

    /**
     * 将数据库数据导入es
     */
    int importAll();

}
