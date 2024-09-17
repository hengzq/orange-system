-- ---------------------------------------
-- orange-system-permission-core 组件依赖MySql表结构
-- ---------------------------------------

CREATE SCHEMA if not exists `orange` DEFAULT CHARACTER SET utf8mb4;

use orange;

-- ----------------------------
-- 部门信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_department`
(
    `id`          bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`   bigint(20)   NOT NULL COMMENT '租户id',
    `name`        varchar(128) NOT NULL DEFAULT '' COMMENT '名称',
    `parent_id`   bigint(20)   NOT NULL DEFAULT '-1' COMMENT '父级id,根节w点的id为-1',
    `sort`        int                   DEFAULT 0 COMMENT '显示顺序',
    `description` varchar(1024)         DEFAULT NULL COMMENT '部门描述',
    `created_by`  bigint(20)   NOT NULL COMMENT '创建人',
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`  bigint(20)            DEFAULT NULL COMMENT '更新人',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '部门表';



-- ----------------------------
-- 用户信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`         bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20) NOT NULL COMMENT '租户id',
    `nickname`   varchar(64)         DEFAULT NULL COMMENT '用户昵称',
    `email`      varchar(64)         DEFAULT NULL COMMENT '邮箱',
    `gender`     tinyint(1)          DEFAULT NULL COMMENT '用户性别',
    `phone`      varchar(16)         DEFAULT NULL COMMENT '手机号',
    `username`   varchar(64)         DEFAULT NULL COMMENT '登陆账号',
    `password`   varchar(256)        DEFAULT NULL COMMENT '密码',
    `created_by` bigint(20) NOT NULL COMMENT '创建人',
    `created_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户信息表';


-- ----------------------------
-- 用户部门关系表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_user_department_rl
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `department_id` bigint(20) NOT NULL COMMENT '用户ID',
    `user_id`       bigint(20) NOT NULL COMMENT '角色ID',
    `created_by`    bigint(20) NOT NULL COMMENT '创建人',
    `created_at`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`    bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户部门关系表';

-- ----------------------------
-- 用户角色关系表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_user_role_rl
(
    `id`         bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20) NOT NULL COMMENT '租户id',
    `user_id`    bigint(20) NOT NULL COMMENT '用户ID',
    `role_id`    bigint(20) NOT NULL COMMENT '角色ID',
    `created_by` bigint(20) NOT NULL COMMENT '创建人',
    `created_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '用户角色关系表';

-- ----------------------------
-- 角色信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_role
(
    `id`         bigint(20)   NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20)   NOT NULL COMMENT '租户id',
    `name`       varchar(64)  NOT NULL COMMENT '角色名称',
    `permission` varchar(128) NOT NULL COMMENT '权限编码',
    `sort`       int                   DEFAULT 0 COMMENT '显示顺序',
    `enabled`    tinyint(1)            DEFAULT 1 COMMENT '启用状态 1:启用 0:禁用',
    `preset`     tinyint(1)            DEFAULT 0 COMMENT '预置状态 1:预置数据 0:自定义',
    `remark`     varchar(512)          DEFAULT NULL COMMENT '备注',
    `created_by` bigint(20)   NOT NULL COMMENT '创建人',
    `created_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` bigint(20)            DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '角色信息表';

-- ----------------------------
-- 角色和资源关系表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_role_resource_rl
(
    `id`            bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`     bigint(20) NOT NULL COMMENT '租户id',
    `role_id`       bigint(20) NOT NULL COMMENT '角色ID',
    `resource_id`   bigint(20)          DEFAULT NULL COMMENT '资源ID',
    `resource_type` tinyint(1)          DEFAULT NULL COMMENT '资源类型 1 菜单 2 按钮',
    `created_by`    bigint(20) NOT NULL COMMENT '创建人',
    `created_at`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by`    bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at`    datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '角色和资源关系表';

-- ----------------------------
-- 菜单管理表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_menu
(
    `id`         bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20) NOT NULL COMMENT '租户id',
    `parent_id`  bigint(20) NOT NULL DEFAULT '-1' COMMENT '父级id,根节点的id为-1',
    `name`       varchar(128)        DEFAULT NULL COMMENT '名称',
    `permission` varchar(128)        DEFAULT NULL COMMENT '权限编码,必须唯一',
    `preset`     tinyint(1)          DEFAULT 0 COMMENT '预置状态 1:预置数据 0:自定义',
    `path`       varchar(256)        DEFAULT NULL COMMENT '菜单路径',
    `icon`       varchar(64)         DEFAULT NULL COMMENT '图标',
    `hidden`     tinyint             DEFAULT 0 COMMENT '隐藏标志 1:隐藏 0:不隐藏',
    `sort`       int                 DEFAULT 0 COMMENT '显示顺序',
    `created_by` bigint(20) NOT NULL COMMENT '创建人',
    `created_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '菜单管理表';

-- ----------------------------
-- 按钮管理表
-- ----------------------------
CREATE TABLE IF NOT EXISTS sys_button
(
    `id`         bigint(20) NOT NULL COMMENT '表的主键',
    `tenant_id`  bigint(20) NOT NULL COMMENT '租户id',
    `menu_id`    bigint(20) NOT NULL COMMENT '菜单ID',
    `name`       varchar(128)        DEFAULT NULL COMMENT '名称',
    `permission` varchar(128)        DEFAULT NULL COMMENT '权限编码，必须唯一',
    `preset`     tinyint(1)          DEFAULT 0 COMMENT '预置状态 1:预置数据 0:自定义',
    `sort`       int                 DEFAULT 0 COMMENT '显示顺序',
    `created_by` bigint(20) NOT NULL COMMENT '创建人',
    `created_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_by` bigint(20)          DEFAULT NULL COMMENT '更新人',
    `updated_at` datetime   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB COMMENT = '按钮管理表';