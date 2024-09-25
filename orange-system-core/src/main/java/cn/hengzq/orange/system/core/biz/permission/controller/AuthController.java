package cn.hengzq.orange.system.core.biz.permission.controller;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.permission.vo.TokenVO;
import cn.hengzq.orange.system.common.biz.permission.vo.param.LoginParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.permission.service.AuthService;
import cn.hengzq.orange.system.permission.core.service.UserService;
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
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/auth")
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


    @Operation(summary = "密码加密 只用于API测试使用", operationId = "system:permission:auth:password-encrypt")
    @GetMapping("/password-encrypt")
    public Result<String> passwordEncrypt(@RequestParam String password) {
        return ResultWrapper.ok(authService.passwordEncrypt(password));
    }
}
