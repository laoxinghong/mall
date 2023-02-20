package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.PmsProduct;
import com.jitgur.mall.portal.domain.PmsPortalProductDetail;
import com.jitgur.mall.portal.domain.PmsProductCategoryNode;
import com.jitgur.mall.portal.service.PmsPortalProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前台商品管理controller
 * Created by jitgur on 20230220
 */
@Api(tags = "PmsPortalProductController")
@Tag(name = "PmsPortalProductController", description = "商品管理")
@Controller
@RequestMapping("/product")
public class PmsPortalProductController {

    @Autowired
    private PmsPortalProductService productService;


    @ApiOperation("综合搜索 筛选 排序")
    @ApiImplicitParam(name = "sort", value = "排序字段：0>按相关度, 1>按新品, 2>按销量, 3>按价格升序, 4>按价格降序;",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long productCategoryId,
            @RequestParam(required = false, defaultValue = "0") Integer pageNum,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "0") Integer sort) {
        List<PmsProduct> productList = productService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
        return CommonResult.success(CommonPage.restPage(productList));
    }


    @ApiOperation("以树形结构获取所有商品分类")
    @RequestMapping(value = "/productCategoryTreeList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProductCategoryNode>> categoryTreeList() {
        List<PmsProductCategoryNode> categoryNodeList = productService.productCategoryTreeList();
        return CommonResult.success(categoryNodeList);
    }


    @ApiOperation("获取前台商品详情")
    @RequestMapping(value = "/detail/#{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsPortalProductDetail> detail(@PathVariable Long id) {
        PmsPortalProductDetail productDetail = productService.detail(id);
        return CommonResult.success(productDetail);
    }

}
