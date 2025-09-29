package cn.hengzq.orange.system.core.biz.msg.service.impl;

import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateChannelConfig;
import cn.hengzq.orange.system.core.biz.msg.converter.MessageTemplateChannelConverter;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateChannelEntity;
import cn.hengzq.orange.system.core.biz.msg.mapper.MessageTemplateChannelMapper;
import cn.hengzq.orange.system.core.biz.msg.service.MsgTemplateChannelService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class MsgTemplateChannelServiceImpl implements MsgTemplateChannelService {

    private final MessageTemplateChannelMapper messageTemplateChannelMapper;

    @Override
    public void createTemplateChannels(String templateId, List<MessageTemplateChannelConfig> channelList) {
        if (StrUtil.isBlank(templateId) || CollUtil.isEmpty(channelList)) {
            log.warn("templateId or channelList is null. templateId:{},channelList:{}", templateId, channelList);
            return;
        }
        List<MessageTemplateChannelEntity> entityList = MessageTemplateChannelConverter.INSTANCE.toList(templateId, channelList);
        if (CollUtil.isEmpty(entityList)) {
            return;
        }
        messageTemplateChannelMapper.insert(entityList);
    }

    @Override
    public void updateTemplateChannels(String templateId, List<MessageTemplateChannelConfig> channelList) {
        if (StrUtil.isBlank(templateId)) {
            log.warn("templateId is null. templateId:{}", templateId);
            return;
        }
        messageTemplateChannelMapper.deleteByTemplateId(templateId);
        this.createTemplateChannels(templateId, channelList);
    }

    @Override
    public List<MessageTemplateChannelConfig> searchByTemplateId(String templateId) {
        if (StrUtil.isBlank(templateId)) {
            log.warn("Invalid templateId: '{}'. It is blank, returning empty list.", templateId);
            return Collections.emptyList();
        }
        List<MessageTemplateChannelEntity> entityList = messageTemplateChannelMapper.selectList(
                CommonWrappers.<MessageTemplateChannelEntity>lambdaQuery()
                        .eq(MessageTemplateChannelEntity::getTemplateId, templateId)
        );
        return MessageTemplateChannelConverter.INSTANCE.toConfigList(entityList);
    }


}
