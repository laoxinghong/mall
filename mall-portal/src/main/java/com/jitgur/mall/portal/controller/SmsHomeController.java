package com.jitgur.mall.portal.controller;


import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.CmsSubject;
import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.mbg.model.SmsFlashPromotionSession;
import com.jitgur.mall.portal.domain.SmsHomeContentDetail;
import com.jitgur.mall.portal.service.SmsHomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页内容管理controller
 * Created by jitgur on 20230220
 */
@Api(tags = "SmsHomeController")
@Tag(name = "SmsHomeController", description = "首页内容管理")
@RequestMapping("/home")
@Controller
public class SmsHomeController {

    @Autowired
    private SmsHomeService homeService;


    @ApiOperation("首页内容信息展示")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsHomeContentDetail> content() {
        SmsHomeContentDetail content = homeService.content();
        return CommonResult.success(content);
    }


    @ApiOperation("根据分类获取专题")
    @RequestMapping(value = "/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<CmsSubject>> getSubjectList(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<CmsSubject> subjectList = homeService.getSubjectList(categoryId, pageNum, pageSize);
        return CommonResult.success(subjectList);
    }


    @ApiOperation("获取人气推荐商品")
    @RequestMapping(value = "/hotProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> getHotProductList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsProduct> hotProductList = homeService.hotProductList(pageNum, pageSize);
        return CommonResult.success(hotProductList);
    }


    @ApiOperation("获取新品推荐商品")
    @RequestMapping(value = "/newProductList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> getNewProductList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsProduct> newProductList = homeService.newProductList(pageNum, pageSize);
        return CommonResult.success(newProductList);
    }


    @ApiOperation("获取秒杀活动全部场次信息")
    @RequestMapping(value = "/getFlashPromotionSessionList/{flashPromotionId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsFlashPromotionSession>> getFlashPromotionSessionList(@PathVariable Long flashPromotionId) {
        List<SmsFlashPromotionSession> flashPromotionSessionList = homeService.getFlashPromotionSessionList(flashPromotionId);
        return CommonResult.success(flashPromotionSessionList);
    }

}

