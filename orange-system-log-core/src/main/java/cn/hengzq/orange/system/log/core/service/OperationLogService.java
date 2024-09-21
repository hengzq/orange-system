package cn.hengzq.orange.system.log.core.service;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.log.common.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.log.common.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.log.common.vo.operation.param.OperationLogPageParam;

import java.util.List;


public interface OperationLogService {

    Long add(AddOperationLogParam param);

    OperationLogVO getById(Long id);

    PageDTO<OperationLogVO> page(OperationLogPageParam param);

}
