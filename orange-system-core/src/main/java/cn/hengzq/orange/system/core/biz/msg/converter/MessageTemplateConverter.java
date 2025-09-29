package cn.hengzq.orange.system.core.biz.msg.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateDetailResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.MessageTemplateResponse;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateCreateRequest;
import cn.hengzq.orange.system.common.biz.msg.dto.request.MessageTemplateUpdateRequest;
import cn.hengzq.orange.system.common.biz.role.dto.RoleResponse;
import cn.hengzq.orange.system.core.biz.msg.entity.MessageTemplateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 部门转换器
 *
 * @author hengzq
 */
@Mapper
public interface MessageTemplateConverter extends Converter {

    MessageTemplateConverter INSTANCE = Mappers.getMapper(MessageTemplateConverter.class);

    MessageTemplateEntity toEntity(RoleResponse vo);

    MessageTemplateEntity toEntity(MessageTemplateCreateRequest request);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.templateCode", target = "templateCode")
    @Mapping(source = "param.remark", target = "remark")
    MessageTemplateEntity toUpdateEntity(MessageTemplateEntity entity, MessageTemplateUpdateRequest param);

    PageDTO<MessageTemplateResponse> toPage(PageDTO<MessageTemplateEntity> page);

    List<RoleResponse> toListVO(List<MessageTemplateEntity> entityList);

    MessageTemplateResponse toResponse(MessageTemplateEntity entity);

    MessageTemplateDetailResponse toDetail(MessageTemplateEntity messageTemplateEntity);
}
