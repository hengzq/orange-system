package cn.hengzq.orange.system.permission.core.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.entity.UserDepartmentRlEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hengzq
 */
@Repository
public interface UserDepartmentRlMapper extends CommonMapper<UserDepartmentRlEntity> {

    default List<UserDepartmentRlEntity> selectListByUserId(Long userId) {
        return selectList(CommonWrappers.<UserDepartmentRlEntity>lambdaQuery().eq(UserDepartmentRlEntity::getUserId, userId));
    }
}
