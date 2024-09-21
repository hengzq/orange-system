package cn.hengzq.orange.system.log.core.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.system.log.core.entity.OperationLogEntity;
import org.springframework.stereotype.Repository;


/**
 * @author hengzq
 */
@Repository
public interface OperationLogMapper extends CommonMapper<OperationLogEntity> {
}
