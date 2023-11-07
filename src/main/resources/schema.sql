DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

create table roles
(
    id          bigint auto_increment
        primary key,
    code        varchar(50)  not null,
    description varchar(500) null
);

create table users
(
    id           bigint auto_increment
        primary key,
    display_name varchar(100) null,
    password     varchar(60)  null,
    user_name    varchar(50)  null
);


create table user_role
(
    role_id bigint not null,
    user_id bigint not null,
    primary key (user_id, role_id),
    constraint FKj345gk1bovqvfame88rcx7yyx
        foreign key (user_id) references users (id),
    constraint FKt7e7djp752sqn6w22i6ocqy6q
        foreign key (role_id) references roles (id)
);
