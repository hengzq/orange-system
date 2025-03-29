package cn.hengzq.orange.system.common.biz.log.vo.login;

import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import cn.hengzq.orange.system.common.biz.log.constant.LoginTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登陆日志实体类
 */
@Data
@Schema(description = "登录记录")
public class LoginLogVO implements Serializable {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    @Schema(description = "租户id")
    private String tenantId;

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "登录账号")
    private String account;

    @Schema(description = "操作类型")
    private LoginTypeEnum type;

    @Schema(description = "操作用户ID")
    private String userId;

    @Schema(description = "操作用户名称")
    private String userName;

    @Schema(description = "操作用户IP")
    private String userIp;

    @Schema(description = "User-Agent")
    private String userAgent;

    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

    @Schema(description = "用户操作地点")
    private String userLocation;

    @Schema(description = "操作状态")
    private OperationStatusEnum status;
}
