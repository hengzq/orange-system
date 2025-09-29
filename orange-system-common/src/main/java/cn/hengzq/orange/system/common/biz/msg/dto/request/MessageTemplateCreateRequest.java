package cn.hengzq.orange.system.common.biz.msg.dto.request;

import cn.hengzq.orange.system.common.biz.msg.constant.MessageErrorCode;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateChannelConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author hengzq
 */
@Schema(description = "消息模板管理 - 创建参数")
@Data
public class MessageTemplateCreateRequest implements Serializable {

    //    @NotNull(message = RoleErrorCode.ROLE_NAME_CANNOT_NULL_KEY)
    @Schema(description = "模板名称")
    private String name;

    @NotBlank(message = MessageErrorCode.TEMPLATE_CODE_CANNOT_NULL_KEY)
    @Schema(description = "模板编码")
    private String templateCode;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "渠道配置")
    private List<MessageTemplateChannelConfig> channelList;

}
