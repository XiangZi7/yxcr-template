package com.yxcr.admin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.RoleMenuDto;
import com.yxcr.bean.mapper.RoleMenuPermissionsMapper;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.pojo.RoleMenuPermissions;
import com.yxcr.bean.pojo.Roles;
import com.yxcr.bean.pojo.Users;
import com.yxcr.bean.service.MenuService;
import com.yxcr.bean.service.RoleMenuPermissionsService;
import com.yxcr.bean.service.RolesService;
import com.yxcr.bean.service.UsersService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@AllArgsConstructor
@Tag(name = "登录")
@RequestMapping("/admin")
public class AdminLoginController {

    private final UsersService usersService;

    private final RolesService rolesService;

    private final MenuService menuService;

    private final RoleMenuPermissionsService roleMenuPermissionsService;

    @Resource
    private RoleMenuPermissionsMapper roleMenuPermissionsMapper;


    @GetMapping("/adminLogin")
    @Operation(summary = "账号密码 + 验证码登录(用于后台登录)", description = "通过账号/手机号/用户名密码登录")
    public ApiResult<?> login(String name, String pwd) {

        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(name) && "123456".equals(pwd)) {
            StpUtil.login(pwd);
            return ApiResult.ok(StpUtil.getTokenValue());
        }
        return ApiResult.error("登录失败");
    }

    @SaCheckPermission("user:add")
    @GetMapping("/check")
    @Operation(summary = "账号密码 + 验证码登录(用于后台登录)", description = "通过账号/手机号/用户名密码登录")
    public ApiResult<?> check() {

        List<Roles> list = rolesService.list();
        List<Users> list1 = usersService.list();

        HashMap<Object, Object> map = new HashMap<>();
        map.put("test1", list);
        map.put("test2", list1);
        map.put("test3", StpUtil.getPermissionList());

        return ApiResult.ok(map);
    }

    @GetMapping("/logout")
    public ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.ok("退出成功");
    }

    @GetMapping("/permission")
    public ApiResult<?> permission() {
        List<Menu> menuList = MenuUtils.getMenuTree(menuService.list());
        return ApiResult.ok(menuList);
    }

    @GetMapping("/userPermission")
    public ApiResult<?> userPermission(int roleId) {
        RoleMenuDto roleWithMenus = rolesService.getRoleWithMenus(roleId);
        return ApiResult.ok(roleWithMenus);
    }

    @PostMapping("/updateRole")
    public ApiResult<?> updateRole(@RequestBody RoleMenuDto roleMenuDto) {
        //清除旧的权限
        roleMenuPermissionsService.removeById(roleMenuDto.getRoleId());
        //新增新的权限
        roleMenuDto.getMenuList().stream().forEach(item -> {
            RoleMenuPermissions menuPermissions = new RoleMenuPermissions();
            menuPermissions.setMenuId(item);
            menuPermissions.setRoleId(roleMenuDto.getRoleId());
            roleMenuPermissionsService.save(menuPermissions);
        });
        return ApiResult.ok(true);
    }
}
