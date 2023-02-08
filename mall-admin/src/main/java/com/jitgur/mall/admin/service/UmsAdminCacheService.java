package com.jitgur.mall.admin.service;

import com.jitgur.mall.mbg.model.UmsAdmin;
import com.jitgur.mall.mbg.model.UmsResource;

import java.util.List;

/**
 * 后台用户信息缓存服务service
 * Created by jitgur on 20230206
 */
public interface UmsAdminCacheService {

    /**
     * 设置缓存后台用户信息
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户信息
     */
    UmsAdmin getAdmin(String username);

    /**
     * 删除缓存后台用户信息
     */
    void delAdmin(Long adminId);


    /**
     * 设置用户资源列表缓存
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);


    /**
     * 获取用户资源列表缓存
     */
    List<UmsResource> getResourceList(Long adminId);


    /**
     * 删除用户资源列表缓存
     */
    void delResourceList(Long adminId);


    /**
     * 当角色相关资源信息改变时删除指定角色相关缓存
     */
    void delResourceListByRoleId(Long roleId);


    /**
     * 当角色相关资源信息改变时删除指定角色相关缓存
     */
    void delResourceListByRoleIds(List<Long> roleIds);


    /**
     * 当资源信息改变时，删除资源项目相关用户缓存
     */
    void delResourceListByResourceId(Long resourceId);

}
