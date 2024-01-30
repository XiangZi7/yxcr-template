package com.yxcr.bean.mapper;
import org.apache.ibatis.annotations.Param;

import com.yxcr.bean.pojo.RoleMenuPermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Administrator
* @description 针对表【yx_sys_role_menu_permissions(角色与菜单权限关联表)】的数据库操作Mapper
* @createDate 2024-01-30 11:14:13
* @Entity com.yxcr.bean.pojo.RoleMenuPermissions
*/
public interface RoleMenuPermissionsMapper extends BaseMapper<RoleMenuPermissions> {
    int deleteByRoleId(@Param("roleId") Integer roleId);
}




