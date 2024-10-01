package cn.hengzq.orange.system.core.biz.permission.controller;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.permission.vo.AuthUserInfoVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.common.biz.user.vo.param.AssignRolesToOneUserParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.permission.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "权限管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Operation(summary = "给一个用户分配多个角色", operationId = "system:permission:assign-roles-to-one-user", description = "先删除该用户所有的已绑定角色，然后绑定新的角色")
    @PostMapping("/assign-roles-to-one-user")
    public Result<Boolean> assignRolesToOneUser(@RequestBody @Validated AssignRolesToOneUserParam param) {
        return ResultWrapper.ok(permissionService.assignRolesToOneUser(param));
    }

    @Operation(summary = "给指定角色赋予资源", operationId = "system:permission:assign-resources-to-one-role", description = "先删除掉已绑定的资源,从新绑定.")
    @PostMapping("/assign-resources-to-one-role")
    public Result<Boolean> assignResourcesToOneRole(@RequestBody @Validated AssignResourcesToOneRoleParam param) {
        return ResultWrapper.ok(permissionService.assignResourcesToOneRole(param));
    }

    @Operation(summary = "获取登陆用户信息", operationId = "system:permission:user-info")
    @GetMapping("/user-info")
    public Result<AuthUserInfoVO> getUserInfo() {
        return ResultWrapper.ok(permissionService.getUserInfo());
    }


//    @Operation(summary = "给多个用户分配多个角色", operationId = "system:permission:permission:assign-roles-to-users")
//    @PostMapping("/assign-roles-to-users")
//    public Result<Boolean> allotUserRole(@RequestBody @Validated AllotUserRoleRequest request) {
//        return ResultWrapper.ok(permissionService.allotUserRole(request));
//    }

}
