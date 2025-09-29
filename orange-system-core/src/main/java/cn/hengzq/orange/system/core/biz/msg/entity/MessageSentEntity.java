package cn.hengzq.orange.system.core.biz.msg.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import cn.hengzq.orange.system.common.biz.msg.constant.MessageChannelEnum;
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
@TableName(value = "sys_msg_sent")
public class MessageSentEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("msg_id")
    private String msgId;

    @TableField("recv_id")
    private String recvId;

    @TableField(value = "channel", typeHandler = EnumCodeTypeHandler.class)
    private MessageChannelEnum channel;

    @TableField("send_at")
    private LocalDateTime sendAt;

    @TableField("fail_reason")
    private String failReason;

}
