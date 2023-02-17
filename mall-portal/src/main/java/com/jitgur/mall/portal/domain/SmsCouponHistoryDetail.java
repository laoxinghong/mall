package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.SmsCoupon;
import com.jitgur.mall.mbg.model.SmsCouponHistory;
import com.jitgur.mall.mbg.model.SmsCouponProductCategoryRelation;
import com.jitgur.mall.mbg.model.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 会员优惠券详情
 * Created by jitgur on 20230217
 */
@Setter
@Getter
public class SmsCouponHistoryDetail extends SmsCouponHistory {

    @ApiModelProperty("具体优惠券信息")
    private SmsCoupon coupon;
    @ApiModelProperty("关联商品分类列表")
    private List<SmsCouponProductCategoryRelation> categoryRelationList;
    @ApiModelProperty("关联商品列表")
    private List<SmsCouponProductRelation> productRelationList;

}
