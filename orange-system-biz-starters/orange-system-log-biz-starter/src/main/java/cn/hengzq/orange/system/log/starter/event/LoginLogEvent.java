package cn.hengzq.orange.system.log.starter.event;

import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import org.springframework.context.ApplicationEvent;

/**
 * 登录日志事件
 *
 * @author hengzq
 */
public class LoginLogEvent extends ApplicationEvent {

    public LoginLogEvent(AddLoginLogParam source) {
        super(source);
    }
}
