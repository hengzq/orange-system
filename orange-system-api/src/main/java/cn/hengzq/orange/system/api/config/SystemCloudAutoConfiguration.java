package cn.hengzq.orange.system.api.config;

import cn.hengzq.orange.system.api.biz.log.LoginLogApi;
import cn.hengzq.orange.system.api.biz.log.OperationLogApi;
import cn.hengzq.orange.system.api.biz.log.impl.LoginLogApiClientImpl;
import cn.hengzq.orange.system.api.biz.log.impl.OperationLogApiClientImpl;
import cn.hengzq.orange.system.api.biz.storage.StorageObjectApi;
import cn.hengzq.orange.system.api.biz.storage.impl.StorageObjectClientImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;


/**
 * 微服务架构自动装配配置
 *
 * @author hengzq
 */
@Slf4j
@AutoConfiguration
@ConditionalOnMissingClass(value = {"cn.hengzq.orange.system.core.biz.log.service.impl.LoginLogServiceImpl",
        "cn.hengzq.orange.system.core.biz.log.service.impl.OperationLogServiceImpl",}
)
public class SystemCloudAutoConfiguration {

    public SystemCloudAutoConfiguration() {
        log.info("init {} complete.", this.getClass().getSimpleName());
    }

    @Bean
    public OperationLogApi operationLogApi() {
        return new OperationLogApiClientImpl();
    }

    @Bean
    public LoginLogApi loginLogApi() {
        return new LoginLogApiClientImpl();
    }

    @Bean
    public StorageObjectApi storageObjectApi() {
        return new StorageObjectClientImpl();
    }
}
