package com.macro.mall.dao;

import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRoleResourceRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleResourceRelationDao {
    List<UmsResource> listResource(@Param("roleId") Long roleId);

    int insertList(@Param("roleResourceRelationList") List<UmsRoleResourceRelation> roleResourceRelationList);
}
