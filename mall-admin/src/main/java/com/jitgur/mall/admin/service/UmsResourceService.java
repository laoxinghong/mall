package com.jitgur.mall.admin.service;

import com.jitgur.mall.mbg.model.UmsResource;

import java.util.List;

/**
 * 后台资源管理Service
 * Created by jitgur on 20230208
 */
public interface UmsResourceService {

    /**
     * 添加资源
     */
    int create(UmsResource resource);


    /**
     * 修改指定资源
     */
    int update(Long resourceId, UmsResource resource);


    /**
     * 删除指定资源
     */
    int delete(Long resourceId);


    /**
     * 获取指定资源
     */
    UmsResource getItem(Long resourceId);


    /**
     * 查询全部资源
     */
    List<UmsResource> listAll();


    /**
     * 分页查询资源
     */
    List<UmsResource> listPage(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize);

}
