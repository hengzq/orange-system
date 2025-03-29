package cn.hengzq.orange.system.common.biz.log.vo.operation;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import cn.hengzq.orange.common.enums.support.RequestMethodEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "操作记录")
@Data
public class OperationLogVO extends BaseTenantDTO {

    @Schema(description = "主键")
    private String id;

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "操作资源ID")
    private String resourceId;

    @Schema(description = "操作资源名称")
    private String resourceName;

    @Schema(description = "请求URL")
    private String requestUrl;

    @Schema(description = "请求方式 GET,POST,PUT,PATCH,DELETE")
    private RequestMethodEnum requestMethod;

    @Schema(description = "Java 方法名")
    private String javaMethod;

    @Schema(description = "Java 方法名参数")
    private String javaMethodArgs;

    @Schema(description = "Java 方法返回结果")
    private String javaMethodResult;

    @Schema(description = "异常堆栈信息")
    private String stackTrace;

    @Schema(description = "操作用户ID")
    private String userId;

    @Schema(description = "操作用户名称")
    private String userName;

    @Schema(description = "操作用户IP")
    private String userIp;

    @Schema(description = "用户操作地点")
    private String userLocation;

    @Schema(description = "User-Agent")
    private String userAgent;

    @Schema(description = "开始时间")
    private LocalDateTime startTime;

    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    @Schema(description = "请求状态")
    private OperationStatusEnum status;

    @Schema(description = "请求耗时,单位毫秒")
    private Long duration;

}
