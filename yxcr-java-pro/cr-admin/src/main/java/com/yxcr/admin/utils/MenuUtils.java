package com.yxcr.admin.utils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.yxcr.bean.dto.MenuDto;
import com.yxcr.bean.pojo.Menu;

public class MenuUtils {
    /**
     * 将 Menu 封装为 MenuDto 树形结构
     * @param allMenus 所有菜单列表
     * @return MenuDto 树形结构列表
     */
    public static List<MenuDto> getMenuTree(List<Menu> allMenus) {
        // 首先过滤出顶级菜单并为它们设置子菜单
        return allMenus.stream()
                .filter(menu -> menu.getParentId() == null) // 找出所有顶级节点
                .map(menu -> convertToMenuDtoWithChildren(menu, allMenus)) // 转换为 MenuDto 且设置子节点
                .collect(Collectors.toList()); // 收集到 List 中
    }

    /**
     * 为给定的菜单设置子菜单列表并转换为 MenuDto 对象，该方法是递归的
     * @param parent    父菜单
     * @param allMenus  所有菜单列表
     * @return MenuDto 对象，其 children 字段包含了所有转换后的子菜单
     */
    private static MenuDto convertToMenuDtoWithChildren(Menu parent, List<Menu> allMenus) {
        MenuDto dto = convertToMenuDto(parent); // 转换父菜单
        List<MenuDto> childDtos = allMenus.stream()
                .filter(menu -> parent.getId().equals(menu.getParentId())) // 只找出当前父节点的直接子节点
                .map(childMenu -> convertToMenuDtoWithChildren(childMenu, allMenus)) // 递归设置子节点
                .collect(Collectors.toList()); // 收集子节点
        dto.setChildren(childDtos); // 将子节点列表设置为 dto 的 children 字段
        return dto;
    }
    // 将 Menu 对象递归转换为 MenuDto 对象
    private static MenuDto convertToMenuDto(Menu menu) {
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setParentId(menu.getParentId());
        dto.setName(menu.getName());
        dto.setPath(menu.getPath());
        dto.setRedirect(menu.getRedirect());
        dto.setComponent(menu.getComponent());

        // 创建 Meta 对象，并填充数据
        MenuDto.Meta meta = new MenuDto.Meta();
        meta.setIcon(menu.getIcon());
        meta.setTitle(menu.getTitle());
        meta.setIsKeepAlive(menu.getIsKeepAlive()); // 设定一个适当的值或根据实际业务逻辑决定
        meta.setVisible(menu.getVisible());
        meta.setIsFull(menu.getIsFull());
        // getOrderNum 可能为 null，提供默认值 (例如 0 或其他合适的值)
        meta.setOrderNum(menu.getOrderNum() != null ? menu.getOrderNum() : 0);
        // 加入更多的 Meta 属性填充，如有必要
        dto.setMeta(meta); // 这里假设每个MenuDto有单个Meta

        // 递归设置子菜单
        if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
            List<MenuDto> childDtos = menu.getChildren().stream()
                    .map(MenuUtils::convertToMenuDto)
                    .collect(Collectors.toList());
            dto.setChildren(childDtos);
        }

        return dto;
    }
    /**
     * 构建树形菜单结构
     * @param allMenus 所有菜单列表
     * @return 树形结构的菜单列表
     */
    public static List<Menu> getMenuTreeTable(List<Menu> allMenus) {
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
