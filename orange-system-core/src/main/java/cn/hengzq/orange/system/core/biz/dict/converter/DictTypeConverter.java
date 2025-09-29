package cn.hengzq.orange.system.core.biz.dict.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeDetailVO;
import cn.hengzq.orange.system.common.biz.dict.dto.type.DictTypeResponse;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeCreateRequest;
import cn.hengzq.orange.system.common.biz.dict.dto.type.request.DictTypeUpdateRequest;
import cn.hengzq.orange.system.core.biz.dict.entity.DictTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author hengzq
 */
@Mapper
public interface DictTypeConverter extends Converter {

    DictTypeConverter INSTANCE = Mappers.getMapper(DictTypeConverter.class);

    DictTypeResponse toVO(DictTypeEntity entity);

    List<DictTypeResponse> toListVO(List<DictTypeEntity> entityList);

    DictTypeEntity toEntity(DictTypeCreateRequest param);

    DictTypeEntity toEntity(DictTypeResponse dictTypeResponse);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.enabled", target = "enabled")
    @Mapping(source = "param.description", target = "description")
    DictTypeEntity toUpdateEntity(DictTypeEntity entity, DictTypeUpdateRequest param);

    PageDTO<DictTypeResponse> toPage(PageDTO<DictTypeEntity> page);

    List<DictTypeDetailVO> toListDetail(List<DictTypeResponse> list);
}
