package cn.hengzq.orange.system.log.core.controller;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.common.result.ResultWrapper;
import cn.hengzq.orange.system.log.common.constant.SystemLogConstant;
import cn.hengzq.orange.system.log.common.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.log.common.vo.operation.param.OperationLogPageParam;
import cn.hengzq.orange.system.log.core.service.OperationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author hengzq
 */
@Tag(name = "日志-操作记录管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemLogConstant.V1_0_URL_PREFIX + "/operation")
public class OperationLogController {

    private final OperationLogService operationLogService;


    @Operation(summary = "分页查询", operationId = "system-log:operation:page")
    @PostMapping(value = "/page")
    public Result<PageDTO<OperationLogVO>> page(@RequestBody OperationLogPageParam param) {
        PageDTO<OperationLogVO> result = operationLogService.page(param);
        return ResultWrapper.ok(result);
    }
//
//    @Operation(summary = "导出", operationId = "system:record:operation:export")
//    @PostMapping(value = "/export")
//    public Result<Void> export(@RequestBody OperationRecordExportQuery query) {
////        List<OperationRecord> exportList = operationRecordService.queryExportList(query);
//        return ResultWrapper.ok();
//    }
//
//    @Operation(summary = "根据ID查询详情", operationId = "system:record:operation:get")
//    @GetMapping("/{id}")
//    public Result<OperationLogVO> getById(@PathVariable("id") Long id) {
//        OperationLogVO result = service.getById(id);
//        return ResultWrapper.ok(result);
//    }
//
//    @PreAuthorize("@ss.hasPermission('system:record:operation:add')")
//    @Operation(summary = "新建", operationId = "system:record:operation:add")
//    @PostMapping
//    public Result<Long> save(@Validated @RequestBody OperationLogVO OperationLogVO) {
//        return ResultWrapper.ok(operationRecordManager.add(RecordOperationConverter.INSTANCE.toEntity(OperationLogVO)));
//    }
//
//    @PreAuthorize("@ss.hasPermission('system:record:operation:clear')")
//    @Operation(summary = "清空操作日志", operationId = "system:record:operation:clear")
//    @DeleteMapping("/clear")
//    public Result<Void> clear() {
//        operationRecordManager.removeAll();
//        return ResultWrapper.ok();
//    }
}
