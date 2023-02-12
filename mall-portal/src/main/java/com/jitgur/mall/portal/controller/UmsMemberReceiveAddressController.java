package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.UmsMemberReceiveAddress;
import com.jitgur.mall.portal.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收货地址管理controller
 * Created by jitgur on 20230212
 */
@Api(tags = "UmsMemberReceiveAddressController")
@Tag(name = "UmsMemberReceiveAddressController", description = "会员收货地址管理")
@Controller
@RequestMapping("/address")
public class UmsMemberReceiveAddressController {

    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;


    @ApiOperation("添加收货地址")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@RequestBody UmsMemberReceiveAddress memberReceiveAddress) {
        int result = memberReceiveAddressService.create(memberReceiveAddress);
        if (result > 0) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("删除收货地址")
    @RequestMapping(value = "/delete/#{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable("id") Long id) {
        int delete = memberReceiveAddressService.delete(id);
        if (delete > 0) {
            return CommonResult.success(delete);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("修改收货地址")
    @RequestMapping(value = "/update/#{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable("id") Long id, @RequestBody UmsMemberReceiveAddress address) {
        int update = memberReceiveAddressService.update(id, address);
        if (update > 0) {
            return CommonResult.success(update);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("获取当前用户收货地址列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMemberReceiveAddress>> list() {
        List<UmsMemberReceiveAddress> list = memberReceiveAddressService.list();
        return CommonResult.success(list);
    }


    @ApiOperation("获得指定收货地址")
    @RequestMapping(value = "/getItem/#{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMemberReceiveAddress> getItem(@PathVariable("id") Long id) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getItem(id);
        if (address == null) {
            return CommonResult.failed("当前地址不存在");
        } else {
            return CommonResult.success(address);
        }
    }

}
