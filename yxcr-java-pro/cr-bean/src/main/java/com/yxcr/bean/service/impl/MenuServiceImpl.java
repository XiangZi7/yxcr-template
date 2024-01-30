package com.yxcr.bean.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.service.MenuService;
import com.yxcr.bean.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【yx_sys_menu(后台管理系统动态菜单表)】的数据库操作Service实现
* @createDate 2024-01-30 11:14:13
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




