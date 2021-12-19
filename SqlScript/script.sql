create table admin
(
    username varchar(254) null,
    password varchar(254) null
);

create table employee
(
    name        varchar(254) null,
    age         int          null,
    sex         varchar(254) not null,
    id          int auto_increment
        primary key,
    base_salary double       null,
    bonus       double       null,
    sum         double       null,
    apartment   varchar(254) null,
    position    varchar(254) null
);

create table user
(
    e_user     varchar(254) null,
    e_password varchar(254) null
);


