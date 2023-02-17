package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.PmsProductAttribute;
import com.jitgur.mall.mbg.model.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用于选择商品规格的商品信息
 * Created by jitgur on 20230217
 */
@Setter
@Getter
public class OmsCartProduct {

    @ApiModelProperty("商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
    @ApiModelProperty("商品sku列表")
    private List<PmsSkuStock> skuStockList;

}
