package cn.hengzq.orange.system.core.biz.user.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.core.biz.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author hengzq
 */
@Repository
public interface UserMapper extends CommonMapper<UserEntity> {


    default UserEntity selectByLoginAccount(String loginAccount) {
        return selectOne(CommonWrappers.<UserEntity>lambdaQuery().eq(UserEntity::getLoginAccount, loginAccount));
    }
}
