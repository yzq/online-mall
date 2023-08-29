package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.UmsRoleMenuRelationDao;
import com.macro.mall.dao.UmsRoleResourceRelationDao;
import com.macro.mall.mapper.UmsRoleMapper;
import com.macro.mall.mapper.UmsRoleMenuRelationMapper;
import com.macro.mall.mapper.UmsRoleResourceRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private UmsRoleResourceRelationMapper roleResourceRelationMapper;
    @Override
    public int create(UmsRole umsRole) {
        umsRole.setAdminCount(0);
        umsRole.setSort(0);
        umsRole.setCreateTime(new Date());
        return umsRoleMapper.insert(umsRole);
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
    public List<UmsRole> list() {
        return umsRoleMapper.selectByExample(new UmsRoleExample());
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
        int count = menuIds == null ? 0 : menuIds.size();
        UmsRoleMenuRelationExample example = new UmsRoleMenuRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuRelationMapper.deleteByExample(example);
        if (!CollectionUtils.isEmpty(menuIds)) {
            List<UmsRoleMenuRelation> roleMenuRelationList = new ArrayList<>();
            for (Long menuId : menuIds) {
                UmsRoleMenuRelation roleMenuRelation = new UmsRoleMenuRelation();
                roleMenuRelation.setRoleId(roleId);
                roleMenuRelation.setMenuId(menuId);
                roleMenuRelationList.add(roleMenuRelation);
            }
            roleMenuRelationDao.insertList(roleMenuRelationList);
        }
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

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        int count = resourceIds == null ? 0 : resourceIds.size();
        UmsRoleResourceRelationExample example = new UmsRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceRelationMapper.deleteByExample(example);
        if (!CollectionUtils.isEmpty(resourceIds)) {
            List<UmsRoleResourceRelation> roleResourceRelationList = new ArrayList<>();
            for (Long resourceId : resourceIds) {
                UmsRoleResourceRelation roleResourceRelation = new UmsRoleResourceRelation();
                roleResourceRelation.setRoleId(roleId);
                roleResourceRelation.setResourceId(resourceId);
                roleResourceRelationList.add(roleResourceRelation);
            }
            roleResourceRelationDao.insertList(roleResourceRelationList);
        }
        return count;
    }
}
