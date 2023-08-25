package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.UmsRoleMenuRelationDao;
import com.macro.mall.dao.UmsRoleResourceRelationDao;
import com.macro.mall.mapper.UmsRoleMapper;
import com.macro.mall.mapper.UmsRoleMenuRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleMapper umsRoleMapper;
    
    @Autowired
    private UmsRoleMenuRelationMapper roleMenuRelationMapper;
    @Autowired
    private UmsRoleMenuRelationDao roleMenuRelationDao;
    @Autowired
    private UmsRoleResourceRelationDao roleResourceRelationDao;
    @Override
    public int create(UmsRole umsRole) {
        umsRole.setCreateTime(new Date());
        return umsRoleMapper.insertSelective(umsRole);
    }

    @Override
    public int update(Long id, UmsRole umsRole) {
        umsRole.setId(id);
        return umsRoleMapper.updateByPrimaryKeySelective(umsRole);
    }

    @Override
    public int delete(List<Long> ids) {
        UmsRoleExample example =new UmsRoleExample();
        UmsRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        int count = umsRoleMapper.deleteByExample(example);
        return count;
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsRoleExample example = new UmsRoleExample();
        if (!StrUtil.isEmpty(keyword)) {
            example.createCriteria().andNameLike("%"+keyword+"%");
        }
        List<UmsRole> roleList = umsRoleMapper.selectByExample(example);
        return roleList;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        List<UmsRoleMenuRelation> roleMenuRelationList = new ArrayList<>();
        for (Long menuId : menuIds) {
            UmsRoleMenuRelation roleMenuRelation = new UmsRoleMenuRelation();
            roleMenuRelation.setRoleId(roleId);
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelationList.add(roleMenuRelation);
        }
        int count = roleMenuRelationDao.insertList(roleMenuRelationList);
        return count;
    }

    @Override
    public List<UmsMenu> listMenu(Long id) {
        List<UmsMenu> umsMenuList = roleMenuRelationDao.listMenu(id);
        return umsMenuList;
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        List<UmsResource> resourceList = roleResourceRelationDao.listResource(roleId);
        return resourceList;
    }
}
