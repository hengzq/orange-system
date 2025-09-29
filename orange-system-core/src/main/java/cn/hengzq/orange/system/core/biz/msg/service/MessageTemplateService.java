package cn.hengzq.orange.system.core.biz.msg.service;

import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateDetailResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateCreateRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplatePageRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateUpdateRequest;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.common.biz.role.dto.request.RoleQueryRequest;

import java.util.List;
import java.util.Optional;

/**
 * @author 衡哥敲AI代码
 */
public interface MessageTemplateService {

    String create(MessageTemplateCreateRequest param);

    void deleteById(String id);

    void updateById(String id, MessageTemplateUpdateRequest request);

    Optional<MessageTemplateResponse> getById(String id);

    MessageTemplateDetailResponse getDetailById(String id);

    PageDTO<MessageTemplateResponse> page(MessageTemplatePageRequest request);


}
