package com.yxcr.bean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxcr.bean.mapper.UserRolesMapper;
import com.yxcr.bean.pojo.Users;
import com.yxcr.bean.service.UsersService;
import com.yxcr.bean.mapper.UsersMapper;
import com.yxcr.bean.vo.adminLoginVO;
import com.yxcr.common.model.NotLogException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.dev33.satoken.stp.StpUtil;

/**
 * @author Administrator
 * @description 针对表【yx_sys_users(用户信息表)】的数据库操作Service实现
 * @createDate 2024-01-30 11:14:13
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
        implements UsersService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private UserRolesMapper userRolesMapper;

    @Override
    public adminLoginVO adminLogin(String phone, String password) throws NotLogException {
        Users users = usersMapper.selectUserByLogin(phone, password);

        System.out.println("users=>>>" + users);
        if (users == null) {
            throw new NotLogException("电话号码或密码错误");
        } else {
            // 这里可以进行进一步的管理员检查
            int count = userRolesMapper.checkIfUserIsAdmin(users.getId());
            if (count == 0) {
                // 若不是管理员，同样抛出异常或者返回特定状态的对象
                throw new NotLogException("无管理员权限");
            }
            // 设置adminLoginVO的属性，比如token，用户名等
            adminLoginVO adminLoginVO = new adminLoginVO();
            StpUtil.login(users.getId());
            adminLoginVO.setUsername(users.getUsername());
            adminLoginVO.setAvatar(users.getAvatar());
            adminLoginVO.setEmail(users.getEmail());
            adminLoginVO.setId(users.getId());
            adminLoginVO.setToken(StpUtil.getTokenValue());
            return adminLoginVO;
        }
    }
}




