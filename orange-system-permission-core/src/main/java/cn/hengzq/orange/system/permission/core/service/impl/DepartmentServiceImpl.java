package cn.hengzq.orange.system.permission.core.service.impl;

import cn.hengzq.orange.common.constant.GlobalConstant;
import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.util.Assert;
import cn.hengzq.orange.common.util.CollUtils;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.converter.DepartmentConverter;
import cn.hengzq.orange.system.permission.core.entity.DepartmentEntity;
import cn.hengzq.orange.system.permission.core.mapper.DepartmentMapper;
import cn.hengzq.orange.system.permission.core.service.DepartmentService;
import cn.hengzq.orange.system.permission.common.exception.DepartmentErrorCode;
import cn.hengzq.orange.system.permission.common.vo.department.DepartmentTreeVO;
import cn.hengzq.orange.system.permission.common.vo.department.DepartmentVO;
import cn.hengzq.orange.system.permission.common.vo.department.param.AddDepartmentParam;
import cn.hengzq.orange.system.permission.common.vo.department.param.DepartmentListParam;
import cn.hengzq.orange.system.permission.common.vo.department.param.UpdateDepartmentParam;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Override
    public Boolean removeById(Long id) {
        DepartmentEntity entity = departmentMapper.selectById(id);
        if (Objects.isNull(entity)) {
            return Boolean.TRUE;
        }
        List<DepartmentEntity> departmentEntityList = departmentMapper.selectByParentId(entity.getId());
        Assert.isEmpty(departmentEntityList, DepartmentErrorCode.DEPARTMENT_DELETE_ERROR_EXIST_SUBSET);
        departmentMapper.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Long add(AddDepartmentParam request) {
        DepartmentEntity entity = DepartmentConverter.INSTANCE.toEntity(request);
        if (Objects.isNull(request.getParentId())) {
            entity.setParentId(GlobalConstant.DEFAULT_PARENT_ID);
        }
       
        return departmentMapper.insertOne(entity);
    }

    @Override
    public Boolean updateById(Long id, UpdateDepartmentParam request) {
        DepartmentEntity entity = departmentMapper.selectById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity = DepartmentConverter.INSTANCE.toUpdateEntity(entity, request);
        return departmentMapper.updateOneById(entity);
    }

    @Override
    public DepartmentVO getById(Long id) {
        return DepartmentConverter.INSTANCE.toVO(departmentMapper.selectById(id));
    }

    @Override
    public List<DepartmentVO> list(DepartmentListParam query) {
        List<DepartmentEntity> entityList = departmentMapper.selectList(
                CommonWrappers.<DepartmentEntity>lambdaQuery()
                        .eqIfPresent(DepartmentEntity::getName, query.getName())
                        .likeIfPresent(DepartmentEntity::getName, query.getNameLike())
        );
        return DepartmentConverter.INSTANCE.toListV0(entityList);
    }

    public List<DepartmentTreeVO> listTree(DepartmentListParam request) {
        List<DepartmentVO> departmentVOList = this.list(request);
        if (CollUtil.isEmpty(departmentVOList)) {
            log.info("entityList is empty.");
            return List.of();
        }
        List<DepartmentTreeVO> treeVoList = DepartmentConverter.INSTANCE.toListTreeVO(departmentVOList);
        Map<Long, List<DepartmentTreeVO>> departmentMap = treeVoList.stream().collect(Collectors.groupingBy(DepartmentTreeVO::getParentId));
        List<Long> deleteSubIds = new ArrayList<>();
        // 组装子集
        treeVoList.forEach(item -> {
            List<DepartmentTreeVO> subList = departmentMap.get(item.getId());
            if (CollUtil.isNotEmpty(subList)) {
                deleteSubIds.addAll(CollUtils.convertList(subList, DepartmentTreeVO::getId));
            }
            item.setChildren(subList);
        });
        // 过滤掉子集数据
        return treeVoList.stream().filter(item -> !deleteSubIds.contains(item.getId())).collect(Collectors.toList());
    }

}
