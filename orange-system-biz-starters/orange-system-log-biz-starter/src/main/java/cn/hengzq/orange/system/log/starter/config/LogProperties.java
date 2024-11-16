package cn.hengzq.orange.system.log.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 日志配置
 *
 * @author hengzq
 */
@Data
@Component
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {

    /**
     * 前缀
     */
    public static final String PREFIX = "orange.system.log";

    /**
     * 是否启用
     */
    private boolean enabled = true;

    /**
     * 操作记录需要忽略的请求
     */
    private Set<String> ignoreUrls = Set.of();

}
