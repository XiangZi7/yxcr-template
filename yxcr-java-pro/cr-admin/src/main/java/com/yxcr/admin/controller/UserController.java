package com.yxcr.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.IdDTO;
import com.yxcr.bean.dto.UserDto;
import com.yxcr.bean.dto.UserListDto;
import com.yxcr.bean.mapper.UsersMapper;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.pojo.Roles;
import com.yxcr.bean.pojo.UserRoles;
import com.yxcr.bean.pojo.Users;
import com.yxcr.bean.service.MenuService;
import com.yxcr.bean.service.UserRolesService;
import com.yxcr.bean.service.UsersService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UsersService usersService;
    @Resource
    private UserRolesService userRolesService;

    @GetMapping("/list")
    @Operation(summary = "用户列表", description = "用户列表")
    public ApiResult<?> list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, UserListDto userListDto) {
        Page<UserDto> page = new Page<>(pageNum, pageSize);
        IPage<UserDto> userDtoIPage = usersMapper.userList(page, userListDto);
        return ApiResult.ok(userDtoIPage);
    }

    @PostMapping("/add")
    @Operation(summary = "用户添加", description = "用户添加")
    public ApiResult<?> add(@RequestBody Users users) {
        usersService.save(users);
        // 如果条目不存在，创建新的用户角色关系
        UserRoles newUserRoles = new UserRoles();
        newUserRoles.setRoleId(users.getRoleId());
        newUserRoles.setUserId(users.getId());
        userRolesService.save(newUserRoles); // 使用 save 方法来新增记录
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/edit")
    @Operation(summary = "用户编辑", description = "用户编辑")
    public ApiResult<?> edit(@RequestBody Users users) {
        // 更新用户信息
        usersService.updateById(users);

        // 尝试查询现存的 UserRoles 条目
        UserRoles existingUserRoles = userRolesService.getById(users.getId());

        if (existingUserRoles != null) {
            // 如果条目存在，更新角色 ID
            existingUserRoles.setRoleId(users.getRoleId());
            userRolesService.updateById(existingUserRoles);
        } else {
            // 如果条目不存在，创建新的用户角色关系
            UserRoles newUserRoles = new UserRoles();
            newUserRoles.setRoleId(users.getRoleId());
            newUserRoles.setUserId(users.getId());
            userRolesService.save(newUserRoles); // 使用 save 方法来新增记录
        }

        return ApiResult.ok("编辑成功");
    }

    @PostMapping("/delete")
    @Operation(summary = "用户删除", description = "用户删除")
    public ApiResult<?> delete(@RequestBody IdDTO idDTO) {
        usersService.removeByIds(idDTO.getId());
        return ApiResult.ok("删除成功");
    }

    @PostMapping("/RefreshPwd/{id}")
    @Operation(summary = "重置用户密码", description = "重置用户密码")
    public ApiResult<?> RefreshPwd(@PathVariable Integer id) {
        usersMapper.RefreshPwd(id);
        return ApiResult.ok("重置用户密码成功");
    }
}
