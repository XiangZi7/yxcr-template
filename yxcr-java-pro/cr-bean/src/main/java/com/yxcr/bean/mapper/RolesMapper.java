package com.yxcr.bean.mapper;

import com.yxcr.bean.dto.RoleMenuDto;
import com.yxcr.bean.pojo.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【yx_sys_roles(角色信息表)】的数据库操作Mapper
* @createDate 2024-01-30 11:14:13
* @Entity com.yxcr.bean.pojo.Roles
*/
public interface RolesMapper extends BaseMapper<Roles> {
    // 定义获取角色和对应菜单ID列表的方法
    RoleMenuDto selectRoleWithMenus(@Param("roleId") Integer roleId);
}




