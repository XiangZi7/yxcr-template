package com.yxcr.admin.utils;

import java.util.List;
import java.util.stream.Collectors;
import com.yxcr.bean.pojo.Menu;

public class MenuUtils {
    /**
     * 构建树形菜单结构
     * @param allMenus 所有菜单列表
     * @return 树形结构的菜单列表
     */
    public static List<Menu> getMenuTree(List<Menu> allMenus) {
        return allMenus.stream()
                .filter(menu -> menu.getParentId() == null) // 找出所有顶级节点，即父ID为null的节点
                .peek(menu -> setChildren(menu, allMenus))  // 为每个顶级节点递归设置子节点
                .collect(Collectors.toList());              // 将结构化后的菜单收集为列表
    }
    /**
     * 为给定的父菜单设置子菜单列表
     * @param parent    父菜单
     * @param allMenus  所有菜单列表
     */
    private static void setChildren(Menu parent, List<Menu> allMenus) {
        List<Menu> children = allMenus.stream()
                .filter(menu -> parent.getId().equals(menu.getParentId())) // 找出当前父节点的所有直接子节点
                .peek(childMenu -> setChildren(childMenu, allMenus))        // 递归设置当前子节点的子节点
                .collect(Collectors.toList());                              // 收集当前节点的所有子节点

        parent.setChildren(children); // 将找到的子节点设置为当前父节点的子节点
    }
}
