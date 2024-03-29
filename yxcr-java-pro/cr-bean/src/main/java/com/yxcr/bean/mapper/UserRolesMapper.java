package com.yxcr.bean.mapper;

import com.yxcr.bean.pojo.UserRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【yx_sys_user_roles(用户与角色关联表)】的数据库操作Mapper
* @createDate 2024-01-30 11:14:13
* @Entity com.yxcr.bean.pojo.UserRoles
*/
public interface UserRolesMapper extends BaseMapper<UserRoles> {

    //    检查用户是否管理员
    int checkIfUserIsAdmin(@Param("userId") Integer userId);
}




