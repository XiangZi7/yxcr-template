package com.yxcr.admin.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.MenuDto;
import com.yxcr.bean.dto.RoleMenuDto;
import com.yxcr.bean.dto.adminLoginDto;
import com.yxcr.bean.mapper.MenuMapper;
import com.yxcr.bean.mapper.RoleMenuPermissionsMapper;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.pojo.RoleMenuPermissions;
import com.yxcr.bean.pojo.Roles;
import com.yxcr.bean.pojo.Users;
import com.yxcr.bean.service.MenuService;
import com.yxcr.bean.service.RoleMenuPermissionsService;
import com.yxcr.bean.service.RolesService;
import com.yxcr.bean.service.UsersService;
import com.yxcr.bean.vo.adminLoginVO;
import com.yxcr.common.model.ApiResult;
import com.yxcr.common.model.NotLogException;
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
    @Resource
    private MenuMapper menuMapper;

    private final RoleMenuPermissionsService roleMenuPermissionsService;

    @Resource
    private RoleMenuPermissionsMapper roleMenuPermissionsMapper;


    @PostMapping("/login")
    @Operation(summary = "手机号码+密码 (用于后台登录)", description = "通过手机号/密码登录")
    public ApiResult<?> login(@RequestBody adminLoginDto adminLoginDto) throws NotLogException {

        adminLoginVO adminLoginVO = usersService.adminLogin(adminLoginDto.getPhone(), adminLoginDto.getPassword());

        return ApiResult.ok(adminLoginVO);
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

    @PostMapping("/logout")
    public ApiResult<?> logout() {
        StpUtil.logout();
        return ApiResult.ok("退出成功");
    }

    @GetMapping("/permission")
    public ApiResult<?> permission() {
        adminLoginVO user = (adminLoginVO) StpUtil.getSession().get("user");

        List<Menu> menus = menuMapper.selectMenuWithPermissions(user.getRoleId());
        List<MenuDto> menuTree = MenuUtils.getMenuTree(menus);
        return ApiResult.ok(menuTree);
    }
}
