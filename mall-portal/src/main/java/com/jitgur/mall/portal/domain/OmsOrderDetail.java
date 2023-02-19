package com.jitgur.mall.portal.domain;

import com.jitgur.mall.mbg.model.OmsOrder;
import com.jitgur.mall.mbg.model.OmsOrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 订单详情封装
 * Created by jitgur on 20230219
 */
@Getter
@Setter
public class OmsOrderDetail extends OmsOrder {

    @ApiModelProperty("订单商品列表")
    private List<OmsOrderItem> orderItemList;

}
