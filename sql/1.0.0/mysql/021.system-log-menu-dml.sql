-- ---------------------------------------
-- orange-system-log-core 服务依赖菜单
-- sys_menu ID 范围 [120, 140)
-- sys_button ID 范围 [120*100, 140*100)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- ----------------------------------------------------------------------------------------------------------------------------------------------------
-- 菜单和按钮数据预置
-- ----------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;

delete
from sys_menu
where id >= 120
  and id < 140;

delete
from sys_button
where id >= 12000
  and id < 14000;

-- 日志管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (120, @t_id, -1, '日志管理', 'system:record', 0, '', 'system-reader', 0, 120, @u_id);

-- 登录日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (121, @t_id, 120, '登陆日志', 'system:record:login:view', 0, 'system-log/login', 'system-reader', 0,
        10, @u_id);

INSERT INTO sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
VALUES (12100, @t_id, 121, '清空', 'system:record:login:clear', 0, 1, @u_id);

-- 操作日志
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (122, @t_id, 120, '操作日志', 'system:record:operation:view', 0, 'system-log/operation',
        'system-reader', 0, 20, @u_id);

INSERT INTO sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
VALUES (12200, @t_id, 122, '清空', 'system:record:operation:clear', 1, 2, @u_id);
COMMIT;