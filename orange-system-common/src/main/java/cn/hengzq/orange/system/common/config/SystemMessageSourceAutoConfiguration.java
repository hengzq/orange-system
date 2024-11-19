package cn.hengzq.orange.system.common.config;

import cn.hengzq.orange.common.message.GlobalMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;

@Slf4j
@AutoConfiguration
public class SystemMessageSourceAutoConfiguration {

    public SystemMessageSourceAutoConfiguration() {
        if (log.isDebugEnabled()) {
            log.info("init {} complete.", this.getClass().getSimpleName());
        }
        // 加载错误码信息
        GlobalMessageSource.getInstance().addBasenames("i18n.system.messages");
    }

}
