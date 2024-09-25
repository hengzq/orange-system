package cn.hengzq.orange.system.core.biz.role.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.role.vo.RoleVO;
import cn.hengzq.orange.system.common.biz.role.vo.param.AddRoleParam;
import cn.hengzq.orange.system.common.biz.role.vo.param.UpdateRoleParam;
import cn.hengzq.orange.system.core.biz.role.entity.RoleEntity;
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
public interface RoleConverter extends Converter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    RoleEntity toEntity(RoleVO vo);

    RoleEntity toEntity(AddRoleParam param);


    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.permission", target = "permission")
    @Mapping(source = "param.sort", target = "sort")
    @Mapping(source = "param.enabled", target = "enabled")
    @Mapping(source = "param.remark", target = "remark")
    RoleEntity toUpdateEntity(RoleEntity entity, UpdateRoleParam param);

    PageDTO<RoleVO> toPage(PageDTO<RoleEntity> page);

    List<RoleVO> toListVO(List<RoleEntity> entityList);

    RoleVO toVO(RoleEntity roleEntity);
}
