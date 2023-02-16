package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.mbg.model.PmsProductDiscountLadder;
import com.jitgur.mall.mbg.model.PmsProductFullReduction;
import com.jitgur.mall.mbg.model.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 商品促销信息封装
 * Created by jitgur on 20230216
 */
@Getter
@Setter
public class OmsPromotionProduct extends PmsProduct {

    @ApiModelProperty("商品库存列表")
    private List<PmsSkuStock> skuStockList;
    @ApiModelProperty("商品折扣阶梯列表")
    private List<PmsProductDiscountLadder> ladderList;
    @ApiModelProperty("商品满减列表")
    private List<PmsProductFullReduction> fullReductionList;

}
