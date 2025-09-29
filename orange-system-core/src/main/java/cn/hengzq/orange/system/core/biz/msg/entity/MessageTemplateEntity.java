package cn.hengzq.orange.system.core.biz.msg.entity;

import cn.hengzq.orange.mybatis.entity.BaseTenantEntity;
import cn.hengzq.orange.mybatis.handler.EnumCodeTypeHandler;
import cn.hengzq.orange.system.common.biz.msg.constant.MessageTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_msg_tpl")
public class MessageTemplateEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 模板唯一编码
     */
    @TableField("tpl_code")
    private String templateCode;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 消息类型
     */
    @TableField(value = "msg_type", typeHandler = EnumCodeTypeHandler.class)
    private MessageTypeEnum msgType;

    /**
     * 备注
     */
    private String remark;

}
