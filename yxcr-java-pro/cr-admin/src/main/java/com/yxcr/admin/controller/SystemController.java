package com.yxcr.admin.controller;

import com.yxcr.admin.utils.MenuUtils;
import com.yxcr.bean.dto.IdDTO;
import com.yxcr.bean.pojo.Menu;
import com.yxcr.bean.service.MenuService;
import com.yxcr.common.model.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yxcr.common.utils.textUtils.getLastPartOfUrl;

@RestController
@AllArgsConstructor
@Tag(name = "系统设置")
@RequestMapping("/system")
public class SystemController {
    private static final Integer NEW_MENU_ID = -1;

    private final MenuService menuService;

    @GetMapping("/table")
    @Operation(summary = "菜单列表", description = "菜单列表")
    public ApiResult<?> permission() {
        List<Menu> menuTreeTable = MenuUtils.getMenuTreeTable(menuService.list());
        return ApiResult.ok(menuTreeTable);
    }

    @PostMapping("/menu")
    @Operation(summary = "菜单新增或更改", description = "菜单新增或更改")
    public ApiResult<?> menu(@RequestBody Menu menu) {
        String lastName = getLastPartOfUrl(menu.getPath());
        menu.setName(lastName);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/menuDelete")
    @Operation(summary = "菜单删除", description = "菜单删除")
    public ApiResult<?> menuDelete(@RequestBody IdDTO idDTO) {
        menuService.removeByIds(idDTO.getId());
        return ApiResult.ok("删除成功");
    }
}
