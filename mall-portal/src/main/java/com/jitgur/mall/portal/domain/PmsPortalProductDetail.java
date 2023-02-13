package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 门户商品详情
 * Created by jitgur on 20230213
 */
@Getter
@Setter
public class PmsPortalProductDetail {

    @ApiModelProperty("商品信息")
    private PmsProduct product;
    @ApiModelProperty("商品品牌信息")
    private PmsBrand brand;
    @ApiModelProperty("商品属性信息")
    private List<PmsProductAttribute> attributeList;
    @ApiModelProperty("商品属性值")
    private List<PmsProductAttributeValue> attributeValueList;
    @ApiModelProperty("商品sku库存列表")
    private List<PmsSkuStock> skuStockList;
    @ApiModelProperty("商品折扣阶梯价格")
    private List<PmsProductDiscountLadder> ladderList;
    @ApiModelProperty("商品满减价格")
    private List<PmsProductFullReduction> fullReductionList;
    @ApiModelProperty("商品可用优惠券")
    private List<SmsCoupon> couponList;

}
