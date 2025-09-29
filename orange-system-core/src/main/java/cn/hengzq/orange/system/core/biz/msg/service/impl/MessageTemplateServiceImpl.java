package cn.hengzq.orange.system.core.biz.msg.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.entity.BaseEntity;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.msg.constant.MessageErrorCode;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateDetailResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateCreateRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplatePageRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateUpdateRequest;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleQueryRequest;
import cn.hengzq.orange.system.common.constant.RedisKeys;
import cn.hengzq.orange.system.core.biz.msg.converter.MessageTemplateConverter;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateEntity;
import cn.hengzq.orange.system.core.biz.msg.mapper.MessageTemplateMapper;
import cn.hengzq.orange.system.core.biz.msg.service.MsgTemplateChannelService;
import cn.hengzq.orange.system.core.biz.msg.service.MessageTemplateService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageTemplateServiceImpl implements MessageTemplateService {

    private final MessageTemplateMapper messageTemplateMapper;

    private final MsgTemplateChannelService msgTemplateChannelService;

    @Override
    @Transactional
    public String create(MessageTemplateCreateRequest request) {
        Assert.nonNull(request.getTemplateCode(), MessageErrorCode.TEMPLATE_CODE_CANNOT_NULL);
        MessageTemplateEntity entity = messageTemplateMapper.selectByTemplateCode(request.getTemplateCode());
        Assert.isNull(entity, MessageErrorCode.TEMPLATE_CODE_CANNOT_REPEAT);
        entity = MessageTemplateConverter.INSTANCE.toEntity(request);
        String tplId = messageTemplateMapper.insertOne(entity);

        if (CollUtil.isNotEmpty(request.getChannelList())) {
            msgTemplateChannelService.createTemplateChannels(tplId, request.getChannelList());
        }
        return tplId;
    }

    @Override
    @CacheEvict(cacheNames = {RedisKeys.MSG_TEMPLATE_BASIC_KEY_PREFIX}, key = "#id")
    public void deleteById(String id) {
        messageTemplateMapper.deleteOneById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {RedisKeys.MSG_TEMPLATE_BASIC_KEY_PREFIX}, key = "#id")
    public void updateById(String id, MessageTemplateUpdateRequest request) {
        MessageTemplateEntity entity = messageTemplateMapper.selectById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);

        entity = MessageTemplateConverter.INSTANCE.toUpdateEntity(entity, request);
        messageTemplateMapper.updateOneById(entity);

        msgTemplateChannelService.updateTemplateChannels(id, request.getChannelList());
    }

    @Override
    @Cacheable(cacheNames = {RedisKeys.MSG_TEMPLATE_BASIC_KEY_PREFIX}, key = "#id")
    public Optional<MessageTemplateResponse> getById(String id) {
        if (StrUtil.isBlank(id)) {
            return Optional.empty();
        }
        MessageTemplateEntity entity = messageTemplateMapper.selectById(id);
        return Optional.ofNullable(MessageTemplateConverter.INSTANCE.toResponse(entity));
    }

    @Override
    public MessageTemplateDetailResponse getDetailById(String id) {
        MessageTemplateEntity entity = messageTemplateMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_PARAMETER_ID_IS_INVALID);

        MessageTemplateDetailResponse response = MessageTemplateConverter.INSTANCE.toDetail(entity);

        response.setChannelList(msgTemplateChannelService.searchByTemplateId(id));
        return response;
    }

    @Override
    public PageDTO<MessageTemplateResponse> page(MessageTemplatePageRequest request) {
        PageDTO<MessageTemplateEntity> page = messageTemplateMapper.selectPage(request, CommonWrappers.<MessageTemplateEntity>lambdaQuery()
                .likeIfPresent(MessageTemplateEntity::getName, request.getName())
                .likeIfPresent(MessageTemplateEntity::getTemplateCode, request.getTemplateCode())
                .orderByDesc(BaseEntity::getUpdatedAt)
        );
        return MessageTemplateConverter.INSTANCE.toPage(page);
    }

}
