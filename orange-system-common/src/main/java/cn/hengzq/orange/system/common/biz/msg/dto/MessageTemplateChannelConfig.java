package cn.hengzq.orange.system.common.biz.msg.dto;

import cn.hengzq.orange.system.common.biz.msg.constant.MessageChannelEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Schema(description = "模板渠道管理")
@Data
public class MessageTemplateChannelConfig implements Serializable {

    @Schema(description = "渠道类型", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = MsgErrorCode.CHANNEL_TYPE_CANNOT_NULL_KEY)
    private MessageChannelEnum channel;

    @Schema(description = "是否启用")
    private boolean enabled;

    @Schema(description = "模板标题")
    private String titleTemplate;

    @Schema(description = "模板内容")
    private String contentTemplate;
}
