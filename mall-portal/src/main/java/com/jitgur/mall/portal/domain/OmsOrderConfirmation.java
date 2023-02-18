package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.UmsMemberIntegrationConsumeSetting;
import com.jitgur.mall.mbg.model.UmsMemberReceiveAddress;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单确认单信息封装
 * Created by jitgur on 20230218
 */
@Setter
@Getter
public class OmsOrderConfirmation {

    @ApiModelProperty("会员收货地址列表")
    private List<UmsMemberReceiveAddress> addressesList;
    @ApiModelProperty("购物车商品列表（包含促销信息）")
    private List<OmsCartPromotionItem> cartPromotionItemList;
    @ApiModelProperty("可使用优惠券列表")
    private List<SmsCouponHistoryDetail> couponHistoryDetailList;
    @ApiModelProperty("会员当前积分数")
    private Integer integration;
    @ApiModelProperty("积分使用规则")
    private UmsMemberIntegrationConsumeSetting integrationConsumeSetting;
    @ApiModelProperty("金额信息")
    private AmountCalculation amountCalculation;

    @Setter
    @Getter
    public static class AmountCalculation {

        @ApiModelProperty("商品总金额")
        private BigDecimal totalAmount;
        @ApiModelProperty("运费")
        private BigDecimal freightAmount;
        @ApiModelProperty("促销优惠")
        private BigDecimal promotionAmount;
        @ApiModelProperty("应付金额")
        private BigDecimal payableAmount;

    }

}
