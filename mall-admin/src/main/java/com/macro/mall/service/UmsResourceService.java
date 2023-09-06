package com.macro.mall.service;

import com.macro.mall.model.UmsResource;
import io.swagger.models.auth.In;

import java.util.List;

public interface UmsResourceService {
    int create(UmsResource umsResource);

    int update(Long id, UmsResource umsResource);

    int delete(Long id);

    List<UmsResource> list(String nameKeyword, String urlKeyword, Long categoryId, Integer pageSize, Integer pageNum);

    UmsResource getItem(Long id);

    List<UmsResource> listAll();
}
