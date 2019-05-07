create table dept
(
  deptno    bigint auto_increment comment '主键'
    primary key,
  dname     varchar(60) null comment '部门名称',
  db_source varchar(60) null comment '所属数据库'
);

insert into dept(dname, db_source) values ('开发部', database()); -- database()获取当前数据库名称
insert into dept(dname, db_source) values ('人事部', database());
insert into dept(dname, db_source) values ('财务部', database());
insert into dept(dname, db_source) values ('市场部', database());
insert into dept(dname, db_source) values ('运营部', database());