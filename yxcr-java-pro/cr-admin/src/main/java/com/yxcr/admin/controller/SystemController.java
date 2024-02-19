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

    private final MenuService menuService;

    @GetMapping("/table")
    @Operation(summary = "菜单列表", description = "菜单列表")
    public ApiResult<?> permission() {
        List<Menu> menuTreeTable = MenuUtils.getMenuTreeTable(menuService.list());
        return ApiResult.ok(menuTreeTable);
    }

    @PostMapping("/addMenu")
    @Operation(summary = "菜单新增", description = "菜单新增")
    public ApiResult<?> addMenu(@RequestBody Menu menu) {
        if (menu.getId() == -1) {
            menu.setId(null);
        } else {
            menu.setParentId(menu.getId());
            menu.setId(null);
        }

        String lastName = getLastPartOfUrl(menu.getPath());
        menu.setName(lastName);
        menuService.save(menu);
        return ApiResult.ok("新增成功");
    }

    @PostMapping("/editMenu")
    @Operation(summary = "菜单更改", description = "菜单更改")
    public ApiResult<?> editMenu(@RequestBody Menu menu) {
        String lastName = getLastPartOfUrl(menu.getPath());
        menu.setName(lastName);
        menuService.updateById(menu);
        return ApiResult.ok("编辑成功");
    }

    @PostMapping("/deleteMenu")
    @Operation(summary = "菜单删除", description = "菜单删除")
    public ApiResult<?> menuDelete(@RequestBody IdDTO idDTO) {
        menuService.removeByIds(idDTO.getId());
        return ApiResult.ok("删除成功");
    }
}
