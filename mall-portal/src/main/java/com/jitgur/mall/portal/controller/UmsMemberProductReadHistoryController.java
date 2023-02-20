package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.portal.domain.UmsMemberProductReadHistory;
import com.jitgur.mall.portal.service.UmsMemberProductReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户浏览记录管理Controller
 * Created by jitgur on 20230220
 */
@Api(tags = "UmsMemberProductReadHistoryController")
@Tag(name = "UmsMemberProductReadHistoryController", description = "用户浏览记录管理")
@Controller
@RequestMapping("/member/readHistory")
public class UmsMemberProductReadHistoryController {

    @Autowired
    private UmsMemberProductReadHistoryService readHistoryService;


    @ApiOperation("创建用户浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> create(@RequestBody UmsMemberProductReadHistory readHistory) {
        readHistoryService.create(readHistory);
        return CommonResult.success(null);
    }


    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> deleteAll(@RequestParam("ids") List<String> ids) {
        int result = readHistoryService.deleteAll(ids);
        if (result > 0) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("删除指定浏览记录")
    @RequestMapping(value = "/delete/#{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> delete(@PathVariable("id") String id) {
        readHistoryService.delete(id);
        return CommonResult.success(null);
    }


    @ApiOperation("分页查阅浏览记录")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberProductReadHistory>> listPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Page<UmsMemberProductReadHistory> page = readHistoryService.listPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));
    }


    @ApiOperation("清空浏览记录")
    @RequestMapping(value = "/value", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> clear() {
        readHistoryService.clear();
        return CommonResult.success(null);
    }


    @ApiOperation("根据商品名称查找相关浏览记录")
    @RequestMapping(value = "/listName", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberProductReadHistory>> listName(@RequestParam String productName) {
        List<UmsMemberProductReadHistory> historyList = readHistoryService.listName(productName);
        return CommonResult.success(CommonPage.restPage(historyList));
    }

}
