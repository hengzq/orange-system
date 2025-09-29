package cn.hengzq.orange.system.core.biz.msg.service.impl;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuDetailVO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.ButtonListParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.MenuListParam;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageItemResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageQueryRequest;
import cn.hengzq.orange.system.common.biz.role.constant.RoleErrorCode;
import cn.hengzq.orange.system.common.biz.role.dto.RoleDetailResponse;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleCreateRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RolePageRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleQueryRequest;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleUpdateRequest;
import cn.hengzq.orange.system.common.constant.RedisKeys;
import cn.hengzq.orange.system.core.biz.menu.service.ButtonService;
import cn.hengzq.orange.system.core.biz.menu.service.MenuService;
import cn.hengzq.orange.system.core.biz.msg.converter.MessageConverter;
import cn.hengzq.orange.system.core.biz.msg.converter.MessageTemplateConverter;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageEntity;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateEntity;
import cn.hengzq.orange.system.core.biz.msg.mapper.MessageMapper;
import cn.hengzq.orange.system.core.biz.msg.mapper.MessageTemplateMapper;
import cn.hengzq.orange.system.core.biz.msg.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    @Override
    public PageDTO<MessageItemResponse> page(MessageQueryRequest request) {
        PageDTO<MessageEntity> page = messageMapper.selectPage(request, CommonWrappers.<MessageEntity>lambdaQuery());
        return MessageConverter.INSTANCE.toPage(page);
    }

}
