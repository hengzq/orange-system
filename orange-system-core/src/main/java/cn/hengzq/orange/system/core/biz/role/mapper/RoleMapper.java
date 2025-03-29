package cn.hengzq.orange.system.core.biz.role.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.core.biz.role.entity.RoleEntity;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author hengzq
 */
@Repository
public interface RoleMapper extends CommonMapper<RoleEntity> {

    List<RoleEntity> selectListByUserId(String userId);

    default RoleEntity selectByPermission(String permission) {
        if (StrUtil.isBlank(permission)) {
            return null;
        }
        return selectOne(CommonWrappers.<RoleEntity>lambdaQuery().eq(RoleEntity::getPermission, permission));
    }
}
