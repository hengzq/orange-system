package cn.hengzq.orange.system.permission.core.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.permission.core.service.RoleService;
import cn.hengzq.orange.system.permission.common.constant.PermissionConstant;
import cn.hengzq.orange.system.permission.common.vo.role.RoleDetailVO;
import cn.hengzq.orange.system.permission.common.vo.role.RoleVO;
import cn.hengzq.orange.system.permission.common.vo.role.param.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "角色管理")
@RestController
@AllArgsConstructor
@RequestMapping(PermissionConstant.V1_0_URL_PREFIX + "/role")
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "新建", operationId = "system-permission:role:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated AddRoleParam request) {
        return ResultWrapper.ok(roleService.add(request));
    }

    @Operation(summary = "根据ID删除", operationId = "system-permission:role:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(roleService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system-permission:role:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody @Validated UpdateRoleParam request) {
        return ResultWrapper.ok(roleService.updateById(id, request));
    }

    @Operation(summary = "根据ID查询", operationId = "system-permission:role:get")
    @GetMapping("/{id}")
    public Result<RoleDetailVO> getById(@PathVariable("id") Long id, @ParameterObject RoleDetailQueryParam param) {
        return ResultWrapper.ok(roleService.getById(id, param));
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system-permission:role:list", description = "返回所有的数据")
    public Result<List<RoleVO>> list(@RequestBody RoleListParam param) {
        List<RoleVO> list = roleService.list(param);
        return ResultWrapper.ok(list);
    }

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询", operationId = "system-permission:role:page")
    public Result<PageDTO<RoleVO>> page(@RequestBody RolePageParam param) {
        PageDTO<RoleVO> list = roleService.page(param);
        return ResultWrapper.ok(list);
    }


}
