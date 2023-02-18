package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.OmsCartItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 购物车商品信息封装
 * Created by jitgur on 20230218
 */
@Getter
@Setter
public class OmsCartPromotionItem extends OmsCartItem {

    @ApiModelProperty("促销活动信息")
    private String promotionMessage;
    @ApiModelProperty("促销活动减去价格，针对单个商品")
    private BigDecimal reducePrice;
    @ApiModelProperty("真实库存：现有库存-锁定库存")
    private Integer realStock;
    @ApiModelProperty("购买商品赠送积分")
    private Integer giftIntegration;
    @ApiModelProperty("购买商品赠送成长值")
    private Integer giftGrowth;

}
