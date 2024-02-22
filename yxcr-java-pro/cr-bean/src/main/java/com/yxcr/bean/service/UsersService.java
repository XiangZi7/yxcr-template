package com.yxcr.bean.service;

import com.yxcr.bean.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yxcr.bean.vo.adminLoginVO;
import com.yxcr.common.model.NotLogException;

/**
* @author Administrator
* @description 针对表【yx_sys_users(用户信息表)】的数据库操作Service
* @createDate 2024-01-30 11:14:13
*/
public interface UsersService extends IService<Users> {

   adminLoginVO adminLogin(String phone,String passowrd) throws NotLogException;

}
