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
import lombok.NoArgsConstructor;

/**
 * 角色资源关系
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_msg_tpl_channel")
public class MessageTemplateChannelEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField("tpl_id")
    private String templateId;

    @TableField(value = "channel", typeHandler = EnumCodeTypeHandler.class)
    private MessageChannelEnum channel;

    private boolean enabled;

    @TableField("title_tpl")
    private String titleTemplate;

    @TableField("content_tpl")
    private String contentTemplate;

}
