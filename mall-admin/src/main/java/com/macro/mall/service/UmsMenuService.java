package com.macro.mall.service;

import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsRole;

public interface UmsMenuService {
    int create(UmsMenu umsMenu);

    int update(Long id, UmsMenu umsMenu);

    UmsMenu getItem(Long id);
}
