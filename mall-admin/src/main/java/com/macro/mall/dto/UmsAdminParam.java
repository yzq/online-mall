package com.macro.mall.dto;

import lombok.Data;

@Data
public class UmsAdminParam {
    
    private String username;
    private String nickname;
    private String email;
    private String password;
    private String note;
    private boolean status;
}
