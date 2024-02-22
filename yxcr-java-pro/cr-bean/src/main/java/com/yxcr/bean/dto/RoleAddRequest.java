package com.yxcr.bean.dto;

import com.yxcr.bean.pojo.Roles;
import lombok.Data;

import java.util.List;

@Data
public class RoleAddRequest {
    private Roles roles;
    private List<Integer> idList;
}
