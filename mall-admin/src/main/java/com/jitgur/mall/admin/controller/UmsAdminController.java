package com.jitgur.mall.admin.controller;

import com.jitgur.mall.admin.dto.UmsAdminParam;
import com.jitgur.mall.admin.dto.UpdateAdminPasswordParam;
import com.jitgur.mall.admin.service.UmsAdminService;
import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.UmsAdmin;
import com.jitgur.mall.mbg.model.UmsResource;
import com.jitgur.mall.mbg.model.UmsRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户管理controller
 * Created by jitgur on 20230208
 */
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "后台用户管理")
@Controller
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;


    @ApiOperation("分页显示所有用户")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsAdmin>> list(@RequestParam("pageNum") Integer pageNum,
                                             @RequestParam("pageSize") Integer pageSize) {
        List<UmsAdmin> list = adminService.list(pageNum, pageSize);
        return CommonResult.success(list);
    }


    @ApiOperation("根据用户名获取后台用户")
    @RequestMapping(value = "/getUserByUsername", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getUserByUsername(String username) {
        UmsAdmin admin = adminService.getUserByUsername(username);
        if (admin == null) {
            return CommonResult.failed("没有该用户");
        } else {
            return CommonResult.success(admin);
        }
    }


    @ApiOperation("根据用户id获取后台用户")
    @RequestMapping(value = "/getUserByAdminId/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getUserByAdminId(@PathVariable("adminId") Long adminId) {
        UmsAdmin admin = adminService.getUserByAdminId(adminId);
        if (admin == null) {
            return CommonResult.failed("没有该用户");
        } else {
            return CommonResult.success(admin);
        }
    }


    @ApiOperation("删除指定用户")
    @RequestMapping(value = "/delete/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable("adminId") Long adminId) {
        int delete = adminService.delete(adminId);
        if (delete > 0) {
            return CommonResult.success(delete);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("获取用户对应的资源列表")
    @RequestMapping(value = "/getResourceList/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsResource>> getResourceList(@PathVariable("adminId") Long adminId) {
        List<UmsResource> resourceList = adminService.getResourceList(adminId);
        return CommonResult.success(resourceList);
    }


    @ApiOperation("修改用户角色对应关系")
    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateRole(@RequestParam("adminId") Long adminId,
                                            @RequestParam("list") List<Long> roleIds) {
        int update = adminService.updateRole(adminId, roleIds);
        if (update > 0) {
            return CommonResult.success(update);
        } else {
            return CommonResult.failed("角色ID队列不能为空");
        }
    }


    @ApiOperation("获取用户对应的角色列表")
    @RequestMapping(value = "/getRoleList/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable("adminId") Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }


    @ApiOperation("更新指定用户信息")
    @RequestMapping(value = "/update/{adminId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable("adminId") Long adminId,
                                        @RequestBody UmsAdmin admin) {
        int update = adminService.update(adminId, admin);
        if (update > 0) {
            return CommonResult.success(update);
        } else {
            return CommonResult.failed();
        }
    }


    @ApiOperation("修改用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updatePassword(@RequestBody UpdateAdminPasswordParam param) {
        int result = adminService.updatePassword(param);
        return CommonResult.success(result);
    }


    @ApiOperation("用户注册")
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> registry(@RequestBody UmsAdminParam adminParam) {
        UmsAdmin registry = adminService.registry(adminParam);
        return CommonResult.success(registry);
    }


    @ApiOperation("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> login(String username, String password) {
        String token = adminService.login(username, password);
        return CommonResult.success(token);
    }

}
