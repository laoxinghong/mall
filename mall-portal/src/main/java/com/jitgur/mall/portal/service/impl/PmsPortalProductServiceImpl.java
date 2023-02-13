package com.jitgur.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.jitgur.mall.mbg.mapper.*;
import com.jitgur.mall.mbg.model.*;
import com.jitgur.mall.portal.domain.PmsPortalProductDetail;
import com.jitgur.mall.portal.domain.PmsProductCategoryNode;
import com.jitgur.mall.portal.service.PmsPortalProductService;
import com.jitgur.mall.portal.service.UmsMemberCouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 门户商品管理service实现类
 * Created by jitgur on 20230213
 */
@Service
public class PmsPortalProductServiceImpl implements PmsPortalProductService {

    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductAttributeMapper attributeMapper;
    @Autowired
    private PmsProductAttributeValueMapper attributeValueMapper;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductDiscountLadderMapper ladderMapper;
    @Autowired
    private PmsProductFullReductionMapper fullReductionMapper;
    @Autowired
    private UmsMemberCouponService memberCouponService;


    @Override
    public List<PmsProduct> search(String keyword, Long brandId, Long categoryId, Integer pageNum, Integer pageSize, Integer sort) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        criteria.andPublishStatusEqualTo(1).andDeleteStatusEqualTo(0);

        if (StringUtil.isNotEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        if (brandId != null) {
            criteria.andBrandIdEqualTo(brandId);
        }
        if (categoryId != null) {
            criteria.andProductCategoryIdEqualTo(categoryId);
        }

        // 排序：1>新品 2>销量 3>价格降序 4>价格升序
        if (sort == 1) {
            example.setOrderByClause("id desc");
        } else if (sort == 2) {
            example.setOrderByClause("sale desc");
        } else if (sort == 3) {
            example.setOrderByClause("price desc");
        } else if (sort == 4) {
            example.setOrderByClause("price asc");
        }
        return productMapper.selectByExample(example);
    }


    @Override
    public List<PmsProductCategoryNode> productCategoryTreeList() {
        PmsProductCategoryExample example = new PmsProductCategoryExample();
        example.createCriteria().andShowStatusEqualTo(1);
        List<PmsProductCategory> categoryList = productCategoryMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(categoryList)) {
            return categoryList.stream()
                    .filter(item -> item.getParentId().equals(0L))
                    .map(item -> covert(item, categoryList))
                    .collect(Collectors.toList());
        }
        return null;
    }


    /**
     * 转换子分类信息
     */
    private PmsProductCategoryNode covert(PmsProductCategory productCategory, List<PmsProductCategory> categoryList) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(productCategory, node);
        List<PmsProductCategoryNode> children = categoryList.stream()
                .filter(item -> item.getParentId().equals(productCategory.getId()))
                .map(item -> covert(item, categoryList))
                .collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }


    @Override
    public PmsPortalProductDetail detail(Long productId) {
        PmsPortalProductDetail productDetail = new PmsPortalProductDetail();

        // 商品信息
        PmsProduct product = productMapper.selectByPrimaryKey(productId);
        productDetail.setProduct(product);

        // 商品品牌信息
        productDetail.setBrand(brandMapper.selectByPrimaryKey(product.getBrandId()));

        // 商品属性列表
        PmsProductAttributeExample attributeExample = new PmsProductAttributeExample();
        attributeExample.createCriteria().andProductAttributeCategoryIdEqualTo(product.getProductAttributeCategoryId());
        productDetail.setAttributeList(attributeMapper.selectByExample(attributeExample));

        // 商品属性值
        PmsProductAttributeValueExample attributeValueExample = new PmsProductAttributeValueExample();
        attributeValueExample.createCriteria().andProductIdEqualTo(productId);
        List<PmsProductAttributeValue> attributeValueList = attributeValueMapper.selectByExample(attributeValueExample);
        productDetail.setAttributeValueList(attributeValueList);

        // 商品sku库存列表
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(productId);
        List<PmsSkuStock> skuStockList = skuStockMapper.selectByExample(skuStockExample);
        productDetail.setSkuStockList(skuStockList);

        // 商品折扣阶梯价格
        PmsProductDiscountLadderExample ladderExample = new PmsProductDiscountLadderExample();
        ladderExample.createCriteria().andProductIdEqualTo(productId);
        List<PmsProductDiscountLadder> ladderList = ladderMapper.selectByExample(ladderExample);
        productDetail.setLadderList(ladderList);

        // 商品满减价格
        PmsProductFullReductionExample fullReductionExample = new PmsProductFullReductionExample();
        fullReductionExample.createCriteria().andProductIdEqualTo(productId);
        List<PmsProductFullReduction> fullReductionList = fullReductionMapper.selectByExample(fullReductionExample);
        productDetail.setFullReductionList(fullReductionList);

        // 商品可用优惠券列表
        List<SmsCoupon> couponList = memberCouponService.listByProduct(productId);
        productDetail.setCouponList(couponList);

        return productDetail;
    }

}
