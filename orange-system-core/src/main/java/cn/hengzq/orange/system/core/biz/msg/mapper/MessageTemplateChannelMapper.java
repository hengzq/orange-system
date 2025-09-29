package cn.hengzq.orange.system.core.biz.msg.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateChannelEntity;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Repository;


/**
 * @author hengzq
 */
@Repository
public interface MessageTemplateChannelMapper extends CommonMapper<MessageTemplateChannelEntity> {


    default void deleteByTemplateId(String templateId) {
        if (StrUtil.isBlank(templateId)) {
            return;
        }
        this.delete(CommonWrappers.<MessageTemplateChannelEntity>lambdaQuery().eq(MessageTemplateChannelEntity::getTemplateId, templateId));
    }
}
