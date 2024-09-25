package cn.hengzq.orange.system.core.biz.menu.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.menu.vo.MenuVO;
import cn.hengzq.orange.system.common.biz.menu.vo.param.AddMenuParam;
import cn.hengzq.orange.system.common.biz.menu.vo.param.UpdateMenuParam;
import cn.hengzq.orange.system.core.biz.menu.entity.MenuEntity;
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
public interface MenuConverter extends Converter {

    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    MenuEntity toEntity(MenuVO vo);

    MenuEntity toEntity(AddMenuParam param);

    MenuVO toVO(MenuEntity entity);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.parentId", target = "parentId")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.permission", target = "permission")
    @Mapping(source = "param.path", target = "path")
    @Mapping(source = "param.icon", target = "icon")
    @Mapping(source = "param.hidden", target = "hidden")
    @Mapping(source = "param.sort", target = "sort")
    MenuEntity toUpdateEntity(MenuEntity entity, UpdateMenuParam param);

    PageDTO<MenuVO> toPage(PageDTO<MenuEntity> page);

    List<MenuVO> toListVO(List<MenuEntity> entityList);

}
