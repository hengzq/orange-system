package cn.hengzq.orange.system.log.starter.config;

import cn.hengzq.orange.system.api.log.OperationLogApi;
import cn.hengzq.orange.system.api.log.impl.OperationLogApiClientImpl;
import cn.hengzq.orange.system.log.starter.listener.OperationLogListener;
import cn.hengzq.orange.system.log.starter.properties.LogProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * 微服务架构自动装配配置
 */
@Slf4j
@EnableAsync
@AutoConfiguration
@ConditionalOnMissingClass(value = {"cn.hengzq.orange.system.core.biz.log.service.impl.LoginLogServiceImpl",
        "cn.hengzq.orange.system.core.biz.log.service.impl.OperationLogServiceImpl",}
)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true")
public class LogCloudAutoConfiguration {

    public LogCloudAutoConfiguration() {
        log.info("init {} complete.", this.getClass().getSimpleName());
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationLogApi operationLogApi() {
        if (log.isDebugEnabled()) {
            log.debug("init {}.operationLogApi complete.", this.getClass().getSimpleName());
        }
        return new OperationLogApiClientImpl();
    }

    @Bean
    public OperationLogListener operationRecordListener(OperationLogApi operationLogApi) {
        if (log.isDebugEnabled()) {
            log.info("init {}.operationRecordListener complete.", this.getClass().getSimpleName());
        }
        return new OperationLogListener(operationLogApi);
    }


}
