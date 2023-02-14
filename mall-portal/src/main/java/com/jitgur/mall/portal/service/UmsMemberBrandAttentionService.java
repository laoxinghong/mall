package com.jitgur.mall.portal.service;

import com.jitgur.mall.portal.domain.UmsMemberBrandAttention;
import org.springframework.data.domain.Page;

/**
 * 会员品牌关注管理service
 * Created by jitgur on 20230214
 */
public interface UmsMemberBrandAttentionService {

    /**
     * 添加关注
     */
    int add(UmsMemberBrandAttention memberBrandAttention);


    /**
     * 取消关注
     */
    int delete(Long brandId);


    /**
     * 获取品牌关注详情
     */
    UmsMemberBrandAttention detail(Long brandId);


    /**
     * 分页获取用户品牌关注列表
     */
    Page<UmsMemberBrandAttention> listPage(Integer pageNum, Integer pageSize);


    /**
     * 清空关注列表
     */
    void clear();

}
