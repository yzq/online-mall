package com.macro.mall.dao;

import com.macro.mall.model.UmsAdminRoleRelation;
import com.macro.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsAdminRoleRelationDao {
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);
    
    int insertList(@Param("UmsAdminRoleRelationList")List<UmsAdminRoleRelation> umsAdminRoleRelationList);
}
