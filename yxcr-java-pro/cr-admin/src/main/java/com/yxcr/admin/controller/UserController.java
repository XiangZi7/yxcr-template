package com.yxcr.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.UserDto;
import com.yxcr.bean.dto.UserListDto;
import com.yxcr.bean.mapper.UsersMapper;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.service.MenuService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "用户管理")
@RequestMapping("/user")
public class UserController {

    @Resource
    UsersMapper usersMapper;

    @GetMapping("/list")
    @Operation(summary = "用户列表", description = "用户列表")
    public ApiResult<?> list(@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum,
                             @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize, UserListDto userListDto) {
        Page<UserDto> page = new Page<>(pageNum, pageSize);
        IPage<UserDto> userDtoIPage = usersMapper.userList(page,userListDto);
        return ApiResult.ok(userDtoIPage);
    }
}
