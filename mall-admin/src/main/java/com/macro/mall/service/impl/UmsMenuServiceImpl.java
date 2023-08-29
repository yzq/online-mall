package com.macro.mall.service.impl;

import com.macro.mall.mapper.UmsMenuMapper;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuMapper menuMapper;
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updatelevel(umsMenu);
        return menuMapper.insert(umsMenu);
    }

    private void updatelevel(UmsMenu umsMenu) {
        if (umsMenu.getParentId() == 0) {
            umsMenu.setLevel(0);
        } else {
            UmsMenu parentMenu = menuMapper.selectByPrimaryKey(umsMenu.getParentId());
            if (parentMenu != null) {
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            } else {
                umsMenu.setLevel(0);
            } 
        } 
    }
}
