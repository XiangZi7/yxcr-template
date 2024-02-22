package com.yxcr.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.*;
import com.yxcr.bean.mapper.RoleMenuPermissionsMapper;
import com.yxcr.bean.mapper.RolesMapper;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.pojo.RoleMenuPermissions;
import com.yxcr.bean.pojo.Roles;
import com.yxcr.bean.service.MenuService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yxcr.common.utils.textUtils.getLastPartOfUrl;

@RestController
@AllArgsConstructor
@Tag(name = "系统设置")
@RequestMapping("/system")
public class SystemController {

    private final MenuService menuService;
    @Resource
    private RolesMapper rolesMapper;
    @Resource
    private RoleMenuPermissionsMapper roleMenuPermissionsMapper;


    @GetMapping("/table")
    @Operation(summary = "菜单列表", description = "菜单列表")
    public ApiResult<?> permission() {
        List<Menu> menuTreeTable = MenuUtils.getMenuTreeTable(menuService.list());
        return ApiResult.ok(menuTreeTable);
    }

    @PostMapping("/addMenu")
    @Operation(summary = "菜单新增", description = "菜单新增")
    public ApiResult<?> addMenu(@RequestBody Menu menu) {
        if (menu.getId() == -1) {
            menu.setId(null);
        } else {
            menu.setParentId(menu.getId());
            menu.setId(null);
        }

        String lastName = getLastPartOfUrl(menu.getPath());
        menu.setName(lastName);
        menuService.save(menu);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/editMenu")
    @Operation(summary = "菜单更改", description = "菜单更改")
    public ApiResult<?> editMenu(@RequestBody Menu menu) {
        String lastName = getLastPartOfUrl(menu.getPath());
        menu.setName(lastName);
        menuService.updateById(menu);
        return ApiResult.ok("编辑成功");
    }

    @PostMapping("/deleteMenu")
    @Operation(summary = "菜单删除", description = "菜单删除")
    public ApiResult<?> menuDelete(@RequestBody IdDTO idDTO) {
        menuService.removeByIds(idDTO.getId());
        return ApiResult.ok("删除成功");
    }

    @GetMapping("/tables")
    @Operation(summary = "菜单列表", description = "菜单列表")
    public ApiResult<?> tables() {
        List<Menu> menuList = menuService.list();
        return ApiResult.ok(menuList);
    }

    @GetMapping("/roleList")
    @Operation(summary = "角色列表", description = "角色列表")
    public ApiResult<?> roleList(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize,
                                 Roles roles) {
        Page<UserDto> page = new Page<>(pageNum, pageSize);
        IPage<Roles> rolesIPage = rolesMapper.selectRoleAll(page, roles);
        return ApiResult.ok(rolesIPage);
    }

    @PostMapping("/addRole")
    @Operation(summary = "添加角色", description = "添加角色")
    public ApiResult<?> addRole(@RequestBody RoleAddRequest addRequest) {
        Roles roles = addRequest.getRoles();
        List<Integer> idList = addRequest.getIdList();
        rolesMapper.insert(roles);
        RoleMenuPermissions permissions = new RoleMenuPermissions();

        permissions.setRoleId(roles.getId());
        if (idList.size() > 0) {
            idList.stream().forEach(item -> {
                permissions.setMenuId(item);
                roleMenuPermissionsMapper.insert(permissions);
            });
        }
        return ApiResult.ok("添加成功");
    }

    @PostMapping("/editRole")
    @Operation(summary = "编辑角色", description = "编辑角色")
    public ApiResult<?> editRole(@RequestBody RoleAddRequest addRequest) {
        Roles roles = addRequest.getRoles();
        List<Integer> idList = addRequest.getIdList();
        rolesMapper.updateById(roles);

        roleMenuPermissionsMapper.deleteByRoleId(roles.getId());

        RoleMenuPermissions permissions = new RoleMenuPermissions();
        permissions.setRoleId(roles.getId());
        if (idList.size() > 0) {
            idList.stream().forEach(item -> {
                permissions.setMenuId(item);
                roleMenuPermissionsMapper.insert(permissions);
            });
        }
        return ApiResult.ok("编辑成功");
    }

    @PostMapping("/deleteRole")
    @Operation(summary = "删除角色", description = "删除角色")
    public ApiResult<?> deleteRole(@RequestBody IdDTO idDTO) {
        rolesMapper.deleteBatchIds(idDTO.getId());
        return ApiResult.ok("删除成功");
    }

    @GetMapping("/role/info/{roleId}")
    @Operation(summary = "角色权限列表", description = "角色权限列表")
    public ApiResult<?> roleList(@PathVariable Integer roleId) {
        List<Integer> integers = roleMenuPermissionsMapper.selectMenuIdByRoleId(roleId);
        return ApiResult.ok(integers);
    }
}
