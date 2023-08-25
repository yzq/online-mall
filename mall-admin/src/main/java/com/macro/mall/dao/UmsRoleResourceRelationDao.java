package com.macro.mall.dao;

import com.macro.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UmsRoleResourceRelationDao {
    List<UmsResource> listResource(@Param("roleId") Long roleId);
}
