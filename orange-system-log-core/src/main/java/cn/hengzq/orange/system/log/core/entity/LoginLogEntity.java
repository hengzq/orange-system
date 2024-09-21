package cn.hengzq.orange.system.log.core.entity;

import cn.hengzq.orange.common.enums.support.OperationStatusEnum;
import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import cn.hengzq.orange.system.log.common.enums.LoginTypeEnum;
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
@Data
@TableName("sys_login_log")
public class LoginLogEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("request_id")
    private String requestId;

    private String account;

    /**
     * 操作类型,0-登陆 1-登出
     */
    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private LoginTypeEnum type;

    @TableField("user_id")
    private Long userId;

    @TableField("user_ip")
    private String userIp;

    @TableField("user_location")
    private String userLocation;

    @TableField("user_agent")
    private String userAgent;

    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 请求状态（0成功 1失败）
     */
    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private OperationStatusEnum status;
}
