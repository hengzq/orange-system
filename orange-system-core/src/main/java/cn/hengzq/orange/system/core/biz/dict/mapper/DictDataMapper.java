package cn.hengzq.orange.system.core.biz.dict.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.system.core.biz.dict.entity.DictDataEntity;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author hengzq
 */
@Repository
public interface DictDataMapper extends CommonMapper<DictDataEntity> {

    default List<DictDataEntity> selectListByType(String dictType) {
        return selectList(new LambdaQueryWrapper<DictDataEntity>().eq(DictDataEntity::getDictType, dictType));
    }
}
