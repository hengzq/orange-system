package cn.hengzq.orange.system.dict.core.type.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.system.dict.core.type.entity.DictTypeEntity;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Repository;


/**
 * @author hengzq
 */
@Repository
public interface DictTypeMapper extends CommonMapper<DictTypeEntity> {


    default DictTypeEntity selectByType(String dictType) {
        return this.selectOne(Wrappers.<DictTypeEntity>lambdaQuery().eq(DictTypeEntity::getDictType, dictType));
    }
}
