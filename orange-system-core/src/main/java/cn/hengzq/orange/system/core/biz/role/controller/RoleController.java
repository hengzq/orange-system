package cn.hengzq.orange.system.core.biz.role.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.role.vo.RoleDetailVO;
import cn.hengzq.orange.system.common.biz.role.vo.RoleVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.*;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.role.service.RoleService;
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
@Tag(name = "系统 - 角色管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/role")
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "新建", operationId = "system:role:add")
    @PostMapping
    public Result<String> add(@RequestBody @Validated AddRoleParam request) {
        return ResultWrapper.ok(roleService.add(request));
    }

    @Operation(summary = "根据ID删除", operationId = "system:role:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") String id) {
        return ResultWrapper.ok(roleService.removeById(id));
    }

    @Operation(summary = "根据ID更新", operationId = "system:role:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") String id, @RequestBody @Validated UpdateRoleParam request) {
        return ResultWrapper.ok(roleService.updateById(id, request));
    }

    @Operation(summary = "根据ID查询", operationId = "system:role:get")
    @GetMapping("/{id}")
    public Result<RoleDetailVO> getById(@PathVariable("id") String id, @ParameterObject RoleDetailQueryParam param) {
        return ResultWrapper.ok(roleService.getById(id, param));
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system:role:list", description = "返回所有的数据")
    public Result<List<RoleVO>> list(@RequestBody RoleListParam param) {
        List<RoleVO> list = roleService.list(param);
        return ResultWrapper.ok(list);
    }

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询", operationId = "system:role:page")
    public Result<PageDTO<RoleVO>> page(@RequestBody RolePageParam param) {
        PageDTO<RoleVO> list = roleService.page(param);
        return ResultWrapper.ok(list);
    }


}
