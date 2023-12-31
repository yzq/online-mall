package com.macro.mall.controller;

import com.macro.mall.api.CommonPage;
import com.macro.mall.api.CommonResult;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "UmsRoleController")
@Tag(name = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsRole umsRole) {
        int count = roleService.create(umsRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("更新角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsRole umsRole) {
        int count = roleService.update(id, umsRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam List<Long> ids) {
        int count = roleService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> listAll() {
        List<UmsRole> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

    @ApiOperation("查询角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UmsRole> roleList = roleService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("更新角色状态")
    @RequestMapping(value = "updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = roleService.update(id, umsRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult allocMenu(@RequestParam(value = "roleId") Long roleId, @RequestParam(value = "menuIds") List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("查询已分配的菜单")
    @RequestMapping(value = "/listMenu/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long id) {
        List<UmsMenu> menuList = roleService.listMenu(id);
        return CommonResult.success(menuList);
    }

    @ApiOperation("查询已分配的资源")
    @RequestMapping(value = "/listResource/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsResource>> listResource(@PathVariable Long roleId) {
        List<UmsResource> resourceList = roleService.listResource(roleId);
        return CommonResult.success(resourceList);
    }

    @ApiOperation("分配资源")
    @RequestMapping(value = "/allocResource", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult allocResource(@RequestParam(value = "roleId") Long roleId, @RequestParam(value = "resourceIds") List<Long> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
