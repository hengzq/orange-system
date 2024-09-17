package cn.hengzq.orange.system.permission.core.convert;

import cn.hengzq.orange.common.convert.Converter;
import cn.hengzq.orange.system.permission.core.entity.DepartmentEntity;
import cn.hengzq.orange.system.permission.common.vo.department.DepartmentTreeVO;
import cn.hengzq.orange.system.permission.common.vo.department.DepartmentVO;
import cn.hengzq.orange.system.permission.common.vo.department.param.AddDepartmentParam;
import cn.hengzq.orange.system.permission.common.vo.department.param.UpdateDepartmentParam;
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
public interface DepartmentConverter extends Converter {

    DepartmentConverter INSTANCE = Mappers.getMapper(DepartmentConverter.class);

    DepartmentEntity toEntity(DepartmentVO departmentVO);

    DepartmentEntity toEntity(AddDepartmentParam request);

    DepartmentEntity toEntity(UpdateDepartmentParam request);

    DepartmentVO toVO(DepartmentEntity entity);

    List<DepartmentTreeVO> toListTreeVO(List<DepartmentVO> departmentVOList);

    List<DepartmentVO> toListV0(List<DepartmentEntity> entityList);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "param.parentId", target = "parentId")
    @Mapping(source = "param.name", target = "name")
    @Mapping(source = "param.sort", target = "sort")
    @Mapping(source = "param.description", target = "description")
    DepartmentEntity toUpdateEntity(DepartmentEntity entity, UpdateDepartmentParam param);
}
