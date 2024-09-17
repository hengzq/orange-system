package cn.hengzq.orange.system.permission.core.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.permission.core.entity.ButtonEntity;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Repository;


/**
 * @author hengzq
 */
@Repository
public interface ButtonMapper extends CommonMapper<ButtonEntity> {

    default ButtonEntity selectByPermission(String permission) {
        if (StrUtil.isBlank(permission)) {
            return null;
        }
        return selectOne(CommonWrappers.<ButtonEntity>lambdaQuery().eq(ButtonEntity::getPermission, permission));
    }
}
