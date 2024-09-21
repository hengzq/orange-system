-- ---------------------------------------
-- orange-system-log-core 服务依赖MySql表结构
-- ---------------------------------------
CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 操作记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_operation_log
(
    `id`                 bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`          bigint(20)   NOT NULL COMMENT '租户id',
    `request_id`         varchar(64)  NOT NULL COMMENT '请求ID',
    `resource_id`        varchar(128) NOT NULL COMMENT '操作接口ID',
    `request_url`        varchar(512) NOT NULL COMMENT '请求URL',
    `request_method`     varchar(8)   NOT NULL COMMENT '请求方式 GET,POST,PUT,PATCH,DELETE',
    `java_method`        varchar(512) NULL COMMENT 'Java 方法名',
    `java_method_args`   text         NULL COMMENT 'Java 方法参数',
    `java_method_result` text         NULL COMMENT 'Java 方法返回结果',
    `stack_trace`        text         NULL COMMENT '异常堆栈信息',
    `user_id`            bigint(32)            DEFAULT NULL COMMENT '操作用户ID',
    `user_ip`            varchar(64)           DEFAULT '' COMMENT '用户 IP',
    `user_location`      varchar(64)           DEFAULT NULL COMMENT 'IP地址,登录地点',
    `user_agent`         varchar(128)          DEFAULT '' COMMENT 'User-Agent',
    `start_time`         datetime              DEFAULT NULL COMMENT '开始时间',
    `end_time`           datetime              DEFAULT NULL COMMENT '结束时间',
    `status`             tinyint(1)            DEFAULT 0 COMMENT '登录状态（0成功 1失败）',
    `duration`           bigint(20)            DEFAULT NULL COMMENT '请求耗时 单位毫秒',
    `created_by`         bigint(20)   NOT NULL COMMENT '创建人',
    `created_at`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`         bigint(20)            DEFAULT NULL COMMENT '更新人',
    `updated_at`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='操作记录';



-- ----------------------------
-- 登录记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_login_log
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `request_id`    varchar(64)         DEFAULT '' COMMENT '请求ID',
    `account`       varchar(64)         DEFAULT '' COMMENT '用户账号',
    `type`          tinyint(1)          DEFAULT NULL COMMENT '操作类型,0-登陆 1-登出',
    `user_id`       bigint(32)          DEFAULT NULL COMMENT '登录用户ID',
    `user_ip`       varchar(32)         DEFAULT '' COMMENT '用户IP',
    `user_location` varchar(64)         DEFAULT NULL COMMENT '登录地点',
    `user_agent`    varchar(128)        DEFAULT '' COMMENT 'User-Agent',
    `login_time`    datetime   NOT NULL COMMENT '登录时间',
    `status`        tinyint(1)          DEFAULT 0 COMMENT '登录状态（0成功 1失败）',
    `created_by`    bigint(20) NOT NULL COMMENT '创建人',
    `created_at`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`    bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='系统登录记录';

