package com.jitgur.mall.admin.controller;

import com.jitgur.mall.admin.service.UmsResourceService;
import com.jitgur.mall.common.api.CommonPage;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.UmsResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台资源管理Controller
 * Created by jitgur on 20230208
 */
@Api(tags = "UmsResourceController")
@Tag(name = "UmsResource", description = "后台资源管理")
@Controller
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService resourceService;


    @ApiOperation("添加资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@RequestBody UmsResource resource) {
        int create = resourceService.create(resource);
        if (create > 0) {
            return CommonResult.success(create);
        } else {
            return CommonResult.failed("创建用户失败");
        }
    }


    @ApiOperation("修改指定资源")
    @RequestMapping(value = "/update/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable("resourceId") Long resourceId,
                                        @RequestBody UmsResource resource) {
        int update = resourceService.update(resourceId, resource);
        if (update > 0) {
            return CommonResult.success(update);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("删除指定资源")
    @RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable("resourceId") Long resourceId) {
        int delete = resourceService.delete(resourceId);
        if (delete > 0) {
            return CommonResult.success(delete);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("获取指定资源")
    @RequestMapping(value = "/getItem/{resourceId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsResource> getItem(@PathVariable("resourceId") Long resourceId) {
        UmsResource resource = resourceService.getItem(resourceId);
        if (resource == null) {
            return CommonResult.failed("当前资源不存在");
        } else {
            return CommonResult.success(resource);
        }
    }


    @ApiOperation("分页获取资源")
    @RequestMapping(value = "/listPage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsResource>> listPage(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "nameKeyword", required = false) String nameKeyword,
            @RequestParam(value = "urlKeyword", required = false) String urlKeyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsResource> resourceList = resourceService.listPage(categoryId, nameKeyword, urlKeyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

}
