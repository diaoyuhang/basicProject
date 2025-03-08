use
basic;

INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (10, 0, '权限管理', 1, 1, 1, now(), now(), 0, 0, 'system', 'system');

INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (1001, 10, '角色管理', 1, 2, 1, now(), now(), 0, 0, 'system', 'system');

INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100101, 1001, '添加角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100102, 1001, '编辑角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100103, 1001, '删除角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100104, 1001, '分配角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100105, 1001, '分配权限', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                               creator, editor)
values (1002, 10, '用户列表', 1, 2, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                               creator, editor)
values (1002001, 1002, '添加用户', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT INTO  basic.permission (id, parent_id, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                               creator, editor)
values (1002002, 1002, '修改用户', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');