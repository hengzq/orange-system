package cn.hengzq.orange.system.api.biz.log.impl;

import cn.hengzq.orange.system.api.biz.log.OperationLogApi;
import cn.hengzq.orange.system.api.client.SystemRestClient;
import cn.hengzq.orange.system.api.biz.log.client.OperationLogClient;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OperationLogApiClientImpl implements OperationLogApi {

    @Override
    public String add(AddOperationLogParam param) {
        try {
            OperationLogClient operationLogClient = SystemRestClient.httpServiceProxyFactory().createClient(OperationLogClient.class);
            return operationLogClient.add(param).getData();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
