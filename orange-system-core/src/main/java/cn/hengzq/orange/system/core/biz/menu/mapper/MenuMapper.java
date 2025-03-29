package cn.hengzq.orange.system.core.biz.menu.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.system.core.biz.menu.entity.MenuEntity;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author hengzq
 */
@Repository
public interface MenuMapper extends CommonMapper<MenuEntity> {

    /**
     * 根据父节点id查询
     */
    default List<MenuEntity> selectListByParentId(String parentId) {
        return this.selectList(Wrappers.<MenuEntity>lambdaQuery().eq(MenuEntity::getParentId, parentId));
    }

    default MenuEntity selectByPermission(String permission) {
        return selectOne(Wrappers.<MenuEntity>lambdaQuery().eq(MenuEntity::getPermission, permission));
    }
}
