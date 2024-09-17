package cn.hengzq.orange.system.permission.core.controller;


import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.permission.core.service.ButtonService;
import cn.hengzq.orange.system.permission.common.constant.PermissionConstant;
import cn.hengzq.orange.system.permission.common.vo.button.ButtonVO;
import cn.hengzq.orange.system.permission.common.vo.button.param.AddButtonParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.ButtonListParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.UpdateButtonParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "按钮管理")
@RestController
@AllArgsConstructor
@RequestMapping(PermissionConstant.V1_0_URL_PREFIX + "/button")
public class ButtonController {

    private final ButtonService buttonService;

    @Operation(summary = "新建", operationId = "system-permission:button:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated AddButtonParam param) {
        return ResultWrapper.ok(buttonService.add(param));
    }

    @Operation(summary = "根据ID删除", operationId = "system-permission:button:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(buttonService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system-permission:button:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody @Validated UpdateButtonParam param) {
        return ResultWrapper.ok(buttonService.updateById(id, param));
    }

    @Operation(summary = "根据ID查询", operationId = "system-permission:button:get")
    @GetMapping("/{id}")
    public Result<ButtonVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(buttonService.getById(id));
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system-permission:button:list", description = "返回所有的数据")
    public Result<List<ButtonVO>> list(@RequestBody ButtonListParam param) {
        List<ButtonVO> list = buttonService.list(param);
        return ResultWrapper.ok(list);
    }
}
