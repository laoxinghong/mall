package com.jitgur.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.jitgur.mall.admin.service.UmsAdminCacheService;
import com.jitgur.mall.admin.service.UmsResourceService;
import com.jitgur.mall.mbg.mapper.UmsResourceMapper;
import com.jitgur.mall.mbg.model.UmsResource;
import com.jitgur.mall.mbg.model.UmsResourceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * Created by jitgur on 20230208
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;


    @Override
    public int create(UmsResource resource) {
        resource.setCreateTime(new Date());
        return resourceMapper.insert(resource);
    }


    @Override
    public int update(Long resourceId, UmsResource resource) {
        resource.setId(resourceId);
        int result = resourceMapper.updateByPrimaryKeySelective(resource);
        adminCacheService.delResourceListByResourceId(resourceId);
        return result;
    }


    @Override
    public int delete(Long resourceId) {
        int result = resourceMapper.deleteByPrimaryKey(resourceId);
        adminCacheService.delResourceListByResourceId(resourceId);
        return result;
    }


    @Override
    public UmsResource getItem(Long resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }


    @Override
    public List<UmsResource> listAll() {
        UmsResourceExample example = new UmsResourceExample();
        return resourceMapper.selectByExample(example);
    }


    @Override
    public List<UmsResource> listPage(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyword)) {
            criteria.andNameLike('%' + nameKeyword + '%');
        }
        if (StrUtil.isNotEmpty(urlKeyword)) {
            criteria.andUrlLike('%' + urlKeyword + '%');
        }
        return resourceMapper.selectByExample(example);
    }

}
