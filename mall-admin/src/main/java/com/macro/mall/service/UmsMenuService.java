package com.macro.mall.service;

import com.macro.mall.dto.UmsMenuNode;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsMenuService {
    int create(UmsMenu umsMenu);

    int update(Long id, UmsMenu umsMenu);

    UmsMenu getItem(Long id);

    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    int delete(Long id);

    List<UmsMenuNode> treeList();
}
