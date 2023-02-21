package com.jitgur.mall.admin.dao;

import com.jitgur.mall.mbg.model.UmsResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户资源关系dao
 * Created by jitgur on 20230207
 */
public interface UmsAdminResourceRelationDao {

    /**
     * 根据资源id获取用户id列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);


    /**
     * 根据用户id获取资源列表
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

}
