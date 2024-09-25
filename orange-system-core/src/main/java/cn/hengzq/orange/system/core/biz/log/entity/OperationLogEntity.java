package cn.hengzq.orange.system.core.biz.log.entity;

import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import cn.hengzq.orange.common.enums.support.RequestMethodEnum;
import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_operation_log")
@Data
public class OperationLogEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("request_id")
    private String requestId;

    /**
     * 操作接口ID
     */
    @TableField("resource_id")
    private String resourceId;

    @TableField("resource_name")
    private String resourceName;

    /**
     * 请求URL
     */
    @TableField("request_url")
    private String requestUrl;

    @TableField(value = "request_method", typeHandler = EnumCodeTypeHandler.class)
    private RequestMethodEnum requestMethod;

    @TableField("java_method")
    private String javaMethod;

    @TableField("java_method_args")
    private String javaMethodArgs;

    @TableField("java_method_result")
    private String javaMethodResult;

    @TableField("stack_trace")
    private String stackTrace;

    @TableField("user_id")
    private Long userId;

    @TableField("user_ip")
    private String userIp;

    @TableField("user_location")
    private String userLocation;

    @TableField("user_agent")
    private String userAgent;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private OperationStatusEnum status;

    /**
     * 请求耗时
     */
    @TableField("duration")
    private Long duration;


}
