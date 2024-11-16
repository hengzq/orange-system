package cn.hengzq.orange.system.log.starter.config;

import cn.hengzq.orange.system.api.biz.log.LoginLogApi;
import cn.hengzq.orange.system.api.biz.log.OperationLogApi;
import cn.hengzq.orange.system.log.starter.aspect.LogAspect;
import cn.hengzq.orange.system.log.starter.listener.LoginLogListener;
import cn.hengzq.orange.system.log.starter.listener.OperationLogListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@EnableAsync
@AutoConfiguration
@AllArgsConstructor
@EnableConfigurationProperties(value = LogProperties.class)
@ConditionalOnProperty(prefix = LogProperties.PREFIX, name = "enabled", havingValue = "true")
public class LogAutoConfiguration {

    private final LogProperties logProperties;

    @Bean
    public LogAspect logAspect(ApplicationContext applicationContext) {
        log.info("init {} complete.", this.getClass().getSimpleName());
        return new LogAspect(applicationContext, logProperties);
    }


    @Bean
    public Executor logExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(1);
        //配置最大线程数
        executor.setMaxPoolSize(1);
        //配置队列大小
        executor.setQueueCapacity(10);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-operation-log-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();
        if (log.isDebugEnabled()) {
            log.debug("init logExecutor complete.");
        }
        return executor;
    }

    @Bean
    public OperationLogListener operationRecordListener(OperationLogApi operationLogApi) {
        log.info("init operationRecordListener complete.");
        return new OperationLogListener(operationLogApi);
    }

    @Bean
    public LoginLogListener loginRecordListener(LoginLogApi loginLogApi) {
        log.info("init loginRecordListener complete.");
        return new LoginLogListener(loginLogApi);
    }

}
