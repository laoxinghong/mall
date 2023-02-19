package com.jitgur.mall.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.jitgur.mall.mbg.mapper.PmsBrandMapper;
import com.jitgur.mall.mbg.mapper.PmsProductMapper;
import com.jitgur.mall.mbg.model.PmsBrand;
import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.mbg.model.PmsProductExample;
import com.jitgur.mall.portal.dao.SmsHomeDao;
import com.jitgur.mall.portal.service.PmsPortalBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门户品牌管理service实现类
 * Created by jitgur on 20230212
 */
@Service
public class PmsPortalBrandServiceImpl implements PmsPortalBrandService {

    @Autowired
    private SmsHomeDao homeDao;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductMapper productMapper;


    @Override
    public List<PmsBrand> recommendBrandList(Integer pageNum, Integer pageSize) {
        return homeDao.recommendBrandList((pageNum - 1) * pageSize, pageSize);
    }


    @Override
    public PmsBrand detail(Long brandId) {
        return brandMapper.selectByPrimaryKey(brandId);
    }


    @Override
    public List<PmsProduct> productList(Long brandId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andBrandIdEqualTo(brandId).andDeleteStatusEqualTo(0).andPublishStatusEqualTo(1);
        return productMapper.selectByExample(example);
    }

}
