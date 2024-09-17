-- ---------------------------------------
-- orange-system-dict-core  服务依赖菜单
-- sys_menu ID 范围 [100,120)
-- sys_button ID 范围 [100*100,120*100)
-- ---------------------------------------
use orange;

-- 默认用户ID
SET @u_id := -100;

-- 默认租户ID
SET @t_id := -100;

-- 系统管理菜单ID
SET @s_id := 1;

-- ---------------------------------------------------------------------------------------------------------------------------------------------------
-- 字典管理 挂在系统管理菜单下面
-- ---------------------------------------------------------------------------------------------------------------------------------------------------
BEGIN;

delete
from sys_menu
where id >= 100
  and id < 120;

delete
from sys_button
where id >= 10000
  and id < 12000;

INSERT INTO sys_menu (id, tenant_id, parent_id, name, permission, preset, path, icon, hidden, sort, created_by)
VALUES (100, @t_id, @s_id, '字典管理', 'system:dict:view', 0, 'system/dict', 'system-library', 0, 60, @u_id);

insert into sys_button (id, tenant_id, menu_id, name, permission, preset, sort, created_by)
values (10000, @t_id, 100, '新增', 'system:dict:dict-type:add', 0, 1, @u_id),
       (10001, @t_id, 100, '删除', 'system:dict:dict-type:delete', 0, 10, @u_id),
       (10002, @t_id, 100, '修改', 'system:dict:dict-type:update', 0, 20, @u_id),
       (10010, @t_id, 100, '数据-新增', 'system:dict:dict-data:add', 0, 100, @u_id),
       (10012, @t_id, 100, '数据-删除', 'system:dict:dict-data:delete', 0, 120, @u_id),
       (10011, @t_id, 100, '数据-修改', 'system:dict:dict-data:update', 0, 130, @u_id)
;

COMMIT;