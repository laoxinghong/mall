package com.jitgur.mall.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.jitgur.mall.admin.dao.UmsAdminResourceRelationDao;
import com.jitgur.mall.admin.service.UmsAdminCacheService;
import com.jitgur.mall.admin.service.UmsAdminService;
import com.jitgur.mall.common.service.RedisService;
import com.jitgur.mall.mbg.mapper.UmsRoleResourceRelationMapper;
import com.jitgur.mall.mbg.model.UmsAdmin;
import com.jitgur.mall.mbg.model.UmsResource;
import com.jitgur.mall.mbg.model.UmsRoleResourceRelation;
import com.jitgur.mall.mbg.model.UmsRoleResourceRelationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户信息缓存管理service
 * Created by jitgur on 20230206
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Autowired
    private UmsAdminResourceRelationDao adminResourceRelationDao;


    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }


    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }


    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin admin = adminService.getUserByAdminId(adminId);
        if (admin != null) {
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
            redisService.del(key);
        }
    }


    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }


    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + "::" + adminId;
        return (List<UmsResource>) redisService.get(key);
    }


    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
        redisService.del(key);
    }


    @Override
    public void delResourceListByRoleId(Long roleId) {
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UmsRoleResourceRelation> relationList = roleResourceRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream()
                    .map(relation -> keyPrefix + relation.getResourceId())
                    .collect(Collectors.toList());
            redisService.del(keys);
        }
    }


    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdIn(roleIds);
        List<UmsRoleResourceRelation> relationList = roleResourceRelationMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(relationList)) {
            String prefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream()
                    .map(relation -> prefix + relation.getResourceId())
                    .collect(Collectors.toList());
            redisService.del(keys);
        }
    }


    @Override
    public void delResourceListByResourceId(Long resourceId) {
        List<Long> adminIdList = adminResourceRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList)) {
            String prefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream()
                    .map(adminId -> prefix + adminId)
                    .collect(Collectors.toList());
            redisService.del(keys);
        }
    }

}
