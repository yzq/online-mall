package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.UmsResourceMapper;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsResourceExample;
import com.macro.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return resourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        return resourceMapper.updateByPrimaryKeySelective(umsResource);
    }

    @Override
    public int delete(Long id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsResource> list(String nameKeyword,
                                  String urlKeyword,
                                  Long categoryId, 
                                  Integer pageSize, 
                                  Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyword)) {
            criteria.andNameLike("%" + nameKeyword + "%");
        }
        if (StrUtil.isNotEmpty(urlKeyword)) {
            criteria.andUrlLike("%" + urlKeyword + "%");
        }
        return resourceMapper.selectByExample(example);
    }

    @Override
    public UmsResource getItem(Long id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsResource> listAll() {
        UmsResourceExample example =new UmsResourceExample();
        return resourceMapper.selectByExample(example);
    }
}
