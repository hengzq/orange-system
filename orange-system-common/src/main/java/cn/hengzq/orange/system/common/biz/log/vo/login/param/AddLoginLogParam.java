package cn.hengzq.orange.system.common.biz.log.vo.login.param;

import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import cn.hengzq.orange.system.common.biz.log.constant.LoginTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登陆日志实体类
 */
@Data
@Schema(description = "登录记录 - 新增参数")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddLoginLogParam implements Serializable {

    @Schema(description = "请求ID")
    private String requestId;

    @Schema(description = "登录账号")
    private String account;

    @Schema(description = "操作类型")
    private LoginTypeEnum type;

    @Schema(description = "操作用户ID")
    private String userId;

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
