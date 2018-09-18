###一、MySQL数据库优化专题
1. mysql 如何优化
     * 表的设计合理化(符合3NF)
     * 分表技术(水平分割、垂直分割)
     * SQL语句优化
     * 添加适当索引(index) [四种: 普通索引、主键索引、唯一索引unique、全文索引]
     * 读写[写: update/delete/add]分离
     * 存储过程 [模块化编程，可以提高速度]
     * 对mysql配置优化 [配置最大并发数my.ini或my.cnf, 调整缓存大小 ]
     * mysql服务器硬件升级
     * 定时的去清除不需要的数据,定时进行碎片整理(MyISAM)
2. 数据库的三大范式
    * 第一范式：1NF是对属性的原子性约束，要求属性(列)具有原子性，不可再分解；
    * 第二范式：2NF是对记录的惟一性约束，表中的记录是唯一的, 比如设计一个主键来实现，主键不能包含业务逻辑。
    * 第三范式：3NF是对字段冗余性的约束，它要求字段没有冗余。
    
3. 分库分表
    1. 垂直拆分：表按模块划分到不同数据库表
    2. 水平拆分：把一个表按照某种规则把数据划分到不同表或数据库里。
    如：使用取摸方式分表
    * 创建三张表 user0 / user1 /user2 存储数据,创建 uuid表提供自增的id。
     ```
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
     ```
    * 实现取模分表（实例地址：[01_mysql_optimize](https://github.com/mister-shen/javalearn/blob/master/advanced/01_mysql_optimize)） 

4. SQL优化
    * 定位慢查询
        1. 常用命令
            * show status：查看MySQL服务器状态信息
            ```
                --mysql数据库启动了多少时间
                show status like 'uptime';
                
                show status like 'com_select'  
                show status like 'com_insert' ...
                类推 update  delete（显示数据库的查询，更新，添加，删除的次数）
                
                show [session|global] status like .... 
                如果你不写  [session|global] 默认是session 会话，指取出当前窗口的执行，
                如果你想看所有(从mysql 启动到现在，则应该 global)
                
                //显示到mysql数据库的连接数
                show status like  'connections '; 
                
                //显示慢查询次数
                show status like 'slow_queries';
            ```
            * 慢查询：MySQL默认10秒内没有响应SQL结果,则为慢查询
            ```
                --查询慢查询时间
                show variables like 'long_query_time';
                
                --修改慢查询时间
                set long_query_time=1; 
                ---但是重启mysql之后，long_query_time依然是my.ini中的值
                
                --查询慢查询日志所在目录
                show variables  like '%slow_query_log%';
                
                --log_output 参数是指定日志的存储方式。
                show variables like '%log_output%';
                
                --MySQL数据库支持同时两种日志存储方式，配置的时候以逗号隔开即可，
                --如：log_output='FILE,TABLE'。日志记录到日志表（mysql.slow_log）更耗费内存
                set global log_output='TABLE';
                
            ```
        2. 设置慢查询方法
        
            方法一：全局变量设置（重启数据库服务失效）
            将 slow_query_log 全局变量设置为“ON”状态
            ```
            mysql> set global slow_query_log='ON'; 
            ```
            设置慢查询日志存放的位置
            ```
            mysql> set global slow_query_log_file='/usr/local/mysql/data/slow.log';
            ```
            查询超过1秒就记录
            ```
            mysql> set global long_query_time=1;
            ```
            方法二：配置文件设置（永久性）
            修改配置文件 ```vi /etc/my.cnf```，在[mysqld]下的下方加入
            ```
            [mysqld]
            slow_query_log = ON
            slow_query_log_file = /usr/local/mysql/data/slow.log
            long_query_time = 1
            ```
            重启MySQL服务
            
            ```service mysqld restart```
        3. [慢查询详细操作地址](https://www.cnblogs.com/luyucheng/p/6265594.html)
           [更多的慢查询相关信息](https://blog.csdn.net/shukebai/article/details/70064287)
        4. 相关工具 
        
                mysqldumpslow[mysql自带]
                pt-query-digest[需单独安装，功能比mysqldumpslow多]