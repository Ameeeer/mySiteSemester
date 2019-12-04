create table mySiteUsers
(
    id            serial primary key,
    Email         varchar(255) not null unique,
    Login         varchar(255) not null,
    Password      varchar(255) not null,
    country       varchar(100) not null,
    infoAboutUser varchar(255)
);
create table roles
(
    id       serial primary key,
    roleName varchar(30)
);
create table usrs_with_roles
(
    userID integer ,
    roleID integer ,
    foreign key (roleID) references roles (id) on delete cascade,
    foreign key (userID) references mySiteUsers (id) on delete cascade
);
drop table roles;
drop sequence roles_id_seq;
insert into roles(roleName)
values ('Admin');
insert into roles(roleName)
values ('simpleUser');
insert into roles(roleName)
values ('Helper');
insert into roles(roleName)
values ('Moderator');
insert into roles(roleName)
values ('Jesus');
insert into roles(roleName)
values ('Lecturer');
delete
from mySiteUsers
where id >= 1;
select * from roles join usrs_with_roles uwr on roles.id = uwr.roleID where userID = 11;
create sequence id_seq
    minvalue 1
    cache 1
    increment 1;
alter table only mySiteUsers
    alter column id set default nextval('id_seq'::regclass);
select Email, Login, Password
from mySiteUsers
order by Login;
delete
from mySiteUsers
where id >= 1;
select *
from mySiteUsers;
drop table mySiteUsers,roles,usrs_with_roles