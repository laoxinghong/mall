package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.portal.domain.UmsMemberBrandAttention;
import com.jitgur.mall.portal.service.UmsMemberBrandAttentionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户关注品牌管理Controller
 * Created by jitgur on 20230220
 */
@Api(tags = "UmsMemberBrandAttentionController")
@Tag(name = "UmsMemberBrandAttentionController", description = "用户关注品牌管理")
@RequestMapping("/member/brandAttention")
@Controller
public class UmsMemberBrandAttentionController {

    @Autowired
    private UmsMemberBrandAttentionService memberBrandAttentionService;

    @ApiOperation("添加品牌关注")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> add(@RequestBody UmsMemberBrandAttention memberBrandAttention) {
        int add = memberBrandAttentionService.add(memberBrandAttention);
        if (add > 0) {
            return CommonResult.success(add);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("取消品牌关注")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(Long brandId) {
        int delete = memberBrandAttentionService.delete(brandId);
        if (delete > 0) {
            return CommonResult.success(delete);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("显示当前用户品牌关注列表")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberBrandAttention>> listPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<UmsMemberBrandAttention> list = memberBrandAttentionService.listPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(list));
    }


    @ApiOperation("清空当前用户品牌关注列表")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> clear() {
        memberBrandAttentionService.clear();
        return CommonResult.success(null);
    }


    @ApiOperation("显示品牌关注详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMemberBrandAttention> detail(@RequestParam Long brandId) {
        UmsMemberBrandAttention detail = memberBrandAttentionService.detail(brandId);
        return CommonResult.success(detail);
    }


    @ApiOperation("根据品牌名称查找品牌关注详情")
    @RequestMapping(value = "/listName", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberBrandAttention>> list(@RequestParam String brandName) {
        List<UmsMemberBrandAttention> list = memberBrandAttentionService.listName(brandName);
        return CommonResult.success(CommonPage.restPage(list));
    }

}
