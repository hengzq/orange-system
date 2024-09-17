package cn.hengzq.orange.system.permission.core.controller;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.context.GlobalContextHelper;
import cn.hengzq.orange.system.permission.core.service.AuthService;
import cn.hengzq.orange.system.permission.core.service.UserService;
import cn.hengzq.orange.system.permission.common.constant.PermissionConstant;
import cn.hengzq.orange.system.permission.common.vo.auth.TokenVO;
import cn.hengzq.orange.system.permission.common.vo.auth.param.LoginParam;
import cn.hengzq.orange.system.permission.common.vo.user.UserDetailVO;
import cn.hengzq.orange.system.permission.common.vo.user.param.UserDetailQueryParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hengzq
 */
@Tag(name = "认证管理")
@RestController
@AllArgsConstructor
@RequestMapping(PermissionConstant.V1_0_URL_PREFIX + "/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    @Operation(summary = "账号密码登陆", operationId = "system:permission:auth:login")
    @PostMapping(value = "/login")
    public Result<TokenVO> login(@Validated @RequestBody LoginParam param) {
        TokenVO tokenVo = authService.login(param);
        return ResultWrapper.ok(tokenVo);
    }


    @Operation(summary = "账号退出登录", operationId = "system:permission:auth:logout")
    @GetMapping(value = "/logout")
    public Result<Void> logout() {
        return ResultWrapper.ok();
    }

    @Operation(summary = "获取登陆用户信息", operationId = "system-permission:auth:user-info")
    @GetMapping("/user-info")
    public Result<UserDetailVO> getUserInfo() {
        UserDetailQueryParam param = new UserDetailQueryParam();
        param.setShowRole(true);
        return ResultWrapper.ok(userService.getById(GlobalContextHelper.getUserId(), param));
    }

    @Operation(summary = "密码加密 只用于API测试使用", operationId = "system:permission:auth:password-encrypt")
    @GetMapping("/password-encrypt")
    public Result<String> passwordEncrypt(@RequestParam String password) {
        return ResultWrapper.ok(authService.passwordEncrypt(password));
    }
}
