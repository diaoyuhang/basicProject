use
basic;

INSERT basic.permission (id, parentId, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (10, 0, '权限管理', 1, 1, 1, now(), now(), 0, 0, 'system', 'system');

INSERT basic.permission (id, parentId, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (1001, 10, '角色管理', 1, 2, 1, now(), now(), 0, 0, 'system', 'system');

INSERT basic.permission (id, parentId, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100101, 1001, '添加角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT basic.permission (id, parentId, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100102, 1001, '编辑角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');
INSERT basic.permission (id, parentId, title, type, level, status, create_time, edit_time, creator_id, editor_id,
                         creator, editor)
values (100103, 1001, '删除角色', 2, 3, 1, now(), now(), 0, 0, 'system', 'system');