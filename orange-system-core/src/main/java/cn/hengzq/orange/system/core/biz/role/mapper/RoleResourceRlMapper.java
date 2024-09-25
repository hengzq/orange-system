package cn.hengzq.orange.system.core.biz.role.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.mybatis.query.CommonWrappers;
import cn.hengzq.orange.system.common.biz.menu.constant.ResourceTypeEnum;
import cn.hengzq.orange.system.core.biz.role.entity.RoleResourceRlEntity;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author hengzq
 */
@Repository
public interface RoleResourceRlMapper extends CommonMapper<RoleResourceRlEntity> {

    default void deleteByRoleId(Long roleId) {
        delete(CommonWrappers.<RoleResourceRlEntity>lambdaQuery().eq(RoleResourceRlEntity::getRoleId, roleId));
    }

    default List<RoleResourceRlEntity> selectListByTypeAndRoleIds(ResourceTypeEnum typeEnum, List<Long> roleIds) {
        return this.selectList(Wrappers.<RoleResourceRlEntity>lambdaQuery()
                .in(RoleResourceRlEntity::getRoleId, roleIds)
                .eq(RoleResourceRlEntity::getResourceType, typeEnum));
    }
}
