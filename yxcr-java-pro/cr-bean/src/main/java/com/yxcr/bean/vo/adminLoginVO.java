package com.yxcr.bean.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class adminLoginVO {
    private Integer id;
    private String token;
    private String username;
    private String avatar;
    private String email;
    @JsonIgnore
    private Integer roleId;
}
