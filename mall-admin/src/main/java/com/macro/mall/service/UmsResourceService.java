package com.macro.mall.service;

import com.macro.mall.model.UmsResource;

public interface UmsResourceService {
    int create(UmsResource umsResource);

    int update(Long id, UmsResource umsResource);
}
