package cn.hengzq.orange.system.core.biz.user.mapper;

import cn.hengzq.orange.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.common.exception.ServiceException;
import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.core.biz.user.entity.UserRoleRlEntity;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author hengzq
 */
@Repository
public interface UserRoleRlMapper extends CommonMapper<UserRoleRlEntity> {

    default int deleteByUserId(String userId) {
        if (Objects.isNull(userId)) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_USER_ID_CANNOT_NULL);
        }
        return this.delete(CommonWrappers.<UserRoleRlEntity>lambdaQuery().eq(UserRoleRlEntity::getUserId, userId));
    }
}
