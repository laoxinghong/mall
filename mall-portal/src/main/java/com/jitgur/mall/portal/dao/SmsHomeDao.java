package com.jitgur.mall.portal.dao;

import com.jitgur.mall.mbg.model.PmsBrand;
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
    List<PmsBrand>recommendBrandList(@Param("offset")Integer offset,@Param("limit")Integer limit);
}
