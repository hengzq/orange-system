# orange-system-log-biz-starter

> `orange-system-log-biz-starter` 项目基于 Spring Boot 构建，专注于为应用程序提供全面的操作日志和登录日志记录功能。
> 该项目不仅简化了日志记录的配置和实现过程，还通过灵活的日志级别设置、详细的日志内容格式化以及高效的数据存储方案
> ，确保日志信息的完整性和可读性。同时，它支持自定义日志处理器，允许开发者根据业务需求对日志进行进一步处理或分析，如实时监控、异常告警等，
> 极大地提升了系统的可维护性和安全性。

## 自定义配置

| 属性                           | 描述          | 必填 | 默认值  |
|------------------------------|-------------|----|------|
| orange.system.log.enabled    | 是否启用        | 是  | true |
| orange.system.log.ignoreUrls | 操作记录需要忽略的请求 | 否  | []   |

## 案例

```yaml
orange:
  system:
    log:
      enabled: true
      ignore-urls:
        - /orange-system/v1.0/auth/password-encrypt
```