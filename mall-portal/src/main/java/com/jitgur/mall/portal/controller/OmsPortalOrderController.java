package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.portal.domain.OmsCalcIntegrationParam;
import com.jitgur.mall.portal.domain.OmsOrderConfirmation;
import com.jitgur.mall.portal.domain.OmsOrderDetail;
import com.jitgur.mall.portal.domain.OmsOrderParam;
import com.jitgur.mall.portal.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 前台订单管理controller
 * Created by jitgur on 20230220
 */
@Api(tags = "OmsPortalOrderController")
@Tag(name = "OmsPortalOrderController", description = "前台订单管理")
@Controller
@RequestMapping("/order")
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService orderService;


    @ApiOperation("根据购物车信息生成确认单")
    @RequestMapping(value = "/generateOrderConfirm", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OmsOrderConfirmation> generateOrderConfirm(@RequestParam List<Long> cartItemIdList) {
        OmsOrderConfirmation orderConfirmation = orderService.generateOrderConfirmation(cartItemIdList);
        return CommonResult.success(orderConfirmation);
    }


    @ApiOperation("生成订单")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> generateOrder(@RequestBody OmsOrderParam orderParam) {
        Map<String, Object> map = orderService.generateOrder(orderParam);
        return CommonResult.success(map, "已成功下单");
    }


    @ApiOperation("计算会员积分抵扣金额")
    @RequestMapping(value = "/calcIntegrationAmount", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<BigDecimal> calcIntegrationAmount(@RequestBody OmsCalcIntegrationParam param) {
        BigDecimal amount = orderService.calcIntegrationAmount(param.getOwnIntegration(),
                param.getUseIntegration(), param.isUseCoupon(), param.getTotalAmount());
        return CommonResult.success(amount);
    }


    @ApiOperation("取消订单")
    @RequestMapping(value = "/cancelOrder/#{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return CommonResult.success(null);
    }


    @ApiOperation("支付成功后回调")
    @RequestMapping(value = "/paySuccess/#{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> paySuccess(@PathVariable("orderId") Long orderId, @RequestParam Integer payType) {
        Integer integer = orderService.paySuccess(orderId, payType);
        return CommonResult.success(integer);
    }


    @ApiOperation("确认收货")
    @RequestMapping(value = "/confirmOrderReceive/#{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> confirmOrderReceive(@PathVariable("orderId") Long orderId) {
        orderService.confirmOrderReceive(orderId);
        return CommonResult.success(null);
    }


    @ApiOperation("分页获取用户订单详情")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<OmsOrderDetail>> listPage(
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<OmsOrderDetail> orderDetailList = orderService.listPage(status, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(orderDetailList));
    }


    @ApiOperation("根据id获取订单详情")
    @RequestMapping(value = "/getOrderDetail/#{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsOrderDetail> getOrderDetail(@PathVariable("orderId") Long orderId) {
        OmsOrderDetail orderDetail = orderService.getOrderDetail(orderId);
        return CommonResult.success(orderDetail);
    }


    @ApiOperation("删除指定订单")
    @RequestMapping(value = "/delete/#{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> delete(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
        return CommonResult.success(null);
    }

}
