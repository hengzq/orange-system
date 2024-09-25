package cn.hengzq.orange.system.api.log;

import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;

public interface OperationLogApi {

    Long add(AddOperationLogParam param);
}
