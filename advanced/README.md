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
                show status like  'connections'; 
                
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
5. 索引

        索引用来快速地寻找那些具有特定值的记录，所有MySQL索引都以B-树的形式保存。
    * 索引的分类
        * 查询索引
            ```
                desc  表名;   不能显示索引名称
                show index from 表名
                show keys from 表名
                explain 查询语句 -- 查询是否使用了索引
            ```
        * 主键索引
         
                当一张表，把某个列设为主键的时候，则该列就是主键索引。
             ``` 
               创建主键索引方式：
                a. 创建表添加：
                    CREATE TABLE tablename ( [...], PRIMARY KEY (列的列表) );
                b. 修改表添加：
                    ALTER TABLE tablename ADD PRIMARY KEY (列的列表);
             ```
        * 全文索引
            ```
                desc articles; -- 查询表详细信息
                show index from articles; -- 查询表的索引
                show keys from articles; 
                
                -- 创建带全文索引的表
                CREATE TABLE articles (
                       id INT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
                       title VARCHAR(200),
                       body TEXT,
                       FULLTEXT (title,body)
                     )engine=myisam charset utf8;
                
                -- 插入数据
                INSERT INTO articles (title,body) VALUES
                     ('MySQL Tutorial','DBMS stands for DataBase ...'),
                     ('How To Use MySQL Well','After you went through a ...'),
                     ('Optimizing MySQL','In this tutorial we will show ...'),
                     ('1001 MySQL Tricks','1. Never run mysqld as root. 2. ...'),
                     ('MySQL vs. YourSQL','In the following database comparison ...'),
                     ('MySQL Security','When configured properly, MySQL ...');
                
                -- 正确的使用全文索引查询
                select * from articles where match(title,body) against ( 'database')
                
                -- 输出的是每行和database的匹配度
                select match(title,body) against ('database') from articles;
            
            ```
                注意：
                    在mysql中fulltext 索引只针对 myisam 生效
                    mysql自己提供的fulltext针对英文生效->sphinx (coreseek) 技术处理中文
                    使用方法是 match(字段名..) against(‘关键字’)
                    全文索引：停止词,  因为在一个文本中，创建索引是一个无穷大的数，因此，对一
                    些常用词和字符，就不会创建，这些词，称为停止词.比如（a，b，mysql，the）
            
        * 唯一索引
            
                索引列的所有值都只能出现一次，即必须唯一。
            ``` 
              创建唯一索引方式：
                a. 创建表添加：
                    CREATE TABLE tablename ( [...], UNIQUE [索引的名字] (列的列表) );
                b. 修改表添加：
                    ALTER TABLE tablename ADD UNIQUE [索引的名字] (列的列表)；
            ```
        * 普通索引
            
                普通索引（由关键字KEY或INDEX定义的索引）的唯一任务是加快对数据的访问速度。
            ```创建索引：create index 索引名 on 表 (列1,列名2);```
    * 索引的实现原理
        ![索引的实现原理](https://github.com/mister-shen/javalearn/blob/master/advanced/01_mysql_optimize/image/索引原理1.png)
        ![索引的实现原理](https://github.com/mister-shen/javalearn/blob/master/advanced/01_mysql_optimize/image/索引原理2.png)
            
        * 应该创建索引的列：
        
            1. 在`经常需要搜索`的列上，可以加快搜索的速度；
            2. 在作为`主键`的列上，强制该列的唯一性和组织表中数据的排列结构；
            3. 在`经常用在连接`的列上，这些列主要是一些`外键`，可以加快连接的速度；
            4. 在`经常需要根据范围进行搜索的列`上创建索引，因为索引已经排序，其指定的范围是连续的；
            5. 在`经常需要排序的列`上创建索引，因为索引已经排序，这样查询可以利用索引的排序，加快排序查询时间；
            6. 在`经常使用在 WHERE 子句中的列`上面创建索引，加快条件的判断速度。
            
        * 不应该创建索引的列：
            1. 对于那些在`查询中很少使用或者参考的列`不应该创建索引。
            2. 对于那些`只有很少数据值的列`也不应该增加索引。
            3. 对于那些`定义为 text, image 和 bit 数据类型的列`不应该增加索引。
            4. 当`修改性能远远大于检索性能`时，不应该创建索引。
                                  
    * 索引的代价
        1. 占用磁盘空间
        2. 对DML(update、delete、insert)语句的效率影响
        3. 增删改会对索引影响，因为索引要重新整理。      

    | 存储引擎 | 允许的索引类型 |
    |--------|--------|
    |myisam  |btree   |
    |innodb  |btree   |
    |memory/yeap  |Hash,btree   |
    
        * 适合添加索引的列
            1. 肯定在`where`条件经常使用
            2. 该字段的内容不是唯一的几个值
            3. 字段内容不是频繁变化

    * 索引的注意事项
    
            创建主键索引
            alter table 表名 add primary key (列名);
            创建一个联合索引
            alter table dept add index my_ind (dname,loc); //  dname 左边的列,loc就是右边的列
        
        1. 对于创建的多列索引，如果不是使用第一部分，则不会创建索引。
        explain select * from dept where loc='aaa' 就不会使用到索引
        2. 模糊查询在like前面有百分号开头会失效。
        3. 如果条件中有or，即使其中有条件带索引也不会使用。换言之，
        就是要求使用的所有字段，都必须建立索引, 我们建议大家尽量避免使用or关键字。
        4. 如果列类型是字符串，那一定要在条件中将数据使用引号引用起来。
        否则不使用索引。(添加时,字符串必须’’), 也就是，如果列是字符串类型，
        就一定要用 ‘’ 把他包括起来.
        5. 如果mysql估计使用全表扫描要比使用索引快，则不使用索引。
    * 查询索引使用率：
        ```
        show status like ‘handler_read%’;
        ```
        
            注意：
            handler_read_key:这个值越高越好，越高表示使用索引查询到的次数。
            handler_read_rnd_next:这个值越高，说明查询低效。
    * SQL优化技巧
        1. 使用group by 分组查询是，默认分组后，还会排序，可能会降低速度，
           在group by 后面增加 order by null 就可以防止排序.
           explain select * from emp  group by deptno order by null;
        2. 有些情况下，可以使用连接来替代子查询。因为使用join，MySQL不需要在内存中创建临时表。
            ```
            select * from dept, emp where dept.deptno=emp.deptno; [简单处理方式]
            select * from dept left join emp on dept.deptno=emp.deptno;  [左外连接，更ok!]
            ```
        3. 对查询进行优化，要尽量避免全表扫描，首先应考虑在 where 及 order by 涉及的列上建立索引
           应尽量避免在 where 子句中对字段进行 null 值判断，否则将导致引擎放弃使用索引而进行全表扫描，
        4.  where 子句中使用 != 或 <> 操作符,将引擎放弃使用索引而进行全表扫描。
        5. where 子句中使用 or 来连接条件，如果一个字段有索引，一个字段没有索引，将导致引擎放弃使用索引而进行全表扫描.
            ```
            如：未使用索引查询：select id from t where num=10 or Name = ‘admin’
              使用索引查询：
              select id from t where num = 10
              union all
              select id from t where Name = ‘admin’
            ```
        6. in 和 not in 也要慎用，否则会导致全表扫描，对于连续的数值，
            能用 between 就不要用 in 了，很多时候用 exists 代替 in 是一个好的选择。
        7. 如果在 where 子句中使用参数，也会导致全表扫描。
            因为 SQL 只有在运行时才会解析局部变量，但优化程序不能将访问计划的选择
            推迟到运行时；它必须在编译时进行选择。然而，如果在编译时建立访问计划，
            变量的值还是未知的，因而无法作为索引选择的输入项。
            ```
            如下面语句将进行全表扫描：
            
            select id from t where num = @num
            
            可以改为强制查询使用索引：
            
            select id from t with(index(索引名)) where num = @num
            
            应尽量避免在 where 子句中对字段进行表达式操作，这将导致引擎放弃使用索引而进行全表扫描。如：
            
            select id from t where num/2 = 100
            
            应改为:
            
            select id from t where num = 100*2
            ```
        8. 应尽量避免在where子句中对字段进行函数操作，这将导致引擎放弃使用索引而进行全表扫描。
           ```
           如：
           select id from t where substring(name,1,3) = ’abc’ -–name 以 abc 开头的 id select id from t where datediff(day,createdate,’2005-11-30′) = 0 -–‘2005-11-30’ –生成的 id
           
           应改为:
           
           select id from t where name like ‘abc%’
           select id from t where createdate >= ‘2005-11-30’ and createdate < ‘2005-12-1’
           ```
        9. `不要`在`where`子句中的“=”`左边进行函数、算术运算或其他表达式运算`，否则系统将可能无法正确使用索引。
        10. 在使用索引字段作为条件时，如果该索引是复合索引，那么必须使用到该索引中的第一个字段作为条件时才能保证系统使用该索引，
        否则该索引将不会被使用，并且应尽可能的让字段顺序与索引顺序相一致。
        11. Update 语句，如果只更改 1、2 个字段，不要 Update 全部字段，否则频繁调用会引起明显的性能消耗，同时带来大量日志。
        12. 对于多张大数据量（这里几百条就算大了）的表 JOIN，要先分页再 JOIN，否则逻辑读会很高，性能很差。
        13. select count(*) from table；这样不带任何条件的 count 会引起全表扫描，并且没有任何业务意义，是一定要杜绝的。
        14. 索引并不是越多越好，索引固然可以提高相应的select的效率，但同时也降低了insert及update的效率，
        因为insert或update时有可能会重建索引，所以怎样建索引需要慎重考虑，视具体情况而定。一个表的索引数最
        好不要超过 6 个，若太多则应考虑一些不常使用到的列上建的索引是否有必要。
        15. 尽量使用数字型字段，若只含数值信息的字段尽量不要设计为字符型，这会降低查询和连接的性能，并会增加存储开销。
        这是因为引擎在处理查询和连 接时会逐个比较字符串中每一个字符，而对于数字型而言只需要比较一次就够了。
        16. 尽可能的使用 varchar/nvarchar 代替 char/nchar ，因为首先变长字段存储空间小，可以节省存储空间，其次对于查询来说，
        在一个相对较小的字段内搜索效率显然要高些。
        17. 任何地方都不要使用 select * from t ，用具体的字段列表代替“*”，不要返回用不到的任何字段。
        18. 
    * MySQL数据引擎
        
            使用的存储引擎 myisam / innodb/ memory
            myisam 存储: 如果表对事务要求不高，同时是以查询和添加为主的，我们考虑使用myisam存储引擎,
            INNODB 存储: 对事务要求高，保存的数据都是重要数据，我们建议使用INNODB,比如订单表，账号表.
            MyISAM 和 INNODB的区别
                1. 事务安全（MyISAM不支持事务，INNODB支持事务）
                2. 查询和添加速度（MyISAM批量插入速度快）
                3. 支持全文索引（MyISAM支持全文索引，INNODB不支持全文索引）
                4. 锁机制（MyISAM时表锁，innodb是行锁）
                5. 外键 MyISAM 不支持外键， INNODB支持外键. (在PHP开发中，通常不设置外键，通常是在程序中保证数据的一致)
            Memory 存储，比如我们数据变化频繁，不需要入库，同时又频繁的查询和修改，我们考虑使用memory, 速度极快. （如果mysql重启的话，数据就不存在了）
    ![MySQL引擎](https://github.com/mister-shen/javalearn/blob/master/advanced/01_mysql_optimize/image/mysql引擎.png)

    * Myisam注意事项
    
            如果你的数据库的存储引擎是myisam,请一定记住要定时进行碎片整理
            举例说明: 
            create table test100(id int unsigned ,name varchar(32))engine=myisam;
            insert into test100 values(1,’aaaaa’);
            insert into test100 values(2,’bbbb’);
            insert into test100 values(3,’ccccc’);
            insert into test100 select id,name from test100;
            我们应该定义对myisam进行整理
            optimize table test100;
    
6. MySQL主从复制
    [CentOS 7 MySql 5.6 主从复制](https://mp.csdn.net/mdeditor/82791305#)
7. MySQL读写分离

        什么是读写分离？
            主库负责处理事务性查询，而从库只负责处理select查询，让两者分工明确达到提高数据库整体读写性能。
            主数据库另外一个功能就是负责将事务性查询导致的数据变更同步到从库中，也就是写操作。
        
        读写分离的好处
            1. 分摊服务器压力，提高机器的系统处理效率
            2. 增加冗余，提高服务可用性，当一台数据库服务器宕机后可以调整另外一台从库以最快速度恢复服务
    * MyCat 实现读写分离
        
        [Mycat简单Demo](https://www.jianshu.com/p/2d1a81b2dafc)
        
        [MyCat安装与使用](https://github.com/MyCATApache/Mycat-Server/wiki/2.0-Mycat%E5%AE%89%E8%A3%85%E4%B8%8E%E4%BD%BF%E7%94%A8)
        