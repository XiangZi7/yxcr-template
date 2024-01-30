package com.yxcr.bean.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class RoleMenuDto {
    private int roleId;
    private String roleName;
    @JsonIgnore
    private String menuLists;
    private List<Integer> menuList;
}
