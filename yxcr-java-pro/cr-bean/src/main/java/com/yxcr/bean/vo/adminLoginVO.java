package com.yxcr.bean.vo;

import lombok.Data;

@Data
public class adminLoginVO {
    private Integer id;
    private String token;
    private String username;
    private String avatar;
    private String email;
}
