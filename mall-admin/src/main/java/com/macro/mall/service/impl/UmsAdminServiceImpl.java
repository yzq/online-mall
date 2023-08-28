package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.UmsAdminRoleRelationDao;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.mapper.UmsAdminRoleRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.UmsAdminService;
import org.graalvm.util.CollectionsUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    
    @Autowired
    private UmsAdminMapper adminMapper;
    
    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;
    
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
//        umsAdmin.setStatus(1);
        UmsAdminExample adminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(adminExample);
        if (umsAdminList.size() > 0) {
            return null;
        }
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public int delete(Long id) {
        int count = adminMapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = umsAdminExample.createCriteria();
        if (!StrUtil.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            umsAdminExample.or(umsAdminExample.createCriteria().andNicknameLike("%" + keyword + "%"));
        }
        
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(umsAdminExample);
        return umsAdminList;
    }

    @Override
    public int update(Long id, UmsAdmin umsAdmin) {
        umsAdmin.setId(id);
        UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
        if (rawAdmin.getPassword().equals(umsAdmin.getPassword())) {
            umsAdmin.setPassword(null);
        } else {
            if (StrUtil.isEmpty(umsAdmin.getPassword())) {
                umsAdmin.setPassword(null);
            } else {
                umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
            }
        }
        int count = adminMapper.updateByPrimaryKeySelective(umsAdmin);
        return count;
    }
    
    @Override
    public UmsAdmin getItem(Long id) {
        UmsAdmin umsAdmin = adminMapper.selectByPrimaryKey(id);
        return umsAdmin;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        List<UmsRole> roleList = umsAdminRoleRelationDao.getRoleList(adminId);
        return roleList;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        UmsAdminRoleRelationExample umsAdminRoleRelationExample = new UmsAdminRoleRelationExample();
        UmsAdminRoleRelationExample.Criteria criteria = umsAdminRoleRelationExample.createCriteria();
        criteria.andUmsAdminIdEqualTo(adminId);
        umsAdminRoleRelationMapper.deleteByExample(umsAdminRoleRelationExample);
        if (CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> umsAdminRoleRelationList = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
                umsAdminRoleRelation.setUmsAdminId(adminId);
                umsAdminRoleRelation.setUmsRoleId(roleId);
                umsAdminRoleRelationList.add(umsAdminRoleRelation);
            }
            umsAdminRoleRelationDao.insertList(umsAdminRoleRelationList);
        }
        return count;
    }
}
