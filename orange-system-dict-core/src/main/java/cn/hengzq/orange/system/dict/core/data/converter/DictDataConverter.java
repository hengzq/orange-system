package cn.hengzq.orange.system.dict.core.data.converter;


import cn.hengzq.orange.common.convert.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.dict.core.data.entity.DictDataEntity;
import cn.hengzq.orange.system.dict.common.vo.data.DictDataVO;
import cn.hengzq.orange.system.dict.common.vo.data.param.AddDictDataParam;
import cn.hengzq.orange.system.dict.common.vo.data.param.UpdateDictDataParam;
import cn.hengzq.orange.system.dict.common.vo.type.param.AddDictTypeParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * @author hengzq
 */
@Mapper
public interface DictDataConverter extends Converter {

    DictDataConverter INSTANCE = Mappers.getMapper(DictDataConverter.class);

    PageDTO<DictDataVO> toPage(PageDTO<DictDataEntity> page);

    DictDataEntity toEntity(AddDictDataParam param);

    DictDataVO toVO(DictDataEntity entity);

    List<DictDataVO> toListVO(List<DictDataEntity> entityList);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.dictType", target = "dictType")
    @Mapping(source = "param.dictLabel", target = "dictLabel")
    @Mapping(source = "param.dictValue", target = "dictValue")
    @Mapping(source = "param.showStyle", target = "showStyle")
    @Mapping(source = "param.enabled", target = "enabled")
    @Mapping(source = "param.sort", target = "sort")
    @Mapping(source = "param.description", target = "description")
    DictDataEntity toUpdateEntity(DictDataEntity entity, UpdateDictDataParam param);
}
