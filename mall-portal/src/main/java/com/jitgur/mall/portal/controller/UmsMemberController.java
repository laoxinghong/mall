package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.mbg.model.UmsMember;
import com.jitgur.mall.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 会员管理controller
 * Created by jitgur on 20230212
 */
@Api(tags = "UmsMemberController")
@Tag(name = "UmsMemberController", description = "会员管理")
@Controller
@RequestMapping("/member")
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;


    @ApiOperation("生成验证码")
    @RequestMapping(value = "/generateAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> generateAuthCode(@RequestParam String telephone) {
        String authCode = memberService.generateAuthCode(telephone);
        return CommonResult.success(authCode);
    }


    @ApiOperation("根据用户名获取会员")
    @RequestMapping(value = "/getUserByUsername", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMember> getUserByUsername(@RequestParam String username) {
        UmsMember member = memberService.getMemberByUsername(username);
        if (member == null) {
            return CommonResult.failed("当前用户不存在");
        } else {
            return CommonResult.success(member);
        }
    }


    @ApiOperation("根据id获取会员")
    @RequestMapping(value = "/getUserById/#{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMember> getUserById(@PathVariable("id") Long id) {
        UmsMember member = memberService.getMemberById(id);
        if (member == null) {
            return CommonResult.failed("当前用户不存在");
        } else {
            return CommonResult.success(member);
        }
    }


    @ApiOperation("修改用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updatePassword(@RequestParam String telephone,
                                               @RequestParam String authCode,
                                               @RequestParam String password) {
        memberService.updatePassword(telephone, authCode, password);
        return CommonResult.success("修改密码成功");
    }


    @ApiOperation("更新会员积分")
    @RequestMapping(value = "/updateMemberIntegration/#{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updateMemberIntegration(@PathVariable("id") Long id, @RequestParam Integer integration) {
        memberService.updateMemberIntegration(id, integration);
        return CommonResult.success("修改成功");
    }


    @ApiOperation("刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> refreshToken(@RequestParam String token) {
        String newToken = memberService.refreshToken(token);
        return CommonResult.success(newToken);
    }


    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> login(@RequestParam String username,
                                      @RequestParam String password) {
        String token = memberService.login(username, password);
        return CommonResult.success(token);
    }


    @ApiOperation("会员注册")
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> registry(@RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam String telephone,
                                         @RequestParam String authCode) {
        memberService.registry(username, password, telephone, authCode);
        return CommonResult.success("注册成功");
    }

}
