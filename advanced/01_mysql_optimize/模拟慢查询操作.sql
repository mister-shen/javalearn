/**************mysql初始化测试数据*************/
/*******创建表结构******/
/*部门表*/
CREATE TABLE dept( 
deptno MEDIUMINT   UNSIGNED  NOT NULL  DEFAULT 0,  /*编号*/
dname VARCHAR(20)  NOT NULL  DEFAULT "", /*名称*/
loc VARCHAR(13) NOT NULL DEFAULT "" /*地点*/
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ;
/*员工表*/
CREATE TABLE emp
(empno  MEDIUMINT UNSIGNED  NOT NULL  DEFAULT 0, /*编号*/
ename VARCHAR(20) NOT NULL DEFAULT "", /*名字*/
job VARCHAR(9) NOT NULL DEFAULT "",/*工作*/
mgr MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,/*上级编号*/
hiredate DATE NOT NULL,/*入职时间*/
sal DECIMAL(7,2)  NOT NULL,/*薪水*/
comm DECIMAL(7,2) NOT NULL,/*红利*/
deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 /*部门编号*/
)ENGINE=MyISAM DEFAULT CHARSET=utf8 ;



/*薪水*/
CREATE TABLE salgrade
(
grade MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
losal DECIMAL(17,2)  NOT NULL,
hisal DECIMAL(17,2)  NOT NULL
)ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*测试数据*/

INSERT INTO salgrade VALUES (1,700,1200);
INSERT INTO salgrade VALUES (2,1201,1400);
INSERT INTO salgrade VALUES (3,1401,2000);
INSERT INTO salgrade VALUES (4,2001,3000);
INSERT INTO salgrade VALUES (5,3001,9999);


/*******创建函数******/
create function rand_string(n INT) 
returns varchar(255) #该函数会返回一个字符串
begin 
#chars_str定义一个变量 chars_str,类型是 varchar(100),默认值'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
 declare chars_str varchar(100) default
   'abcdefghijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ';
 declare return_str varchar(255) default '';
 declare i int default 0;
 while i < n do 
   set return_str =concat(return_str,substring(chars_str,floor(1+rand()*52),1));
   set i = i + 1;
   end while;
  return return_str;
  end;

create FUNCTION rand_num()
RETURNS int(5)
BEGIN
 DECLARE i int default 0;
 set i =floor(10+RAND()*500);
 return i;
END;

/*******创建存储过程******/
delimiter $$
create procedure insert_emp(in start int(10),in max_num int(10))
begin
declare i int default 0; 
#set autocommit =0 把autocommit设置成0
 set autocommit = 0;  
 repeat
 set i = i + 1;
 insert into emp values ((start+i) ,rand_string(6),'SALESMAN',0001,curdate(),2000,400,rand_num());
  until i = max_num
 end repeat;
   commit;
 end $$
/*执行存储过程*/
call insert_emp (100001,40000000);  


/**************慢查询常用命令*************/

/*mysql数据库启动了多少时间*/
show status like 'uptime';

/*显示数据库的查询，更新，添加，删除的次数 类推 update  delete*/
show status like 'com_select';
show status like 'com_insert';

/*show [session|global] status like .... 
如果你不写  [session|global] 默认是session 会话，指取出当前窗口的执行，
如果你想看所有(从mysql 启动到现在，则应该 global)*/
show global status like 'com_insert' ;

/*显示到mysql数据库的连接数*/
show status like  'connections '; 

/*显示慢查询次数*/
show status like 'slow_queries';

/*查询慢查询时间*/
show variables like 'long_query_time';

/*修改慢查询时间
	但是重启mysql之后，long_query_time依然是my.ini中的值
*/
set long_query_time=1; 

/*查询slow_query_log日志所在位置*/
show variables  like '%slow_query_log%';


SELECT * FROM emp LIMIT 1, 40000