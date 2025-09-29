package cn.hengzq.orange.system.common.biz.msg.dto;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import cn.hengzq.orange.system.common.biz.msg.constant.MessageChannelEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息管理 - 列表展示")
@Data
public class MessageItemResponse extends BaseTenantDTO {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "模板ID")
    private String templateId;

    @Schema(description = "渠道")
    private MessageChannelEnum channel;

    @Schema(description = "消息标题")
    private String title;

    @Schema(description = "消息内容")
    private String content;


}
