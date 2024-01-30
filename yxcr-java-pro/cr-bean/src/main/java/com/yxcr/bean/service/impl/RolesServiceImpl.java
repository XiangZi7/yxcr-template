package com.yxcr.bean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxcr.bean.dto.RoleMenuDto;
import com.yxcr.bean.pojo.Roles;
import com.yxcr.bean.service.RolesService;
import com.yxcr.bean.mapper.RolesMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【yx_sys_roles(角色信息表)】的数据库操作Service实现
 * @createDate 2024-01-30 11:14:13
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles>
        implements RolesService {

    @Resource
    private RolesMapper roleMenuMapper;


    @Override
    public RoleMenuDto getRoleWithMenus(Integer roleId) {
        RoleMenuDto roleMenuDto = roleMenuMapper.selectRoleWithMenus(roleId);
        if (roleMenuDto.getMenuLists() != null) {
            List<Integer> menuList = Arrays.stream(roleMenuDto.getMenuLists().split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            roleMenuDto.setMenuList(menuList);
            System.out.println("menuList=>>>" + menuList);
        }
        return roleMenuDto;
    }
}




