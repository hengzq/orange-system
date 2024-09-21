package cn.hengzq.orange.system.dict.core.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.system.dict.core.entity.DictTypeEntity;
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
