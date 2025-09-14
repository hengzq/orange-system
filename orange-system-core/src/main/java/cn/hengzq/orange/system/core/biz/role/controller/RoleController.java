package cn.hengzq.orange.system.core.biz.role.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.response.ApiResponse;
import cn.hengzq.orange.system.common.biz.role.dto.RoleDetailResponse;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleCreateRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RolePageRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleQueryRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleUpdateRequest;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.role.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 角色管理", description = "角色相关的增删改查操作")
@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/roles")
public class RoleController {

    private final RoleService roleService;

    @Operation(summary = "创建角色", description = "创建一个新的系统角色", operationId = "system:role:create")
    @PostMapping
    public ApiResponse<String> createRole(@RequestBody @Validated RoleCreateRequest request) {
        return ApiResponse.ok(roleService.createRole(request));
    }

    @Operation(summary = "删除角色", description = "根据角色ID删除指定的角色", operationId = "system:role:delete")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteRoleById(@PathVariable("id") String id) {
        roleService.deleteRoleById(id);
        return ApiResponse.ok();
    }

    @Operation(summary = "更新角色信息", description = "根据角色ID更新角色信息", operationId = "system:role:update")
    @PutMapping("/{id}")
    public ApiResponse<Boolean> updateRoleById(@PathVariable("id") String id, @RequestBody @Validated RoleUpdateRequest request) {
        return ApiResponse.ok(roleService.updateRoleById(id, request));
    }

    @Operation(summary = "根据ID查询", operationId = "system:role:get")
    @GetMapping("/{id}")
    public ApiResponse<RoleResponse> getRoleById(@PathVariable("id") String id) {
        Optional<RoleResponse> roleResponse = roleService.getRoleById(id);
        return ApiResponse.ok(roleResponse.orElse(null));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:role:get-detail")
    @GetMapping("/{id}/detail")
    public ApiResponse<RoleDetailResponse> getRoleDetailById(@PathVariable("id") String id) {
        return ApiResponse.ok(roleService.getRoleDetailById(id));
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有角色", description = "根据条件查询所有角色信息", operationId = "system:role:list-all")
    public ApiResponse<List<RoleResponse>> listRoles(@RequestBody RoleQueryRequest request) {
        List<RoleResponse> list = roleService.listRoles(request);
        return ApiResponse.ok(list);
    }

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询角色", description = "根据条件分页查询角色信息", operationId = "system:role:page")
    public ApiResponse<PageDTO<RoleResponse>> pageRoles(@RequestBody RolePageRequest param) {
        PageDTO<RoleResponse> list = roleService.pageRoles(param);
        return ApiResponse.ok(list);
    }
}
