package com.hengzq.orange.system.storage.local.starter.config;

import cn.hengzq.orange.system.common.biz.storage.service.StorageService;
import com.hengzq.orange.system.storage.local.starter.service.LocalStorageServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(value = LocalStorageProperties.class)
@ConditionalOnProperty(prefix = LocalStorageProperties.PREFIX, name = "enabled", havingValue = "true")
public class LocalStorageAutoConfiguration {

    @Bean
    public StorageService localStorageStorageService(LocalStorageProperties localStorageProperties) {
        return new LocalStorageServiceImpl(localStorageProperties);
    }
}
