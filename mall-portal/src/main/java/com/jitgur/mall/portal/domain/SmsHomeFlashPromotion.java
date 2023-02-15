package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.SmsFlashPromotion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 首页秒杀活动信息封装
 * Created by jitgur on 20230215
 */
@Getter
@Setter
public class SmsHomeFlashPromotion {

    @ApiModelProperty("当前秒杀活动")
    private SmsFlashPromotion flashPromotion;
    @ApiModelProperty("本场开始时间")
    private Date startTime;
    @ApiModelProperty("本场结束时间")
    private Date endTime;
    @ApiModelProperty("下场开始时间")
    private Date nextStartTime;
    @ApiModelProperty("下场结束时间")
    private Date nextEndTime;
    @ApiModelProperty("本场秒杀商品")
    private List<SmsFlashPromotionProduct> productList;

}
