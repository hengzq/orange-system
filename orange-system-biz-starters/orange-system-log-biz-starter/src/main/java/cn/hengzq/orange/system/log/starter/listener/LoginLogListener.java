package cn.hengzq.orange.system.log.starter.listener;

import cn.hengzq.orange.common.util.IPAddressUtil;
import cn.hengzq.orange.system.api.biz.log.LoginLogApi;
import cn.hengzq.orange.system.common.biz.log.vo.login.param.AddLoginLogParam;
import cn.hengzq.orange.system.log.starter.event.LoginLogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志监听
 */
@Slf4j
public class LoginLogListener {

    private final LoginLogApi loginLogApi;

    public LoginLogListener(LoginLogApi loginLogApi) {
        this.loginLogApi = loginLogApi;
    }

    @Async("logExecutor")
    @Order
    @EventListener(LoginLogEvent.class)
    public void saveLog(LoginLogEvent event) {
        try {
            if (event.getSource() instanceof AddLoginLogParam param) {
                param.setUserLocation(IPAddressUtil.getCacheAddressByIP(param.getUserIp()));
                loginLogApi.add(param);
            } else {
                log.warn("params type error.");
            }
        } catch (Exception e) {
            log.error("save Log is error. msg:{},data:{}", e.getMessage(), event.getSource());
        }
    }
}
