package com.jitgur.mall.search.controller;

import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.search.domain.EsProduct;
import com.jitgur.mall.search.domain.EsProductRelatedInfo;
import com.jitgur.mall.search.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品搜索系统controller
 * Created by jitgur on 20230211
 */
@Api(tags = "EsProductController")
@Tag(name = "EsProductController", description = "商品搜索系统管理")
@Controller
@RequestMapping("/search")
public class EsProductController {

    @Autowired
    private EsProductService esProductService;


    @ApiOperation("将数据库中的数据导入es")
    @RequestMapping(value = "/importAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> importAll() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }


    @ApiOperation("新增es商品")
    @RequestMapping(value = "/create/#{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<EsProduct> create(@PathVariable("id") Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct == null) {
            return CommonResult.failed("当前商品不存在");
        } else {
            return CommonResult.success(esProduct);
        }
    }


    @ApiOperation("删除指定es商品")
    @RequestMapping(value = "/delete/#{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> delete(@PathVariable("id") Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }


    @ApiOperation("根据id批量删除es商品")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> deleteAll(@RequestParam("list") List<Long> ids) {
        esProductService.deleteAll(ids);
        return CommonResult.success(null);
    }


    @ApiOperation("简单商品搜索")
    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> simple(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }


    @ApiOperation("综合商品搜索")
    @ApiImplicitParam(name = "sort", value = "排序字段：0->按相关度； 1-按新品； 2-按销量； 3-按价格升序； 4-按价格降序",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/comprehensive", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> comprehensive(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "productCategoryId", required = false) Long productCategoryId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "sort", defaultValue = "0") Integer sort) {
        Page<EsProduct> esProductPage = esProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }


    @ApiOperation("相关商品推荐")
    @RequestMapping(value = "/recommend/#{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> recommend(@PathVariable("id") Long id,
                                                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.recommend(id, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }


    @ApiOperation("聚合搜索商品相关信息")
    @RequestMapping(value = "/searchRelatedInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(value = "keyword", required = false) String keyword) {
        EsProductRelatedInfo esProductRelatedInfo = esProductService.searchRelatedInfo(keyword);
        return CommonResult.success(esProductRelatedInfo);
    }

}
