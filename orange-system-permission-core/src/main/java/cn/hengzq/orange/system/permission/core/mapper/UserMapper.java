package cn.hengzq.orange.system.permission.core.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author hengzq
 */
@Repository
public interface UserMapper extends CommonMapper<UserEntity> {


    default UserEntity selectByUsername(String username) {
        return selectOne(CommonWrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
    }
}
