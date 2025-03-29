package cn.hengzq.orange.system.core.biz.log.service;


import cn.hengzq.orange.common.dto.PageDTO;
import cn.hengzq.orange.system.common.biz.log.vo.operation.OperationLogVO;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.OperationLogPageParam;

import java.util.List;


public interface OperationLogService {

    String add(AddOperationLogParam param);

    OperationLogVO getById(String id);

    PageDTO<OperationLogVO> page(OperationLogPageParam param);

}
