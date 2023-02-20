package com.jitgur.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 计算积分抵扣金额信息封装
 * Created by jitgur on 20230220
 */
@Getter
@Setter
@AllArgsConstructor
public class OmsCalcIntegrationParam {

    @ApiModelProperty("用户拥有积分")
    private Integer ownIntegration;
    @ApiModelProperty("订单使用积分")
    private Integer useIntegration;
    @ApiModelProperty("是否使用优惠券")
    private boolean useCoupon;
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

}
