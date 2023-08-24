package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.UmsRoleMapper;
import com.macro.mall.model.UmsRole;
import com.macro.mall.model.UmsRoleExample;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleMapper umsRoleMapper;
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
}
