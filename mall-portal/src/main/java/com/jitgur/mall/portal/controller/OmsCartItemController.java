package com.jitgur.mall.portal.controller;


import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.OmsCartItem;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.domain.OmsCartProduct;
import com.jitgur.mall.portal.domain.OmsCartPromotionItem;
import com.jitgur.mall.portal.service.OmsCartItemService;
import com.jitgur.mall.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车管理controller
 * Created by jitgur on 20230220
 */
@Api(tags = "OmsCartItemController")
@Tag(name = "OmsCartItemController", description = "购物车管理")
@RequestMapping("/cart")
@Controller
public class OmsCartItemController {

    @Autowired
    private OmsCartItemService cartItemService;
    @Autowired
    private UmsMemberService memberService;


    @ApiOperation("添加商品到购物车")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> add(@RequestBody OmsCartItem cartItem) {
        int count = cartItemService.add(cartItem);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("获取当前用户的购物车列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCartItem>> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        List<OmsCartItem> list = cartItemService.list(currentMember.getId());
        return CommonResult.success(list);
    }


    @ApiOperation("获取当前用户的购物车列表，包含促销信息")
    @RequestMapping(value = "/list/promotion", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<OmsCartPromotionItem>> listPromotion(@RequestParam List<Long> cartItemIdList) {
        UmsMember currentMember = memberService.getCurrentMember();
        List<OmsCartPromotionItem> cartPromotionItemList = cartItemService.listPromotion(currentMember.getId(), cartItemIdList);
        return CommonResult.success(cartPromotionItemList);
    }


    @ApiOperation("修改购物车中指定商品的数量")
    @RequestMapping(value = "/update/quantity", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> updateQuantity(@RequestParam Long cartItemId, @RequestParam Integer quantity) {
        UmsMember currentMember = memberService.getCurrentMember();
        int result = cartItemService.updateQuantity(cartItemId, currentMember.getId(), quantity);
        return CommonResult.success(result);
    }


    @ApiOperation("获取购物车中指定商品的规格，用于重选规格")
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OmsCartProduct> getCartProduct(@PathVariable Long productId) {
        OmsCartProduct cartProduct = cartItemService.cartProduct(productId);
        return CommonResult.success(cartProduct);
    }


    @ApiOperation("修改购物车中指定商品的规格")
    @RequestMapping(value = "/update/attr", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateAttr(@RequestBody OmsCartItem cartItem) {
        int result = cartItemService.updateAttr(cartItem);
        if (result > 0) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("删除购物车中的指定商品")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@RequestParam("cartItemIdList") List<Long> cartItemIdList) {
        UmsMember currentMember = memberService.getCurrentMember();
        int count = cartItemService.delete(currentMember.getId(), cartItemIdList);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("清空购物车")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> clear() {
        UmsMember currentMember = memberService.getCurrentMember();
        cartItemService.clear(currentMember.getId());
        return CommonResult.success(null);
    }

}

