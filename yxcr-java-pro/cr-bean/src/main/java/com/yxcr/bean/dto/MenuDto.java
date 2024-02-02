package com.yxcr.bean.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuDto {
    private Integer id;
    private Integer parentId;
    private List<MenuDto> children;
    private Meta meta;
    private String name;
    private String component;
    private String path;
    private String redirect;
    @Data
    public static class Meta {
        private String icon;
        private String title;
        private int visible;
        private int isFull;
        private int orderNum;
        private int isKeepAlive;
    }
}
