package com.jitgur.mall.admin.dao;

import com.jitgur.mall.mbg.model.UmsAdminRoleRelation;
import com.jitgur.mall.mbg.model.UmsRole;

import java.util.List;

/**
 * 用户角色关系dao
 * Created by jitgur on 20230207
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 批量插入
     */
    void insertList(List<UmsAdminRoleRelation> relationList);


    /**
     * 获取用户对应的角色列表
     */
    List<UmsRole> getRoleList(Long adminId);

}
