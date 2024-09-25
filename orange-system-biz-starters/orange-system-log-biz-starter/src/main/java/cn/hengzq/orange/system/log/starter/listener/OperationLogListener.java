package cn.hengzq.orange.system.log.starter.listener;

import cn.hengzq.orange.common.util.IPAddressUtil;
import cn.hengzq.orange.system.api.log.OperationLogApi;
import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import cn.hengzq.orange.system.log.starter.event.OperationLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 操作记录监听
 *
 * @author hengzq
 */
@Slf4j
public class OperationLogListener {

    private final OperationLogApi operationLogApi;

    public OperationLogListener(OperationLogApi operationLogApi) {
        this.operationLogApi = operationLogApi;
    }


    @Async("logExecutor")
    @Order
    @EventListener(OperationLogEvent.class)
    public void saveLog(OperationLogEvent event) {
        try {
            if (event.getSource() instanceof AddOperationLogParam param) {
                param.setUserLocation(IPAddressUtil.getCacheAddressByIP(param.getUserIp()));
                operationLogApi.add(param);
            } else {
                log.warn("params type error.");
            }
        } catch (Exception e) {
            log.error("save Log is error. msg:{}", event.getSource());
        }
    }
}
