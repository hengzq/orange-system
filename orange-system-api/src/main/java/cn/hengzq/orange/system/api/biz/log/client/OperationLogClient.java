package cn.hengzq.orange.system.api.biz.log.client;

import cn.hengzq.orange.common.result.Result;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.common.constant.SystemConstant;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

//@HttpExchange(url = SystemConstant.V1_0_URL_PREFIX + "/operation-log")
public interface OperationLogClient {

    @PostExchange(SystemConstant.V1_0_URL_PREFIX + "/operation-log")
    Result<Long> add(@Validated @RequestBody AddOperationLogParam param);
}
