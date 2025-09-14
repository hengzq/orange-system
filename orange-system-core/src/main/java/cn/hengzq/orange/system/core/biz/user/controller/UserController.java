package cn.hengzq.orange.system.core.biz.user.controller;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.response.ApiResponse;
import cn.hengzq.orange.system.common.biz.user.dto.UserDetailResponse;
import cn.hengzq.orange.system.common.biz.user.dto.UserResponse;
import cn.hengzq.orange.system.common.biz.user.dto.request.*;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hengzq
 */
@Tag(name = "系统 - 用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "创建用户", operationId = "system:user:create")
    @PostMapping
    public ApiResponse<String> createUser(@RequestBody @Validated UserCreateRequest request) {
        return ApiResponse.ok(userService.createUser(request));
    }

    @Operation(summary = "根据ID删除", operationId = "system:user:delete")
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUserById(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID更新", operationId = "system:user:update")
    @PutMapping("/{id}")
    public ApiResponse<Void> updateUserById(@PathVariable("id") String id, @RequestBody @Validated UserUpdateRequest request) {
        userService.updateUserById(id, request);
        return ApiResponse.ok();
    }

    @Operation(summary = "重置密码", operationId = "system:user:update")
    @PutMapping("/{id}/reset-password")
    public ApiResponse<Void> resetPasswordById(@PathVariable("id") String id, @RequestBody @Validated UserResetPasswordRequest request) {
        userService.resetPasswordById(id, request);
        return ApiResponse.ok();
    }

    @Operation(summary = "根据ID查询", operationId = "system:user:get")
    @GetMapping("/{id}")
    public ApiResponse<UserDetailResponse> getById(@PathVariable("id") String id, @ParameterObject UserDetailQueryParam param) {
        return ApiResponse.ok(userService.getById(id, param));
    }

    @PostMapping(value = "/page")
    @Operation(summary = "分页查询", operationId = "system:user:page")
    public ApiResponse<PageDTO<UserResponse>> page(@RequestBody UserPageRequest param) {
        PageDTO<UserResponse> list = userService.page(param);
        return ApiResponse.ok(list);
    }

    @PostMapping(value = "/list")
    @Operation(summary = "查询所有的数据", operationId = "system:user:list", description = "返回所有的数据")
    public ApiResponse<List<UserResponse>> list(@RequestBody UserQueryRequest param) {
        List<UserResponse> list = userService.list(param);
        return ApiResponse.ok(list);
    }
}
