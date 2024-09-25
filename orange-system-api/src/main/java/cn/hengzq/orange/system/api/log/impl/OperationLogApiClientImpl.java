package cn.hengzq.orange.system.api.log.impl;

import cn.hengzq.orange.system.api.client.SystemRestClient;
import cn.hengzq.orange.system.api.log.OperationLogApi;
import cn.hengzq.orange.system.api.log.client.OperationLogClient;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperationLogApiClientImpl implements OperationLogApi {

    @Override
    public Long add(AddOperationLogParam param) {
        try {
            OperationLogClient operationLogClient = SystemRestClient.httpServiceProxyFactory().createClient(OperationLogClient.class);
            return operationLogClient.add(param).getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
