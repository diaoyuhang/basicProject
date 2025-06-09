CREATE
DATABASE nature
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

use
nature;
CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    employee_id   varchar(32)  NOT NULL comment '工号',
    password      varchar(128) NOT NULL comment '密码',
    name          varchar(32)  NOT NULL comment '用户名',
    gender        TINYINT(1) NOT NULL default 1 comment '性别|0-女，1-男',
    avatar        BIGINT                default null comment '头像|file.id',
    email         varchar(64)           default '' comment '用户邮箱',
    department_id BIGINT                DEFAULT 0 comment '部门id',
    status        int          not null default 1 comment '状态|0-停用，1-激活',
    creator_id    BIGINT       NOT NULL comment '创建人用户id',
    editor_id     BIGINT       NOT NULL comment '修改人用户id',
    create_time   datetime     not null comment '创建时间',
    edit_time     datetime     not null comment '编辑时间',
    creator       varchar(32)  not null comment '创建人',
    editor        varchar(32)  not null comment '修改人',
    rec_time      timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX user_employee_id (employee_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户';

CREATE TABLE department
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    parent_id       varchar(32) NOT NULL comment '上级部门id',
    department_name varchar(32) NOT NULL comment '部门名称',
    level           int         not null comment '部门级别',
    status          int         not null default 0 comment '状态|0-停用，1-启用',
    creator_id      BIGINT      NOT NULL comment '创建人用户id',
    editor_id       BIGINT      NOT NULL comment '修改人用户id',
    create_time     datetime    not null comment '创建时间',
    edit_time       datetime    not null comment '编辑时间',
    creator         varchar(32) not null comment '创建人',
    editor          varchar(32) not null comment '修改人',
    rec_time        timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX           department_parent_id (parent_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='部门';

CREATE TABLE file
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    file_name        varchar(100) NOT NULL comment '文件名',
    file_type        varchar(8)   not null comment '文件类型',
    data             MEDIUMBLOB comment '文件字节',
    md5              varchar(100)          default '' comment 'md5标识',
    storage_location varchar(8)   not null default 'local' comment '存储位置|local-本地数据库，qn-七牛云',
    create_time      datetime     not null comment '创建时间',
    edit_time        datetime     not null comment '编辑时间',
    creator_id       BIGINT       NOT NULL comment '创建人用户id',
    editor_id        BIGINT       NOT NULL comment '修改人用户id',
    creator          varchar(32)  not null comment '创建人',
    editor           varchar(32)  not null comment '修改人',
    rec_time         timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX file_md5 (md5)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='文件';

CREATE TABLE video
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    video_name       varchar(100) NOT NULL comment '文件名',
    video_type       varchar(8)   not null comment '文件类型',
    data             LONGBLOB comment '文件字节',
    storage_location varchar(8)   not null default 'local' comment '存储位置|local-本地数据库，qn-七牛云',
    create_time      datetime     not null comment '创建时间',
    edit_time        datetime     not null comment '编辑时间',
    creator_id       BIGINT       NOT NULL comment '创建人用户id',
    editor_id        BIGINT       NOT NULL comment '修改人用户id',
    creator          varchar(32)  not null comment '创建人',
    editor           varchar(32)  not null comment '修改人',
    rec_time         timestamp    not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='视频';

CREATE TABLE role
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    role_name   varchar(32) NOT NULL comment '角色名称',
    status      int         not null default 1 comment '状态|0-停用，1-启用',
    create_time datetime    not null comment '创建时间',
    edit_time   datetime    not null comment '编辑时间',
    creator_id  BIGINT      NOT NULL comment '创建人用户id',
    editor_id   BIGINT      NOT NULL comment '修改人用户id',
    creator     varchar(32) not null comment '创建人',
    editor      varchar(32) not null comment '修改人',
    rec_time    timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='角色';

CREATE TABLE user_role
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    role_id     BIGINT      NOT NULL comment '角色id',
    user_id     BIGINT      NOT NULL comment '用户id',
    create_time datetime    not null comment '创建时间',
    edit_time   datetime    not null comment '编辑时间',
    creator_id  BIGINT      NOT NULL comment '创建人用户id',
    editor_id   BIGINT      NOT NULL comment '修改人用户id',
    creator     varchar(32) not null comment '创建人',
    editor      varchar(32) not null comment '修改人',
    rec_time    timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户角色关联表';

CREATE TABLE permission
(
    id          BIGINT PRIMARY KEY comment '主键',
    parent_id   BIGINT      NOT NULL comment '父节点id|permission.id',
    path        varchar(128)         DEFAULT '' comment '路径',
    title       varchar(32) NOT NULL comment '权限主题',
    type        int         not null comment '类型|1-菜单权限，2-按钮权限',
    level       int         not null comment '菜单级别',
    status      int         not null default 1 comment '状态|0-停用，1-启用',
    create_time datetime    not null comment '创建时间',
    edit_time   datetime    not null comment '编辑时间',
    creator_id  BIGINT      NOT NULL comment '创建人用户id',
    editor_id   BIGINT      NOT NULL comment '修改人用户id',
    creator     varchar(32) not null comment '创建人',
    editor      varchar(32) not null comment '修改人',
    rec_time    timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX       permission_parentId (parent_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='权限';

CREATE TABLE role_permission
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    role_id       BIGINT      NOT NULL comment '角色id',
    permission_id BIGINT      NOT NULL comment '权限id',
    create_time   datetime    not null comment '创建时间',
    edit_time     datetime    not null comment '编辑时间',
    creator_id    BIGINT      NOT NULL comment '创建人用户id',
    editor_id     BIGINT      NOT NULL comment '修改人用户id',
    creator       varchar(32) not null comment '创建人',
    editor        varchar(32) not null comment '修改人',
    rec_time      timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX         permission_role_id (role_id),
    INDEX         permission_permission_id (permission_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='角色权限关联表';


CREATE TABLE user_permission
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY comment '主键',
    user_id       BIGINT      NOT NULL comment '用户id',
    permission_id BIGINT      NOT NULL comment '权限id',
    create_time   datetime    not null comment '创建时间',
    edit_time     datetime    not null comment '编辑时间',
    creator_id    BIGINT      NOT NULL comment '创建人用户id',
    editor_id     BIGINT      NOT NULL comment '修改人用户id',
    creator       varchar(32) not null comment '创建人',
    editor        varchar(32) not null comment '修改人',
    rec_time      timestamp   not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '时间戳',
    INDEX         permission_role_id (user_id),
    INDEX         permission_permission_id (permission_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户权限关联表';

create table core_user
(
    id           bigint auto_increment comment '主键'
        primary key,
    union_id     varchar(32)  default null comment '微信开放平台账号下的唯一标识',
    open_id      varchar(32)  default null comment '微信小程序open_id',
    nickname     varchar(128)                           not null comment '昵称',
    name         varchar(128) default '游客' comment '用户姓名',
    phone_number varchar(32)  default '' comment '手机号',
    gender       tinyint(1) default 1 not null comment '性别|0-女，1-男',
    avatar       bigint       default null comment '头像|file.id',
    status       int          default 1                 not null comment '状态|0-停用，1-激活',
    creator_id   bigint                                 not null comment '创建人用户id',
    editor_id    bigint                                 not null comment '修改人用户id',
    create_time  datetime                               not null comment '创建时间',
    edit_time    datetime                               not null comment '编辑时间',
    creator      varchar(32)                            not null comment '创建人',
    editor       varchar(32)                            not null comment '修改人',
    rec_time     timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX user_open_id (open_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='前台用户';

create table sys_config
(
    id          bigint auto_increment comment '主键'
        primary key,
    config_key  varchar(32)                         not null comment '配置key',
    value       text                                not null comment '配置内容',
    creator_id  bigint                              not null comment '创建人用户id',
    editor_id   bigint                              not null comment '修改人用户id',
    create_time datetime                            not null comment '创建时间',
    edit_time   datetime                            not null comment '编辑时间',
    creator     varchar(32)                         not null comment '创建人',
    editor      varchar(32)                         not null comment '修改人',
    rec_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX sys_config_config_key (config_key)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='配置表';

create table massage_class
(
    id          bigint auto_increment comment '主键'
        primary key,
    title       varchar(32) not null comment '分类名称',
    description varchar(128)         default null comment '分类描述',
    file_id     bigint      not null comment '封面图片',
    state       int         not null default 1 comment '状态|0-停用，1-启用',
    creator_id  bigint      not null comment '创建人用户id',
    editor_id   bigint      not null comment '修改人用户id',
    create_time datetime    not null comment '创建时间',
    edit_time   datetime    not null comment '编辑时间',
    creator     varchar(32) not null comment '创建人',
    editor      varchar(32) not null comment '修改人',
    rec_time    timestamp            default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX massage_class_title (title),
    INDEX       massage_class_create_time (create_time),
    INDEX       massage_class_edit_time (edit_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='按摩分类';


create table massage_project
(
    id               bigint auto_increment comment '主键'
        primary key,
    project_name     varchar(32)                            not null comment '项目名称',
    project_desc     varchar(128) default null comment '分类描述',
    massage_class_id bigint       default null comment '按摩分类id|massage_class.id',
    duration         bigint                                 not null comment '按摩时长',
    file_id          bigint                                 not null comment '封面图片',
    creator_id       bigint                                 not null comment '创建人用户id',
    editor_id        bigint                                 not null comment '修改人用户id',
    create_time      datetime                               not null comment '创建时间',
    edit_time        datetime                               not null comment '编辑时间',
    creator          varchar(32)                            not null comment '创建人',
    editor           varchar(32)                            not null comment '修改人',
    rec_time         timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX massage_project_project_name (project_name),
    INDEX            massage_project_create_time (create_time),
    INDEX            massage_project_edit_time (edit_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='按摩项目';

create table massage_staff
(
    user_id     bigint                                 not null comment '主键，员工工号|user.id' primary key,
    staff_desc  varchar(128) default null comment '从业人员描述',
    score DOUBLE comment '评分',
    creator_id  bigint                                 not null comment '创建人用户id',
    editor_id   bigint                                 not null comment '修改人用户id',
    create_time datetime                               not null comment '创建时间',
    edit_time   datetime                               not null comment '编辑时间',
    creator     varchar(32)                            not null comment '创建人',
    editor      varchar(32)                            not null comment '修改人',
    rec_time    timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX massage_staff_project_name (user_id),
    INDEX       massage_staff_create_time (create_time),
    INDEX       massage_staff_edit_time (edit_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='按摩从业人员';

create table massage_project_staff_relation
(
    id          bigint auto_increment comment '主键'
        primary key,
    staff_id    bigint                              not null comment '员工工号|massage_staff.user_id',
    project_id  bigint                              not null comment '项目id|massage_project.id',
    creator_id  bigint                              not null comment '创建人用户id',
    editor_id   bigint                              not null comment '修改人用户id',
    create_time datetime                            not null comment '创建时间',
    edit_time   datetime                            not null comment '编辑时间',
    creator     varchar(32)                         not null comment '创建人',
    editor      varchar(32)                         not null comment '修改人',
    rec_time    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX massage_project_staff_sid_pid (staff_id, project_id),
    INDEX       massage_staff_create_time (create_time),
    INDEX       massage_staff_edit_time (edit_time),
    INDEX       massage_staff_project_id (project_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='按摩从业人员与项目关联表';

create table core_massage_reserve
(
    id           bigint auto_increment comment '主键'
        primary key,
    core_user_id bigint      not null comment '用户id|core_user.id',
    staff_id     bigint      not null comment '员工工号|massage_staff.user_id',
    project_id   bigint      not null comment '项目id|massage_project.id',
    reserve_time datetime    not null comment '预约时间',
    state        int         not null default 1 comment '预约状态|1-生效，2-执行中，3-完成，4-取消',
    creator_id   bigint      not null comment '创建人用户id',
    editor_id    bigint      not null comment '修改人用户id',
    create_time  datetime    not null comment '创建时间',
    edit_time    datetime    not null comment '编辑时间',
    creator      varchar(32) not null comment '创建人',
    editor       varchar(32) not null comment '修改人',
    rec_time     timestamp            default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '时间戳',
    UNIQUE INDEX reserve_core_user_id (core_user_id),
    INDEX        reserve_create_time (create_time),
    INDEX        reserve_edit_time (edit_time),
    INDEX        reserve_staff_id (staff_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 comment ='用户预约表';