package cn.hengzq.orange.system.core.biz.log.mapper;

import cn.hengzq.orange.mybatis.mapper.CommonMapper;
import cn.hengzq.orange.system.core.biz.log.entity.OperationLogEntity;
import org.springframework.stereotype.Repository;


/**
 * @author hengzq
 */
@Repository
public interface OperationLogMapper extends CommonMapper<OperationLogEntity> {
}
