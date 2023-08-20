package com.macro.mall.controller;

import com.macro.mall.api.CommonPage;
import com.macro.mall.api.CommonResult;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@Controller
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    UmsAdminService adminService;

    @ApiOperation("注册后台用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
        int row = adminService.delete(id);
        return CommonResult.success(row);
    }

    @ApiOperation("查询用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsAdmin> umsAdminList = adminService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(umsAdminList));
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Long id, @RequestBody UmsAdmin umsAdmin) {
        int count = adminService.update(id, umsAdmin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("更新用户状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable("id") Long id, @RequestParam(value = "status") Integer status) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        int count = adminService.update(id, umsAdmin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取制定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getItem(@PathVariable("id") Long id) {
        UmsAdmin umsAdmin = adminService.getItem(id);
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation("获取用户的角色列表")
    @RequestMapping(value = "/role/{id}")
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable("adminId") Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "role/update")
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId, @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
