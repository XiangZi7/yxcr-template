package com.yxcr.admin.controller;

import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.MenuDto;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.service.MenuService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "系统设置")
@RequestMapping("/system")
public class SystemController {

    private final MenuService menuService;
    @GetMapping("/table")
    public ApiResult<?> permission() {
        List<Menu> menuTreeTable = MenuUtils.getMenuTreeTable(menuService.list());
        return ApiResult.ok(menuTreeTable);
    }
}
