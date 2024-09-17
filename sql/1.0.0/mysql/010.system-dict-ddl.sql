-- ---------------------------------------
-- orange-system-dict-core 服务依赖MySql表结构
-- ---------------------------------------
CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;


-- ----------------------------
-- 字典类型表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_dict_type
(
    `id`          bigint(20)          NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20)          NOT NULL COMMENT '租户id',
    `name`        varchar(128)                 DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(128) UNIQUE NOT NULL COMMENT '字典类型,唯一',
    `enabled`     tinyint(1)                   DEFAULT 1 COMMENT '启用状态 1:启用 0:禁用',
    `preset`      tinyint(1)                   DEFAULT 0 COMMENT '预置状态 1:预置数据 0:自定义',
    `description` varchar(1024)                DEFAULT NULL COMMENT '描述',
    `created_by`  bigint(20)          NOT NULL COMMENT '创建人',
    `created_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`  bigint(20)                   DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='字典类型表';

-- ----------------------------
-- 字典数据表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_dict_data
(
    `id`          bigint       NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint       NOT NULL COMMENT '租户id',
    `dict_type`   varchar(128) NOT NULL COMMENT '字典类型',
    `dict_label`  varchar(100)          DEFAULT '' COMMENT '字典数据标签',
    `dict_value`  varchar(64)           DEFAULT '' COMMENT '字典数据键值',
    `show_style`  varchar(32)           DEFAULT '' COMMENT '回显样式',
    `enabled`     tinyint(1)            DEFAULT 1 COMMENT '启用状态 1:启用 0:禁用',
    `preset`      tinyint(1)            DEFAULT 0 COMMENT '预置状态 1:预置数据 0:自定义',
    `sort`        int                   DEFAULT 0 COMMENT '字典排序',
    `description` varchar(1024)         DEFAULT NULL COMMENT '描述',
    `created_by`  bigint(20)   NOT NULL COMMENT '创建人',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`  bigint(20)            DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT ='字典数据表';

