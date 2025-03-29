package cn.hengzq.orange.system.core.biz.menu.controller;


import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuDetailVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.AddMenuParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.MenuListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.UpdateMenuParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 菜单管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/menu")
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "新建", operationId = "system:menu:add")
    @PostMapping
    public Result<String> add(@RequestBody @Validated AddMenuParam param) {
        return ResultWrapper.ok(menuService.add(param));
    }

    @Operation(summary = "根据ID删除", operationId = "system:menu:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") String id) {
        return ResultWrapper.ok(menuService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system:menu:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") String id, @RequestBody @Validated UpdateMenuParam param) {
        return ResultWrapper.ok(menuService.updateById(id, param));
    }

    @Operation(summary = "根据ID查询", operationId = "system:menu:get")
    @GetMapping("/{id}")
    public Result<MenuVO> getById(@PathVariable("id") String id) {
        return ResultWrapper.ok(menuService.getById(id));
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system:menu:list", description = "返回所有的数据")
    public Result<List<MenuDetailVO>> list(@RequestBody MenuListParam param) {
        List<MenuDetailVO> list = menuService.list(param);
        return ResultWrapper.ok(list);
    }
}
