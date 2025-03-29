package cn.hengzq.orange.system.api.biz.log;

import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;

public interface OperationLogApi {

    String add(AddOperationLogParam param);
}
