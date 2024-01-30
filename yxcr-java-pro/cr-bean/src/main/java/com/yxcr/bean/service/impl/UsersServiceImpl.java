package com.yxcr.bean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxcr.bean.pojo.Users;
import com.yxcr.bean.service.UsersService;
import com.yxcr.bean.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【yx_sys_users(用户信息表)】的数据库操作Service实现
* @createDate 2024-01-30 11:14:13
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




