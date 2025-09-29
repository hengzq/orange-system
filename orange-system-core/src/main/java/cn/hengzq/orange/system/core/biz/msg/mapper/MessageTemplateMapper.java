package cn.hengzq.orange.system.core.biz.msg.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateEntity;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Repository;


/**
 * @author hengzq
 */
@Repository
public interface MessageTemplateMapper extends CommonMapper<MessageTemplateEntity> {

    default MessageTemplateEntity selectByTemplateCode(String templateCode) {
        if (StrUtil.isBlank(templateCode)) {
            return null;
        }
        return selectOne(CommonWrappers.<MessageTemplateEntity>lambdaQuery().eq(MessageTemplateEntity::getTemplateCode, templateCode));
    }
}
