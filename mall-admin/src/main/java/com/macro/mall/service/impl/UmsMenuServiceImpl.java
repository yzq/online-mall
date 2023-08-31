package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.UmsMenuNode;
import com.macro.mall.mapper.UmsMenuMapper;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsMenuExample;
import com.macro.mall.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    private UmsMenuMapper menuMapper;

    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updatelevel(umsMenu);
        return menuMapper.insert(umsMenu);
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        return menuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample menuExample = new UmsMenuExample();
        menuExample.setOrderByClause("sort desc");
        menuExample.createCriteria().andParentIdEqualTo(parentId);
        return menuMapper.selectByExample(menuExample);
    }

    @Override
    public int delete(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = menuMapper.selectByExample(new UmsMenuExample());
//        List<UmsMenuNode> result = menuList.stream()
//                .filter(menu -> menu.getParentId().equals(0L))
//                .map(menu-> covertMenuNode(menu, menuList))
//                .collect(Collectors.toList());

        List<UmsMenu> firstMenus = new ArrayList<>();
        List<UmsMenuNode> result = new ArrayList<>();
        for (UmsMenu umsMenu : menuList) {
            if (umsMenu.getParentId() == 0L) {
                firstMenus.add(umsMenu);
            }
        }
        for (UmsMenu firstMenu : firstMenus) {
            UmsMenuNode firstNode = covertMenuNode(firstMenu, menuList);
            result.add(firstNode); 
        }
        return result;
    }

    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> childrenList = new ArrayList<>();
        for (UmsMenu subMenu : menuList) {
            if (subMenu.getParentId().equals(menu.getId())) {
                UmsMenuNode children = covertMenuNode(subMenu, menuList);
                childrenList.add(children);
            }
        }
        node.setChildren(childrenList);
        return node;
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

//    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList) {
//        UmsMenuNode node = new UmsMenuNode();
//        BeanUtils.copyProperties(menu, node);
//        List<UmsMenuNode> children = menuList.stream()
//                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
//                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
//        node.setChildren(children);
//        return node;
//}
    
}
