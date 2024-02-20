package com.yxcr.bean.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxcr.bean.dto.UserDto;
import com.yxcr.bean.dto.UserListDto;
import com.yxcr.bean.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【yx_sys_users(用户信息表)】的数据库操作Mapper
 * @createDate 2024-01-30 11:14:13
 * @Entity com.yxcr.bean.pojo.Users
 */
public interface UsersMapper extends BaseMapper<Users> {

    IPage<UserDto> userList(IPage<UserDto> page, @Param("params") UserListDto params);
}




