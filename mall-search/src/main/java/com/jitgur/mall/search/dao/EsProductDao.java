package com.jitgur.mall.search.dao;

import com.jitgur.mall.search.domain.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统商品管理dao
 * Created by jitgur on 20230209
 */
public interface EsProductDao {

    /**
     * 将数据库中数据导入es
     */
    List<EsProduct> getEsProductListById(@Param("id") Long id);
}
