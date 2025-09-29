package cn.hengzq.orange.system.core.biz.msg.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateChannelConfig;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateChannelEntity;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hengzq
 */
@Mapper
public interface MessageTemplateChannelConverter extends Converter {

    MessageTemplateChannelConverter INSTANCE = Mappers.getMapper(MessageTemplateChannelConverter.class);


    default List<MessageTemplateChannelEntity> toList(String templateId, List<MessageTemplateChannelConfig> channelList) {
        if (StrUtil.isBlank(templateId)) {
            return null;
        }
        if (CollUtil.isEmpty(channelList)) {
            return null;
        }
        List<MessageTemplateChannelEntity> list = new ArrayList<>(channelList.size());
        for (MessageTemplateChannelConfig config : channelList) {
            list.add(toEntity(templateId, config));
        }
        return list;
    }

    @Mapping(source = "templateId", target = "templateId")
    @Mapping(source = "config.channel", target = "channel")
    @Mapping(source = "config.titleTemplate", target = "titleTemplate")
    @Mapping(source = "config.contentTemplate", target = "contentTemplate")
    MessageTemplateChannelEntity toEntity(String templateId, MessageTemplateChannelConfig config);

    List<MessageTemplateChannelConfig> toConfigList(List<MessageTemplateChannelEntity> entityList);

}
