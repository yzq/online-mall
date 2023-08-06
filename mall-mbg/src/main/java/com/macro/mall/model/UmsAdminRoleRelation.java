package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class UmsAdminRoleRelation implements Serializable {
    private Long id;

    private Long umsAdminId;

    private Long umsRoleId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUmsAdminId() {
        return umsAdminId;
    }

    public void setUmsAdminId(Long umsAdminId) {
        this.umsAdminId = umsAdminId;
    }

    public Long getUmsRoleId() {
        return umsRoleId;
    }

    public void setUmsRoleId(Long umsRoleId) {
        this.umsRoleId = umsRoleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", umsAdminId=").append(umsAdminId);
        sb.append(", umsRoleId=").append(umsRoleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}