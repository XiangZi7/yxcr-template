package com.yxcr.bean.service;

import com.yxcr.bean.dto.RoleMenuDto;
import com.yxcr.bean.pojo.Roles;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【yx_sys_roles(角色信息表)】的数据库操作Service
* @createDate 2024-01-30 11:14:13
*/
public interface RolesService extends IService<Roles> {
    RoleMenuDto getRoleWithMenus(Integer roleId);

}
