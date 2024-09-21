package cn.hengzq.orange.system.log.common.vo.operation.param;

import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hengzq
 */
@Data
@Schema(description = "操作记录-查询所有参数")
public class OperationLogListParam implements Serializable {

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "操作结果")
    private OperationStatusEnum status;

    @Schema(description = "操作开始时间")
    private LocalDateTime operationStartTime;

    @Schema(description = "操作结束时间")
    private LocalDateTime operationEndTime;

}
