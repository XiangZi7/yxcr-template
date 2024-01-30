package com.yxcr.bean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxcr.bean.pojo.UserRoles;
import com.yxcr.bean.service.UserRolesService;
import com.yxcr.bean.mapper.UserRolesMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【yx_sys_user_roles(用户与角色关联表)】的数据库操作Service实现
* @createDate 2024-01-30 11:14:13
*/
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles>
    implements UserRolesService{

}




