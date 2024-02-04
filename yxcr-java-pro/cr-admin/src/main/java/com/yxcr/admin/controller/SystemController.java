package com.yxcr.admin.controller;

import com.yxcr.admin.utils.MenuUtils;
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

    @PostMapping("/menu")
    @Operation(summary = "菜单新增或更改", description = "菜单新增或更改")
    public ApiResult<?> menu(@RequestBody Menu menu) {
        String lastName = getLastPartOfUrl(menu.getPath());
        menu.setName(lastName);
        if (menu.getId() == null) {
            menuService.save(menu);
            return ApiResult.ok("新增成功");
        }else{
            menu.setParentId(menu.getId());
            menu.setId(null);
            menuService.save(menu);
            return ApiResult.ok("新增失败");
        }
    }
}
