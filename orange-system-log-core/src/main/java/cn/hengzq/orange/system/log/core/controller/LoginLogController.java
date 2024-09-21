package cn.hengzq.orange.system.log.core.controller;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.log.common.constant.SystemLogConstant;
import cn.hengzq.orange.system.log.common.vo.login.LoginLogVO;
import cn.hengzq.orange.system.log.common.vo.login.param.LoginLogPageParam;
import cn.hengzq.orange.system.log.core.service.LoginLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author hengzq
 */
@Tag(name = "日志-登陆记录")
@RestController
@AllArgsConstructor
@RequestMapping(SystemLogConstant.V1_0_URL_PREFIX + "/login")
public class LoginLogController {

    private final LoginLogService loginLogService;


    @Operation(summary = "分页查询", operationId = "system-log:login:page")
    @PostMapping("/page")
    public Result<PageDTO<LoginLogVO>> page(@RequestBody LoginLogPageParam query) {
        PageDTO<LoginLogVO> result = loginLogService.page(query);
        return ResultWrapper.ok(result);
    }

//    @Operation(summary = "根据ID查询详情", operationId = "system:record:login:get")
//    @GetMapping("/{id}")
//    public Result<RecordLoginVO> getById(@PathVariable("id") Long id) {
//        RecordLoginEntity entity = recordLoginManager.getById(id);
//        return ResultWrapper.ok(RecordLoginConverter.INSTANCE.toVo(entity));
//    }
//
//    @PreAuthorize("@ss.hasPermission('system:record:login:add')")
//    @Operation(summary = "新建", operationId = "system:record:login:add")
//    @PostMapping
//    public Result<Long> save(@Validated @RequestBody RecordLoginVO record) {
//        return ResultWrapper.ok(loginLogService.add(record));
//    }
//
//    @PreAuthorize("@ss.hasPermission('system:record:login:clear')")
//    @Operation(summary = "清空操作日志", operationId = "system:record:login:clear")
//    @DeleteMapping("/clear")
//    public Result<Void> clear() {
//        recordLoginManager.removeAll();
//        return ResultWrapper.ok();
//    }
}
