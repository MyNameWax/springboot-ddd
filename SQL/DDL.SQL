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
