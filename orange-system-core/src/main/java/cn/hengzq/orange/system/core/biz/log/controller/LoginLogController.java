package cn.hengzq.orange.system.core.biz.log.controller;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.core.biz.log.service.LoginLogService;
import cn.hengzq.orange.system.common.biz.log.vo.login.LoginLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.LoginLogPageParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author hengzq
 */
@Tag(name = "日志-登陆记录")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/login-log")
public class LoginLogController {

    private final LoginLogService loginLogService;

    @Operation(summary = "新建", operationId = "system-log:login:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated AddLoginLogParam param) {
        return ResultWrapper.ok(loginLogService.add(param));
    }


    @Operation(summary = "分页查询", operationId = "system-log:login:page")
    @PostMapping("/page")
    public Result<PageDTO<LoginLogVO>> page(@RequestBody LoginLogPageParam query) {
        PageDTO<LoginLogVO> result = loginLogService.page(query);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:record:login:get")
    @GetMapping("/{id}")
    public Result<LoginLogVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(loginLogService.getById(id));
    }


}
