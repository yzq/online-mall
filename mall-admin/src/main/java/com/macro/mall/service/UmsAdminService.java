package com.macro.mall.service;

import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin register(UmsAdminParam umsAdminParam);

    int delete(Long id);

    List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize);

    int update(Long id, UmsAdmin umsAdmin);

    UmsAdmin getItem(Long id);

    List<UmsRole> getRoleList(Long adminId);

    int updateRole(Long adminId, List<Long> roleIds);
}
