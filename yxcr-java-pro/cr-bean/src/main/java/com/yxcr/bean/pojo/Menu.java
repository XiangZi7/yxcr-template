package com.yxcr.bean.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.ibatis.mapping.FetchType;

/**
 * 后台管理系统动态菜单表
 *
 * @TableName yx_sys_menu
 */
@TableName(value = "yx_sys_menu")
@Data
public class Menu implements Serializable {
    /**
     * 主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID，顶级菜单为NULL
     */
    private Integer parentId;

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单路由路径
     */
    private String path;
    /**
     * 菜单路由name
     */
    private String name;
    /**
     * 前端Vue组件路径
     */
    private String component;

    /**
     * 菜单排序顺序
     */
    private Integer orderNum;

    /**
     * 菜单是否可见, 1为可见，0为不可见
     */
    private Integer visible;

    /**
     * 权限标识，用于权限控制
     */
    @TableField(value = "permission")
    private String permission;


    // 自关联，一个菜单可以有多个子菜单
    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();

    // 自关联，一个菜单只有一个父菜单
    @JsonIgnore
    @TableField(exist = false)
    private Menu parent;
    /**
     * 当前路由是否缓存 0：否 1：是
     */
    private int isKeepAlive;
    /**
     * 路由重定向地址
     */
    private String redirect;
    /**
     * 是否全屏
     */
    private int isFull;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
                && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
                && (this.getComponent() == null ? other.getComponent() == null : this.getComponent().equals(other.getComponent()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
                && (this.getVisible() == null ? other.getVisible() == null : this.getVisible().equals(other.getVisible()))
                && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getVisible() == null) ? 0 : getVisible().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", title=").append(title);
        sb.append(", icon=").append(icon);
        sb.append(", path=").append(path);
        sb.append(", component=").append(component);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", visible=").append(visible);
        sb.append(", permission=").append(permission);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}