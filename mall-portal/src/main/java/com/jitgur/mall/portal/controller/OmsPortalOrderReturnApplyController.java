package com.jitgur.mall.portal.controller;

import com.jitgur.mall.common.api.CommonResult;
import com.jitgur.mall.portal.domain.OmsPortalOrderReturnApplyParam;
import com.jitgur.mall.portal.service.OmsPortalOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 退货申请管理controller
 * Created by jitgur on 20230220
 */
@Api(tags = "OmsPortalOrderReturnApplyController ")
@Tag(name = "OmsPortalOrderReturnApplyController ", description = "退货申请管理")
@RequestMapping("/returnApply")
@Controller
public class OmsPortalOrderReturnApplyController {

    @Autowired
    private OmsPortalOrderReturnApplyService returnApplyService;


    @ApiOperation("退货申请")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@RequestBody OmsPortalOrderReturnApplyParam returnApplyParam) {
        int result = returnApplyService.create(returnApplyParam);
        if (result > 0) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed("当前订单不存在");
        }
    }

}
