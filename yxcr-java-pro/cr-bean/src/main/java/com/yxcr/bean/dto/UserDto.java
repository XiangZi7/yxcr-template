package com.yxcr.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int id;
    private String username;
    private String avatar;
    private String email;
    private String phone;
    private int status;
    private Date createTime;
    private Date updateTime;
    private Boolean isDelete;
    private String roleName; // 用户的角色名称
}
