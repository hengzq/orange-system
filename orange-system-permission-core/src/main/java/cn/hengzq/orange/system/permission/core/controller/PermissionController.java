package cn.hengzq.orange.system.permission.core.controller;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.permission.core.service.PermissionService;
import cn.hengzq.orange.system.permission.common.constant.PermissionConstant;
import cn.hengzq.orange.system.permission.common.vo.permission.RouterVO;
import cn.hengzq.orange.system.permission.common.vo.role.param.AssignResourcesToOneRoleParam;
import cn.hengzq.orange.system.permission.common.vo.user.param.AssignRolesToOneUserParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "权限管理")
@RestController
@AllArgsConstructor
@RequestMapping(PermissionConstant.V1_0_URL_PREFIX + "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Operation(summary = "给一个用户分配多个角色", operationId = "system-permission:permission:assign-roles-to-one-user", description = "先删除该用户所有的已绑定角色，然后绑定新的角色")
    @PostMapping("/assign-roles-to-one-user")
    public Result<Boolean> assignRolesToOneUser(@RequestBody @Validated AssignRolesToOneUserParam param) {
        return ResultWrapper.ok(permissionService.assignRolesToOneUser(param));
    }

    @Operation(summary = "给指定角色赋予资源", operationId = "system-permission:permission:assign-resources-to-one-role", description = "先删除掉已绑定的资源,从新绑定.")
    @PostMapping("/assign-resources-to-one-role")
    public Result<Boolean> assignResourcesToOneRole(@RequestBody @Validated AssignResourcesToOneRoleParam param) {
        return ResultWrapper.ok(permissionService.assignResourcesToOneRole(param));
    }


    @Operation(summary = "查询当前用户路由", operationId = "system-permission:permission:list-user-routers", description = "只返回当前登录用户拥有的路由")
    @GetMapping("/list-user-routers")
    public Result<List<RouterVO>> listUserRouters() {
        return ResultWrapper.ok(permissionService.listUserRouters());
    }
//    @Operation(summary = "给多个用户分配多个角色", operationId = "system:permission:permission:assign-roles-to-users")
//    @PostMapping("/assign-roles-to-users")
//    public Result<Boolean> allotUserRole(@RequestBody @Validated AllotUserRoleRequest request) {
//        return ResultWrapper.ok(permissionService.allotUserRole(request));
//    }

}
