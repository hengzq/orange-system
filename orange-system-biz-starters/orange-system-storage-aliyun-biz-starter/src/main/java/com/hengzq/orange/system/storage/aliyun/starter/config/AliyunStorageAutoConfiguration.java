package com.hengzq.orange.system.storage.aliyun.starter.config;

import cn.hengzq.orange.system.common.biz.storage.service.StorageService;
import com.hengzq.orange.system.storage.aliyun.starter.service.AliyunStorageServiceImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(AliyunStorageProperties.class)
@ConditionalOnProperty(prefix = AliyunStorageProperties.PREFIX, name = "enabled", havingValue = "true")
public class AliyunStorageAutoConfiguration {


    /**
     * 阿里云存储服务
     */
    @Bean
    public StorageService aliyunStorageStorageService(AliyunStorageProperties properties) {
        return new AliyunStorageServiceImpl(properties);
    }
}
