package cn.hengzq.orange.system.log.starter.event;

import cn.hengzq.orange.system.common.biz.log.vo.operation.param.AddOperationLogParam;
import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 * @author hengzq
 */
public class OperationLogEvent extends ApplicationEvent {

    public OperationLogEvent(AddOperationLogParam source) {
        super(source);
    }
}
