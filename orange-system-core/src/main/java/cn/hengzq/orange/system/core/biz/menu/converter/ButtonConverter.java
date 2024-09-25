package cn.hengzq.orange.system.core.biz.menu.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.menu.vo.ButtonVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.AddButtonParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.UpdateButtonParam;
import cn.hengzq.orange.system.core.biz.menu.entity.ButtonEntity;
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
