package com.jitgur.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 生成订单请求参数
 * Created by jitgur on 20230218
 */
@Getter
@Setter
public class OmsOrderParam {

    @ApiModelProperty("收货地址")
    private Long addressId;
    @ApiModelProperty("商品列表")
    private List<Long> cartItemIdList;
    @ApiModelProperty("优惠券")
    private Long couponId;
    @ApiModelProperty("使用的积分数")
    private Integer useIntegration;
    @ApiModelProperty("支付方式")
    private Integer payType;

}
