package cn.hengzq.orange.system.core.biz.department.converter;

import cn.hengzq.orange.common.converter.Converter;
import cn.hengzq.orange.system.common.biz.department.vo.DepartmentTreeVO;
import cn.hengzq.orange.system.common.biz.department.vo.DepartmentVO;
import cn.hengzq.orange.system.common.biz.department.vo.param.AddDepartmentParam;
import cn.hengzq.orange.system.common.biz.department.vo.param.UpdateDepartmentParam;
import cn.hengzq.orange.system.core.biz.department.entity.DepartmentEntity;
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
