package cn.hengzq.orange.system.core.biz.msg.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
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
@TableName(value = "sys_msg_recv")
public class MessageReceiveEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("msg_id")
    private String msgId;

    @TableField("recv_id")
    private String recvId;

    @TableField("read_status")
    private boolean readStatus;

    @TableField("read_at")
    private LocalDateTime readAt;

    @TableField("deleted")
    private boolean deleted;

    @TableField("deleted_at")
    private LocalDateTime deletedAt;
}
