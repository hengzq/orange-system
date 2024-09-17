-- ---------------------------------------
-- orange-system-permission-core 服务依赖菜单
-- sys_menu ID 范围 [1,100)
-- sys_button ID 范围 [1*100,100*100)
--
-- 按钮ID范围为：[菜单ID*100, (菜单ID + 1) * 100)
-- eg: 菜单ID：1 按钮ID：[100,200)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- ----------------------------
-- 系统管理-菜单和按钮数据预置
-- ----------------------------
BEGIN;

delete
from sys_menu
where id >= 1
  and id < 100;

delete
from sys_button
where id >= 100
  and id < 10000;

-- 系统管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (1, @t_id, -1, '系统管理', 'system', 0, '', 'system-settings', 0, 20, @u_id);

-- 部门管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (2, @t_id, 1, '部门管理', 'system-permission:department:view', 0, 'system/department',
        'system-people-circle', 0, 20, @u_id);

insert into sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
values (200, @t_id, 2, '新增', 'system-permission:department:add', 1, 1, @u_id),
       (210, @t_id, 2, '删除', 'system-permission:department:delete', 1, 10, @u_id),
       (220, @t_id, 2, '修改', 'system-permission:department:edit', 1, 20, @u_id)
;

-- 用户管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (3, @t_id, 1, '用户管理', 'system-permission:user:page', 0, 'system/user', 'system-person-circle', 0,
        30, @u_id);

insert into sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
values (300, @t_id, 3, '新增', 'system-permission:user:add', 0, 1, @u_id),
       (310, @t_id, 3, '删除', 'system-permission:user:delete', 0, 10, @u_id),
       (311, @t_id, 3, '批量删除', 'system-permission:user:batch-delete', 0, 11, @u_id),
       (320, @t_id, 3, '修改', 'system-permission:user:edit', 0, 20, @u_id),
       (321, @t_id, 3, '修改密码', 'system-permission:user:update-password', 0, 21, @u_id),
       (322, @t_id, 3, '重置密码', 'system-permission:user:reset-password', 0, 22, @u_id)
;

-- 菜单管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (4, @t_id, 1, '菜单管理', 'system-permission:menu:view', 0, 'system/menu', 'system-list-circle', 0,
        40, @u_id);

insert into sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
values (400, @t_id, 4, '新增', 'system-permission:menu:add', 1, 1, @u_id),
       (410, @t_id, 4, '删除', 'system-permission:menu:delete', 1, 10, @u_id),
       (420, @t_id, 4, '修改', 'system-permission:menu:update', 1, 20, @u_id)
;

-- 角色管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (5, @t_id, 1, '角色管理', 'system-permission:role:view', 0, 'system/role', 'system-people', 0, 50,
        @u_id);

insert into sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
values (500, @t_id, 5, '新增', 'system-permission:role:add', 1, 1, @u_id),
       (510, @t_id, 5, '删除', 'system-permission:role:delete', 1, 10, @u_id),
       (520, @t_id, 5, '修改', 'system-permission:role:update', 1, 20, @u_id)
;
COMMIT;


-- ----------------------------
-- 看板-菜单
-- ----------------------------
BEGIN;
-- 角色管理
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (20, @t_id, -1, '首页', 'dashboard', 0, 'board/home', 'system-home', 0, 1, @u_id);

COMMIT;


-- ----------------------------
-- 系统工具-菜单
-- ----------------------------
BEGIN;
-- 系统工具
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (30, @t_id, -1, '系统工具', 'system:tools', 0, '', 'system-apps', 0, 1000, @u_id);

-- API文档
INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (31, @t_id, 30, '图标', 'system:tools:icon', 0, 'tools/icon', 'system-images', 0, 1, @u_id);

COMMIT;