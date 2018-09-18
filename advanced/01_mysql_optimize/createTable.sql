--建表语句
create table user0(
id int unsigned primary key ,
name varchar(32) not null default '',
pwd  varchar(32) not null default '')
engine=myisam charset utf8;

create table user1(
id int unsigned primary key ,
name varchar(32) not null default '',
pwd  varchar(32) not null default '')
engine=myisam charset utf8;

create table user2(
id int unsigned primary key ,
name varchar(32) not null default '',
pwd  varchar(32) not null default '')
engine=myisam charset utf8;


create table uuid(
id int unsigned primary key auto_increment)engine=myisam charset utf8;