package com.jitgur.mall.portal.service;

import com.jitgur.mall.portal.domain.UmsMemberProductReadHistory;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 会员商品浏览记录管理service
 * Created by jitgur on 20230214
 */
public interface UmsMemberProductReadHistoryService {

    /**
     * 添加浏览记录
     */
    void create(UmsMemberProductReadHistory readHistory);


    /**
     * 删除指定浏览记录
     */
    void delete(String id);


    /**
     * 批量删除浏览记录
     */
    int deleteAll(List<String> idList);


    /**
     * 根据商品名称查找相关浏览记录
     */
    List<UmsMemberProductReadHistory> list(String productName);


    /**
     * 分页获取商品浏览记录
     */
    Page<UmsMemberProductReadHistory> listPage(Integer pageNum, Integer pageSize);


    /**
     * 清空浏览记录
     */
    void clear();

}
