package com.macro.mall.service;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsRoleService {
    int create(UmsRole umsRole);

    int update(Long id, UmsRole umsRole);

    int delete(List<Long> ids);
}
