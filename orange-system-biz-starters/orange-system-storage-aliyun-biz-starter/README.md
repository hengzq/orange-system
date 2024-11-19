# orange-system-storage-aliyun-biz-starter

> `orange-system-storage-aliyun-biz-starter`项目基于`Spring Boot`框架，旨在为开发者提供一个简洁高效的文件上传解决方案，
> 直接将文件存储至阿里云的对象存储服务（OSS）。该框架不仅简化了与阿里云 OSS 的集成流程，还通过内置的安全机制和性能优化，确保文件上传的高效性和数据的安全性。
> 此外，它支持多种配置选项，如自定义存储桶、访问控制策略等，使开发者能够轻松应对复杂多变的应用场景，是构建现代云端应用的理想工具。

## 自定义配置

| 属性                                       | 描述       | 必填 | 默认值                            |
|------------------------------------------|----------|----|--------------------------------|
| orange.system.storage.aliyun.enabled     | 是否启用     | 是  | false                          |
| orange.system.storage.aliyun.basePath    | 基础存储路径   | 是  | D://temp                       |
| orange.system.storage.aliyun.servicePath | 项目服务路径地址 | 是  | http://tiny.hengzq.cn/rest-api |

## 案例

```yaml
orange:
  system:
    storage:
      aliyun:
        enabled: false
        basePath: d://temp
        service-path: http://tiny.hengzq.cn/rest-api
```