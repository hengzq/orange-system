package cn.hengzq.orange.system.permission.core.convert;

import cn.hengzq.orange.common.convert.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.permission.core.entity.ButtonEntity;
import cn.hengzq.orange.system.permission.common.vo.button.ButtonVO;
import cn.hengzq.orange.system.permission.common.vo.button.param.AddButtonParam;
import cn.hengzq.orange.system.permission.common.vo.button.param.UpdateButtonParam;
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
public interface ButtonConverter extends Converter {

    ButtonConverter INSTANCE = Mappers.getMapper(ButtonConverter.class);

    ButtonEntity toEntity(ButtonVO vo);

    ButtonEntity toEntity(AddButtonParam param);

    ButtonVO toVO(ButtonEntity entity);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.permission", target = "permission")
    @Mapping(source = "param.sort", target = "sort")
    ButtonEntity toUpdateEntity(ButtonEntity entity, UpdateButtonParam param);

    PageDTO<ButtonVO> toPage(PageDTO<ButtonEntity> page);

    List<ButtonVO> toListVO(List<ButtonEntity> entityList);
}
