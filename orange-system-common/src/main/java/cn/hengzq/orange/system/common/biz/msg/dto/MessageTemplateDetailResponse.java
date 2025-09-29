package cn.hengzq.orange.system.common.biz.msg.dto;

import cn.hengzq.orange.common.dto.BaseTenantDTO;
import cn.hengzq.orange.system.common.biz.msg.constant.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author hengzq
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息模板管理")
@Data
public class MessageTemplateDetailResponse extends BaseTenantDTO {

    @Schema(description = "主键ID")
    private String id;

    @Schema(description = "模板名称")
    private String name;

    @Schema(description = "模板编码")
    private String templateCode;

    @Schema(description = "消息类型")
    private MessageTypeEnum msgType;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "渠道配置")
    private List<MessageTemplateChannelConfig> channelList;



}
