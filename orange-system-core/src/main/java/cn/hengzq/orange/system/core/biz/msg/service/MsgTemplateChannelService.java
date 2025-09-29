package cn.hengzq.orange.system.core.biz.msg.service;

import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateChannelConfig;

import java.util.List;

/**
 * @author 衡哥敲AI代码
 */
public interface MsgTemplateChannelService {

    void createTemplateChannels(String templateId, List<MessageTemplateChannelConfig> channelList);

    void updateTemplateChannels(String templateId, List<MessageTemplateChannelConfig> channelList);

    List<MessageTemplateChannelConfig> searchByTemplateId(String templateId);
}
