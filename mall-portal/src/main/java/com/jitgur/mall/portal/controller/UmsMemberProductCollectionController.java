package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.portal.domain.UmsMemberProductCollection;
import com.jitgur.mall.portal.service.UmsMemberProductCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户商品收藏管理Controller
 * Created by jitgur on 20230220
 */
@Api(tags = "UmsMemberProductCollectionController")
@Tag(name = "UmsMemberProductCollectionController", description = "用户商品收藏管理")
@RequestMapping("/member/productCollect")
@Controller
public class UmsMemberProductCollectionController {

    @Autowired
    private UmsMemberProductCollectionService memberProductCollectionService;


    @ApiOperation("添加商品收藏")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> add(@RequestBody UmsMemberProductCollection memberProductCollection) {
        int add = memberProductCollectionService.create(memberProductCollection);
        if (add > 0) {
            return CommonResult.success(add);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("删除商品收藏")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@RequestParam Long productId) {
        int delete = memberProductCollectionService.delete(productId);
        if (delete > 0) {
            return CommonResult.success(delete);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("显示当前用户商品收藏列表")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberProductCollection>> list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<UmsMemberProductCollection> list = memberProductCollectionService.listPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @ApiOperation("商品收藏详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMemberProductCollection> detail(@RequestParam Long productId) {
        UmsMemberProductCollection detail = memberProductCollectionService.detail(productId);
        return CommonResult.success(detail);
    }


    @ApiOperation("清空当前用户商品收藏列表")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> clear() {
        memberProductCollectionService.clear();
        return CommonResult.success(null);
    }


    @ApiOperation("根据商品名称查询用户商品收藏信息")
    @RequestMapping(value = "/listName", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberProductCollection>> listName(@RequestParam String productName) {
        List<UmsMemberProductCollection> list = memberProductCollectionService.listName(productName);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
