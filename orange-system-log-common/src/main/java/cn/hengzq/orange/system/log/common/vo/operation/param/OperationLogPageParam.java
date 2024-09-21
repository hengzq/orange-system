package cn.hengzq.orange.system.log.common.vo.operation.param;

import cn.hengzq.orange.common.dto.param.PageParam;
import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author hengzq
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "操作记录-分页查询")
public class OperationLogPageParam extends PageParam {

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "资源ID")
    private String resourceId;

    @Schema(description = "操作结果")
    private OperationStatusEnum status;

    @Schema(description = "操作开始时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationStartTime;

    @Schema(description = "操作结束时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationEndTime;

}
