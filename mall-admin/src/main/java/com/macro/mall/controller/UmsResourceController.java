package com.macro.mall.controller;

import com.macro.mall.api.CommonResult;
import com.macro.mall.model.UmsResource;
import com.macro.mall.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "UmsResourceController")
@Tag(name = "UmsResourceController", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    private UmsResourceService resourceService;

    @ApiOperation("添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsResource umsResource) {
        int count = resourceService.create(umsResource);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
