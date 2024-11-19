package cn.hengzq.orange.system.api.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;


/**
 * 单体架构场景自动装配配置
 *
 * @author hengzq
 */
@Slf4j
@AutoConfiguration
@ConditionalOnClass(name = {"cn.hengzq.orange.system.core.biz.log.service.impl.LoginLogServiceImpl",
        "cn.hengzq.orange.system.core.biz.log.service.impl.OperationLogServiceImpl"})
public class SystemMonomerAutoConfiguration {

    public SystemMonomerAutoConfiguration() {
        log.info("init {} complete.", this.getClass().getSimpleName());
    }

}
