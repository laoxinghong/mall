package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.PmsProduct;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀活动商品信息封装
 * Created by jitgur on 20230215
 */
@Getter
@Setter
public class SmsFlashPromotionProduct extends PmsProduct {

    @ApiModelProperty("秒杀价格")
    private BigDecimal flashPrice;
    @ApiModelProperty("秒杀商品数量")
    private Integer flashCount;
    @ApiModelProperty("秒杀限购数量")
    private Integer perCount;

}
