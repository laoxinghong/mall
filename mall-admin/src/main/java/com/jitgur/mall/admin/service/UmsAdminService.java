package com.jitgur.mall.admin.service;

import com.jitgur.mall.admin.dto.UmsAdminParam;
import com.jitgur.mall.admin.dto.UmsUpdateAdminPasswordParam;
import com.jitgur.mall.mbg.model.UmsAdmin;
import com.jitgur.mall.mbg.model.UmsResource;
import com.jitgur.mall.mbg.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台用户管理service
 * Created by jitgur on 20230206
 */
public interface UmsAdminService {


    /**
     * 获取缓存服务
     */
    UmsAdminCacheService getCacheService();


    /**
     * 分页显示所有用户
     */
    List<UmsAdmin> list(Integer pageNum, Integer pageSize);


    /**
     * 根据用户名获取后台用户
     */
    UmsAdmin getUserByUsername(String username);


    /**
     * 根据用户id获取后台用户
     */
    UmsAdmin getUserByAdminId(Long adminId);


    /**
     * 删除指定用户
     */
    int delete(Long id);


    /**
     * 获取用户对应的资源
     */
    List<UmsResource> getResourceList(Long adminId);


    /**
     * 修改用户角色对应关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);


    /**
     * 获取用户对应的角色
     */
    List<UmsRole> getRoleList(Long adminId);


    /**
     * 修改指定用户信息
     */
    int update(Long adminId, UmsAdmin umsAdmin);


    /**
     * 修改用户密码
     */
    int updatePassword(UmsUpdateAdminPasswordParam umsUpdateAdminPasswordParam);


    /**
     * 获取用户详情
     */
    UserDetails loadUserByUsername(String username);


    /**
     * 注册用户
     */
    UmsAdmin registry(UmsAdminParam adminParam);


    /**
     * 用户登录
     *
     * @Return token
     */
    String login(String username, String password);

}
