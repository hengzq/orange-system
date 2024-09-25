package cn.hengzq.orange.system.core.biz.department.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.core.biz.department.entity.DepartmentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author hengzq
 */
@Repository
public interface DepartmentMapper extends CommonMapper<DepartmentEntity> {

    default List<DepartmentEntity> selectByParentId(Long parentId) {
        if (Objects.isNull(parentId)) {
            return List.of();
        }
        return this.selectList(CommonWrappers.<DepartmentEntity>lambdaQuery()
                .eq(DepartmentEntity::getParentId, parentId));
    }
}
