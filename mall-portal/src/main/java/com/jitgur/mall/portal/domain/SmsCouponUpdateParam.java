package com.jitgur.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 更新优惠券使用状态请求参数
 * Created by jitgur on 20230219
 */
@Getter
@Setter
@AllArgsConstructor
public class SmsCouponUpdateParam {

    @ApiModelProperty("会员id")
    private Long memberId;
    @ApiModelProperty("优惠券id")
    private Long couponId;
    @ApiModelProperty("订单id")
    private Long orderId;
    @ApiModelProperty("使用状态")
    private Integer useStatus;
    @ApiModelProperty("使用时间")
    private Date useTime;
    @ApiModelProperty("订单编码")
    private String orderSn;

}
