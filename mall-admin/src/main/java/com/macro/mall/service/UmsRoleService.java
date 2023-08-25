package com.macro.mall.service;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsRoleService {
    int create(UmsRole umsRole);

    int update(Long id, UmsRole umsRole);

    int delete(List<Long> ids);
    List<UmsRole> list(String keyword, Integer pageNum, Integer pageSize);

    int allocMenu(Long roleId, List<Long> menuIds);

    List<UmsMenu> listMenu(Long id);
}
