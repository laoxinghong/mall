package com.jitgur.mall.portal.service;

import com.jitgur.mall.portal.domain.UmsMemberProductCollection;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 会员商品收藏管理service
 * Created by jitgut on 20230214
 */
public interface UmsMemberProductCollectionService {

    /**
     * 添加商品收藏
     */
    int create(UmsMemberProductCollection memberProductCollection);


    /**
     * 取消商品收藏
     */
    int delete(Long productId);


    /**
     * 分页获取用户商品收藏列表
     */
    Page<UmsMemberProductCollection> listPage(Integer pageNum,Integer pageSize);


    /**
     * 清空用户商品收藏
     */
    void clear();


    /**
     * 根据商品名称查询用户商品收藏信息
     */
    List<UmsMemberProductCollection> list(String productName);

}
