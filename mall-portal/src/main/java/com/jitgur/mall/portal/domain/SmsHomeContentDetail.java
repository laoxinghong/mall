package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容详情
 * Created by jitgur on 20230214
 */
@Getter
@Setter
public class SmsHomeContentDetail {

    @ApiModelProperty("首页轮播广告")
    private List<SmsHomeAdvertise> homeAdvertiseList;
    @ApiModelProperty("当前秒杀活动")
    private List<SmsHomeFlashPromotion> homeFlashPromotionList;
    @ApiModelProperty("首页品牌推荐")
    private List<PmsBrand> brandList;
    @ApiModelProperty("首页人气推荐商品")
    private List<PmsProduct> hotProductList;
    @ApiModelProperty("首页新品推荐商品")
    private List<PmsProduct> newProductList;
    @ApiModelProperty("首页专题推荐")
    private List<CmsSubject> subjectList;

}
