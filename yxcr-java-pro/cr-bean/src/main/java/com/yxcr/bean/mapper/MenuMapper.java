package com.yxcr.bean.mapper;

import com.yxcr.bean.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【yx_sys_menu(后台管理系统动态菜单表)】的数据库操作Mapper
 * @createDate 2024-01-30 11:14:13
 * @Entity com.yxcr.bean.pojo.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色ID查看该角色的菜单栏数据
     *
     * @param roleId
     * @return
     */
    List<Menu> selectMenuWithPermissions(@Param("roleId") int roleId);
}




