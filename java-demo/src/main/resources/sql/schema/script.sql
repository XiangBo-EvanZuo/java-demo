create table skin.tb_wang_menu
(
    id               int auto_increment
        primary key,
    path             varchar(30)   null,
    parent_id        varchar(30)   null,
    parent_name      varchar(30)   null,
    title            varchar(30)   null,
    deleted          int default 0 null,
    `order`          int default 0 null,
    sub_hide_menu_id varchar(30)   null,
    constraint id
        unique (id)
);

create table skin.tb_wang_role
(
    id        int auto_increment
        primary key,
    role_name varchar(30) null,
    constraint id
        unique (id)
);

create table skin.tb_wang_role_menu_relation
(
    role_id int null,
    menu_id int null
);

create table skin.tb_wang_skin
(
    price      decimal      null,
    name       varchar(30)  null,
    is_deleted int          null,
    time       datetime     null,
    img        varchar(500) null,
    id         int auto_increment
        primary key,
    type       int          null,
    detailId   int          null,
    constraint id
        unique (id)
);

create table skin.tb_wang_skin_detail
(
    id          int auto_increment
        primary key,
    description varchar(100) null,
    constraint id
        unique (id)
);

create table skin.tb_wang_user
(
    id                      int auto_increment
        primary key,
    username                varchar(30)   null,
    mobile                  varchar(30)   null,
    pwd                     varchar(230)  null,
    deleted                 int default 0 null,
    version                 int default 0 null,
    avatar_url              varchar(100)  null,
    enabled                 int default 1 null,
    credentials_non_expired int default 1 null,
    account_non_expired     int default 1 null,
    account_non_locked      int default 1 null,
    column_12               int           null,
    constraint id
        unique (id)
);

create table skin.tb_wang_user_role_relation
(
    user_id int null,
    role_id int null
);


