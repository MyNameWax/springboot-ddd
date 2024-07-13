CREATE DATABASE ddd;

USE ddd;

CREATE TABLE user
(
    id       bigint primary key not null auto_increment comment 'ID主键',
    username varchar(80)        not null comment '用户名',
    password varchar(80)        not null comment '密码',
    email    varchar(80)        not null comment '邮箱',
    address  varchar(80)        not null comment '地址',
    avatar   varchar(80)        not null comment '头像'
) comment '用户表';


CREATE TABLE role
(
    id     bigint primary key not null auto_increment comment 'ID主键',
    code   varchar(80)        not null comment '角色编码',
    name   varchar(80)        not null comment '角色名',
    `desc` varchar(80)        not null comment '描述'
) comment '角色表';

create table user_role
(
    id   bigint primary key not null auto_increment comment 'ID主键',
    u_id bigint             not null comment '用户ID',
    r_id bigint             not null comment '角色ID'
) comment '用户角色关联表';

create table role_menu
(
    id      bigint primary key not null auto_increment comment 'ID',
    role_id bigint             not null comment '角色ID',
    menu_id bigint             not null comment '菜单ID'
) comment '角色菜单表';

create table menu
(
    id             bigint primary key auto_increment not null comment 'ID',
    parent_id      bigint                            not null default 0 comment '父ID',
    menu_name      varchar(50)                       not null comment '菜单名称',
    sort           tinyint                           not null comment '排序',
    menu_type      tinyint                           not null comment '菜单类型(0:目录 1:菜单 2:按钮)',
    path           varchar(255)                      not null comment '路径',
    component_path varchar(255)                      not null comment '组件路径',
    perms          varchar(255) comment '权限',
    icon           varchar(255) comment '图标'
) comment '菜单';
