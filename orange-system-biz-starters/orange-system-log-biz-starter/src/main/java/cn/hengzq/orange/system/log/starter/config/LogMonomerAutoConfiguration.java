package cn.hengzq.orange.system.log.starter.config;

import cn.hengzq.orange.system.api.log.LoginLogApi;
import cn.hengzq.orange.system.api.log.OperationLogApi;
import cn.hengzq.orange.system.log.starter.listener.LoginLogListener;
import cn.hengzq.orange.system.log.starter.listener.OperationLogListener;
import cn.hengzq.orange.system.log.starter.properties.LogProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * 单体架构场景自动装配配置
 *
 * @author hengzq
 */
@Slf4j
@EnableAsync
@AutoConfiguration
@ConditionalOnClass(name = {"cn.hengzq.orange.system.core.biz.log.service.impl.LoginLogServiceImpl",
        "cn.hengzq.orange.system.core.biz.log.service.impl.OperationLogServiceImpl"})
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true")
public class LogMonomerAutoConfiguration {
    public LogMonomerAutoConfiguration() {
        log.info("init {} complete.", this.getClass().getSimpleName());
    }

    @Bean
    @ConditionalOnClass(name = "cn.hengzq.orange.system.core.biz.log.service.impl.OperationLogServiceImpl")
    public OperationLogListener operationRecordListener(OperationLogApi operationLogApi) {
        log.info("init operationRecordListener complete.");
        return new OperationLogListener(operationLogApi);
    }

    @Bean
    @ConditionalOnClass(name = "cn.hengzq.orange.system.core.biz.log.service.impl.LoginLogServiceImpl")
    public LoginLogListener loginRecordListener(LoginLogApi loginLogApi) {
        log.info("init loginRecordListener complete.");
        return new LoginLogListener(loginLogApi);
    }


}
