package com.macro.mall.dao;

import com.macro.mall.model.UmsRoleMenuRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleMenuRelationDao {
    int insertList(@Param("RoleMenuRelationList") List<UmsRoleMenuRelation> roleMenuRelationList);
}
