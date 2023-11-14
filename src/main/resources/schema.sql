DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

create table roles
(
    role_id     bigint auto_increment
        primary key,
    create_by   varchar(50)        null,
    create_date datetime(3)        null,
    state_void  smallint default 0 not null,
    update_by   varchar(50)        null,
    update_date datetime(3)        null,
    code        varchar(50)        not null,
    description varchar(500)       null
);

create table users
(
    user_id        bigint auto_increment
        primary key,
    create_by      varchar(50)        null,
    create_date    datetime(3)        null,
    state_void     smallint default 0 not null,
    update_by      varchar(50)        null,
    update_date    datetime(3)        null,
    display_name   varchar(100)       null,
    password       varchar(60)        null,
    user_name      varchar(50)        null,
    constraint UK_k8d0f2n7n88w1a16yhua64onx
        unique (user_name)
);


create table user_role
(
    role_id bigint not null,
    user_id bigint not null,
    primary key (user_id, role_id),
    constraint FKj345gk1bovqvfame88rcx7yyx
        foreign key (user_id) references users (user_id),
    constraint FKt7e7djp752sqn6w22i6ocqy6q
        foreign key (role_id) references roles (role_id)
);
DROP TABLE IF EXISTS fr_log;
create table fr_log
(
    fr_log_id   bigint auto_increment
        primary key,
    create_by   varchar(50)                   null,
    create_date datetime(3)                   null,
    state_void  smallint default 0            not null,
    update_by   varchar(50)                   null,
    update_date datetime(3)                   null,
    client_ip   varchar(20)                   null,
    detail_msg  varchar(4000) charset utf8mb3 null,
    end_date    datetime(3)                   not null,
    error_msg   varchar(4000) charset utf8mb3 null,
    host_name   varchar(20)                   null,
    msg         varchar(4000) charset utf8mb3 null,
    msg_type    varchar(20)                   not null,
    object_name varchar(250)                  not null,
    person_uid  int                           not null,
    start_date  datetime(3)          CONVERT_TZ(NOW(), 'UTC', 'GMT')         not null,
    unid        varchar(250)                  not null,
    user_logon  varchar(250)                  not null
);



