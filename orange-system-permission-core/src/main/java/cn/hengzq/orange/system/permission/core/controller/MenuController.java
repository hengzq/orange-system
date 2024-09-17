package cn.hengzq.orange.system.permission.core.controller;


import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.permission.core.service.MenuService;
import cn.hengzq.orange.system.permission.common.constant.PermissionConstant;
import cn.hengzq.orange.system.permission.common.vo.menu.MenuDetailVO;
import cn.hengzq.orange.system.permission.common.vo.menu.MenuVO;
import cn.hengzq.orange.system.permission.common.vo.menu.param.AddMenuParam;
import cn.hengzq.orange.system.permission.common.vo.menu.param.MenuListParam;
import cn.hengzq.orange.system.permission.common.vo.menu.param.UpdateMenuParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "菜单管理")
@RestController
@AllArgsConstructor
@RequestMapping(PermissionConstant.V1_0_URL_PREFIX + "/menu")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "新建", operationId = "system-permission:menu:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated AddMenuParam param) {
        return ResultWrapper.ok(menuService.add(param));
    }

    @Operation(summary = "根据ID删除", operationId = "system-permission:menu:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(menuService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system-permission:menu:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody @Validated UpdateMenuParam param) {
        return ResultWrapper.ok(menuService.updateById(id, param));
    }

    @Operation(summary = "根据ID查询", operationId = "system-permission:menu:get")
    @GetMapping("/{id}")
    public Result<MenuVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(menuService.getById(id));
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system-permission:menu:list", description = "返回所有的数据")
    public Result<List<MenuDetailVO>> list(@RequestBody MenuListParam param) {
        List<MenuDetailVO> list = menuService.list(param);
        return ResultWrapper.ok(list);
    }
}
