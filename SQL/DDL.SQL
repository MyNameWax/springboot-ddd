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
) comment '用户表'
