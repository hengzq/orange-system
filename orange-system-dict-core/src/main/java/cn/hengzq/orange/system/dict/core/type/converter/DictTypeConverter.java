package cn.hengzq.orange.system.dict.core.type.converter;

import cn.hengzq.orange.common.convert.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.dict.core.type.entity.DictTypeEntity;
import cn.hengzq.orange.system.dict.common.vo.type.DictTypeVO;
import cn.hengzq.orange.system.dict.common.vo.type.param.AddDictTypeParam;
import cn.hengzq.orange.system.dict.common.vo.type.param.UpdateDictTypeParam;
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

    DictTypeVO toVO(DictTypeEntity entity);

    List<DictTypeVO> toListVO(List<DictTypeEntity> entityList);

    DictTypeEntity toEntity(AddDictTypeParam param);

    DictTypeEntity toEntity(DictTypeVO dictTypeVO);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.enabled", target = "enabled")
    @Mapping(source = "param.description", target = "description")
    DictTypeEntity toUpdateEntity(DictTypeEntity entity, UpdateDictTypeParam param);

    PageDTO<DictTypeVO> toPage(PageDTO<DictTypeEntity> page);
}
