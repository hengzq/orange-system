package cn.hengzq.orange.system.core.biz.log.controller;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import cn.hengzq.orange.system.common.biz.log.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.OperationLogPageParam;
import cn.hengzq.orange.system.core.biz.log.service.OperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author hengzq
 */
@Tag(name = "系统 - 日志 - 操作记录管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemConstant.V1_0_URL_PREFIX + "/operation-log")
public class OperationLogController {

    private final OperationLogService operationLogService;


    //    @PreAuthorize("@ss.hasPermission('system:record:operation:add')")
    @Operation(summary = "新增操作日志", operationId = "system:record:operation:add")
    @PostMapping
    public Result<String> add(@Validated @RequestBody AddOperationLogParam param) {
        return ResultWrapper.ok(operationLogService.add(param));
    }

    @Operation(summary = "分页查询", operationId = "system-log:operation:page")
    @PostMapping(value = "/page")
    public Result<PageDTO<OperationLogVO>> page(@RequestBody OperationLogPageParam param) {
        PageDTO<OperationLogVO> result = operationLogService.page(param);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:record:operation:get")
    @GetMapping("/{id}")
    public Result<OperationLogVO> getById(@PathVariable("id") String id) {
        OperationLogVO result = operationLogService.getById(id);
        return ResultWrapper.ok(result);
    }

}
