package cn.hengzq.orange.system.permission.core.mapper;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.entity.UserRoleRlEntity;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author hengzq
 */
@Repository
public interface UserRoleRlMapper extends CommonMapper<UserRoleRlEntity> {

    default int deleteByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_USER_ID_CANNOT_NULL);
        }
        return this.delete(CommonWrappers.<UserRoleRlEntity>lambdaQuery().eq(UserRoleRlEntity::getUserId, userId));
    }
}
