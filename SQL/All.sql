-- 各数据库数据类型说明：http://www.w3school.com.cn/sql/sql_datatypes.asp


-- 查询数据库表内不重复的记录
SELECT DISTINCT c_name FROM t_person;

-- 查询前10条记录(SQL Server)
SELECT top 10 * FROM t_person;
-- 查询前10%条记录(SQL Server)
SELECT top 10 percent * FROM t_person;

-- not like
select * from t_person where c_name not like '%lon%';
-- 下划线代表一个字符
select * from t_person where c_name like '_ouchongj_n';
-- 查询名字以z或x开头的人
select * from t_person where c_name like '[zx]%';
-- 查询名字不以z或x开头的人
select * from t_person where c_name like '[!zx]%';
-- in
select * from t_person where c_name in ('zou','xu');
-- between
select * from t_person where c_name between 'a' and 'z';
-- not between
select * from t_person where c_name not between 'a' and 'z';

-- inner join : 同等于join : 查询匹配的行
-- left join : 有些数据库用left outer join : 查询匹配行+左表所有行
-- right join : 有些数据库用right outer join : 查询匹配行+右表所有行
-- full join : 有些数据库用full outer join : 查询匹配行+左表所有行+右表所有行

-- union : 和并多个 select 语句的结果集，去掉相同部分
select c_name from t_person
union
select c_name from t_person2
-- union all : 和并多个 select 语句的结果集，不去掉相同部分
select c_name from t_person
union all
select c_name from t_person2

-- MySql 输出值处理
SELECT id, name, sex 'xb', (CASE status WHEN 0 THEN '无效' ELSE '有效' END) 'status' FROM t_student

create table t_person 
(
	-- SQL Server / Oracle / MS Access
	id int not null primary key,
	code varchar(255) not null unique,
	age int not null check (age>0),
	sid int foreign key references t_school(id),
	city varchar(255) default 'sandnes',
	id int primary key identity,-- SQL Server：设置主键ID为自增：以 20 起始且递增 10，请把 identity 改为 identity(20,10)
	id int primary key autoincrement,-- MS Access：设置主键ID为自增：以 20 起始且递增 10，请把 autoincrement 改为 autoincrement(20,10)
	
	-- MySQL
	primary key (id),
	unique (code),
	foreign key (sid) references t_school(id),
	check (age>0),
	city varchar(255) default 'sandnes',
	id int not null auto_increment,-- 设置ID为自增
	
	-- 方法二：命名、多个约束
	constraint pk_person_id primary key (id),
	constraint uc_person_code unique (code),
	constraint fk_person_sid foreign key (sid) references t_school(id),
	constraint chk_person_age check (age>0 and city='sandnes')
)
-- 方法三：表外约束
alter table t_person add primary key (id);
alter table t_person add foreign key (sid) references t_school(id);
alter table t_person add check (age>0);
alter table t_person alter city set default 'sandnes';-- MySQL
alter table t_person alter column city set default 'sandnes';-- SQL Server / Oracle / MS Access
alter table t_person auto_increment=100;-- MySQL
-- 方法四：表外约束、可命名、可多个约束
alter table t_person add constraint pk_person_id primary key (id, id2);
alter table t_person add constraint uc_person_code unique (code, code2);
alter table t_person add constraint fk_person_sid foreign key (sid) references t_school(id);
alter table t_person add constraint chk_person_age check (age>0 and city='sandnes');
-- MySQL
alter table t_person drop primary key;
alter table t_person drop index uc_person_code;
alter table t_person drop foreign key fk_person_sid;
alter table t_person drop check chk_person_age;-- 不一定正确
alter table t_person alter city drop default;
-- SQL Server / Oracle / MS Access
alter table t_person drop constraint pk_person_id;
alter table t_person drop constraint uc_person_code;
alter table t_person drop constraint fk_person_sid;
alter table t_person drop constraint chk_person_age;
alter table t_person alter column city drop default;

-- 删除表中的内容
truncate table t_person;
-- 在表中添加列
alter table t_person add sex varchar(255);
-- 在表中删除列
alter table t_person drop column sex;
-- 在表中修改列类型
alter table t_person alter column sex varchar(255);
alter table t_person modify column sex varchar(255);-- mysql


-- 索引

	-- index：索引创建和删除
	-- unique index：唯一的索引意味着两个行不能拥有相同的索引值。
	ALTER TABLE t_person ADD INDEX index_person_code(code);-- 创建索引（MySQL）
	ALTER TABLE t_person ADD UNIQUE index_person_code(code);-- 创建唯一索引（MySQL）
	ALTER TABLE t_person DROP INDEX index_person_idcode;-- 删除索引（MySQL）
	
	create index index_person_idcode on t_person (id desc, code);
	create unique index index_person_idcode on t_person (id desc, code);
	drop index index_person_idcode;-- IBM DB2 / Oracle
	drop index t_person.index_person_idcode;-- SQL Server
	drop index index_person_idcode on t_person;-- MySQL / MS Access / Microsoft SQLJet
	
	SHOW INDEX FROM t_person; -- 查看索引


-- 视图

	CREATE OR REPLACE VIEW view_name AS select id,code from t_person where 1=1;-- 创建视图
	SELECT * FROM view_name;-- 使用视图
	DROP VIEW view_name;-- 删除视图

	
-- 复制表

	CREATE TABLE t2 LIKE t1;-- 复制表结构（MySQL）
	INSERT INTO t2 SELECT * FROM t1;-- 复制表数据（用*的话必须两个表的结构一摸一样）（MySQL）
	
	-- 拷贝zaasmis数据库里的表dbo.RYK_RYJBXX到另一个数据库zcjtest里，拷贝后需重新设置主键和标识
	select * into zcjtest.dbo.RYK_RYJBXX from zaasmis.dbo.RYK_RYJBXX;
	-- 拷贝另一台数据库的表到本地数据库，拷贝后需重新设置主键和标识
	select * into cw_zcgl from opendatasource('SQLOLEDB','Data Source=192.168.68.160;User ID=sa;Password=123456').zcjtest.dbo.cw_zcgl
	-- 拷贝另一台数据库的表到本地数据库(自定义属性)，拷贝后需重新设置主键和标识
	insert into cw_zcgl(ziclb,JILCJZ) select ziclb,JILCJZ from opendatasource('SQLOLEDB','Data Source=192.168.68.160;User ID=sa;Password=123456').zcjtest.dbo.cw_zcgl
	-- 拷贝-相同服务器-不同数据库-不同表的数据，拷贝后需重新设置主键和标识
	insert into cw_zcgl(kapbh,gudzcbh,gudzcmc) select kapbh,gudzcbh,gudzcmc from test.dbo.cw_zcgl
	
	
-- 预处理【MySQL】

	PREPARE s1 FROM 'select * from t1 where id > ?';-- 设置预处理语句
	SET @i=1;-- 设置变量
	EXECUTE s1 USING @i;-- 执行语句
	DROP PREPARE s1;-- 废弃预处理
	

-- 事务【MySQL】【Innodb才支持】

	SET AUTOCOMMIT=0;-- 关闭自动提交
	delete from t1 where id = 11;
	SAVEPOINT p1;-- 设置一个还原点
	delete from t1 where id = 12;
	ROLLBACK TO p1;-- 退回到p1状态
	ROLLBACK;-- 退回到最初状态
	COMMIT;-- 提交事务
	
-- MySQL
	-- 日期相关_数据库格式
		--date 		- 格式 YYYY-MM-DD (1000-01-01 到 9999-12-31)
		--datetime 	- 格式: YYYY-MM-DD HH:MM:SS (1000-01-01 00:00:00 到 9999-12-31 23:59:59)
		--timestamp - 格式: YYYY-MM-DD HH:MM:SS (1970-01-01 00:00:00 到 2037-12-31 23:59:59)
			--时区转换：存储时对当前的时区进行转换，检索时再转换回当前的时区。
			--自动插入：自动的使用当前日期和时间标记insert和update的操作。(一个表中只能有一个列选择下面其中一种)。
			--例：createTime timestamp not null default current_timestamp--添加时默认为当前时间
			--例：createTime timestamp not null default current_timestamp on update current_timestamp--添加修改时都更新为当前时间
		--year 		- 格式 YYYY 或 YY
	-- 日期相关_SQL函数
		--now()			返回当前的日期和时间
		--curdate()		返回当前的日期
		--curtime()		返回当前的时间
		--date()		提取日期或日期/时间表达式的日期部分
		--extract()		返回日期/时间按的单独部分
		--date_add()	给日期添加指定的时间间隔
		--date_sub()	从日期减去指定的时间间隔
		--datediff()	返回两个日期之间的天数
		--date_format()	用不同的格式显示日期/时间
	-- NULL相关_SQL函数
		--ifnull(age,0)	如果是NULL则返回0
		--coalesce(age,0)如果是NULL则返回0
-- SQL Server
	-- 日期相关_数据库格式
		--date 			- 格式 YYYY-MM-DD
		--datetime 		- 格式: YYYY-MM-DD HH:MM:SS
		--smalldatetime - 格式: YYYY-MM-DD HH:MM:SS
		--timestamp 	- 格式: 唯一的数字
	-- 日期相关_SQL函数
		--getdate()		返回当前日期和时间
		--datepart()	返回日期/时间的单独部分
		--dateadd()		在日期中添加或减去指定的时间间隔
		--datediff()	返回两个日期之间的时间
		--convert()		用不同的格式显示日期/时间
	-- NULL相关_SQL函数
		--isnull(age,0)	如果是NULL则返回0
		

avg(column_name)-- 返回数值列的平均值
count(*)-- 返回记录数
count(column_name)-- 返回指定属性的记录数，NULL不计入
count(distinct column_name)-- 返回指定属性不同值的记录数
first(column_name)-- 返回指定的字段中第一条记录的值
last(colume_name)-- 返回指定的字段中最后一条记录的值
max(colume_name)-- 返回指定的字段中最大的值
min(colume_name)-- 返回指定的字段中最小的值
sum(colume_name)-- 返回指定的字段的总额
ucase(column_name)-- 转换成大写
lcase(column_name)-- 转换成小写
mid(column_name,start[,length])-- 截取字符串：column_name从第start个字符开始，返回length个字符
substring(column_name,start[,length])-- 截取字符串：column_name从第start个字符开始，返回length个字符
len(column_name)-- 返回指定的字段的长度
round(column_name,decimals)-- 四舍五入为decimals个小数点
format(column_name,format)-- 返回format格式的column_name字段 如：format(now(),'YYYY-MM-DD')==2011-11-11
replace(column_name,'abc','def')-- 替换column_name中的abc为def
IFNULL(NULL,10)-- 10
IFNULL(1,0);-- 1
CONCAT('11','22','33')-- 112233
CONCAT('11',NULL,'33')-- NULL
LTRIM("  ZOU")-- 去左边空格
RTRIM("ZOU  ")-- 去右边空格
REPEAT("ZOU",3)-- 重复3次：ZOUZOUZOU
CEILING(1.5)-- 向上取整：2
FLOOR(1.5)-- 向下取整：1
RAND()-- 取0-1的随机数：0.2933707249289106
	SELECT * FROM t1 ORDER BY RAND();-- 随机排序


MySql
	
	-- 分组查询，各组只取某条件的一条
	SELECT
		t_location.*
	FROM
		t_location 
		INNER JOIN (
			SELECT 
				MAX(locationTime) ltime, t_location.userId userId
			FROM 
				t_location 
			GROUP BY userId
		) t1 ON (t1.ltime=t_location.locationTime AND t1.userId=t_location.userId)

	-- 根据自定义的顺序排序
	ORDER BY field(t_studapply.ssxsq,'市直','鹿城区','瓯海区','龙湾区','经开区','瑞安市','乐清市','永嘉县','平阳县','苍南县','文成县','泰顺县','洞头县')

	-- 一对多关联查询：只拿一个
	SELECT
		t_school.*, t_user.mobile 'userMobile'
	FROM
		t_school 
		LEFT JOIN t_user ON t_school.id=t_user.schoolId AND t_user.id IN (
			SELECT min(id) FROM t_user WHERE userGroup='2' AND valid='1' GROUP BY schoolId
		)
		
	-- 一对多查询：一个schoolId对应多个name,name用逗号隔开
	SELECT schoolId, GROUP_CONCAT(name) AS name_list
	FROM t_user
	GROUP BY schoolId
	
	-- 一对多查询：根据条件查询,去掉重复的name,排序,name用‘-’隔开
	SELECT DISTINCT schoolId, GROUP_CONCAT(DISTINCT name ORDER BY name SEPARATOR '-') AS name_list
	FROM t_user 
	WHERE userGroup='2'
	GROUP BY schoolId
	ORDER BY schoolId
	
	-- 分组按条件统计总数
	SELECT dw, SUM(CASE WHEN (ggkhch=5001 AND dgcj=1) THEN 1 ELSE 0 END) 'a'
	FROM t_backbone
	GROUP BY dw
	
	-- 查询附近的记录($lng是你的经度，$lat是你的纬度)
	SELECT lng,lat,
        (POWER(MOD(ABS(lng - $lng),360),2) + POWER(ABS(lat - $lat),2)) AS distance
    FROM `user_location`
    ORDER BY distance LIMIT 100
	
-- 1、通过customer分组
-- 2、显示每个customer的orderprice总和
-- 3、显示orderprice总和小于2000的记录
select customer,sum(orderprice) 
from orders
group by customer
having sum(orderprice)<2000


-- 查排名
SELECT 
(
	SELECT count(*) 
	FROM `t_creditslog`
	WHERE k.creditsChange<creditsChange or (k.creditsChange=creditsChange and k.creditsRemaining<creditsRemaining)
) AS timeSort
FROM `t_creditslog` k
WHERE creditsUser='1539ba717dff43d09d15c6c34c2536ee';


-- 升序并且NULL排在最后
	-- Sqlserver 和 MySql 认为NULL最小
		ORDER BY CASE WHEN realname IS NULL THEN 1 ELSE 0 END,realname
	-- Oracle认为NULL最大
		ORDER BY realname NULLS FIRST



-- dml 数据操作语言(insert update delete)
-- dtl 数据事物语言(commit rollback savepoint)
-- ddl 数据定义语言(create alter drop..)
-- dcl 数据控制语言(grant revoke)



-- sql优化

show status;	-- 显示数据库的当前状态信息
show global status;	-- 显示数据库从启动到查询的次数

show status like 'Com%';	-- 显示当前控制台的情况
show status like 'connections';	-- 视图链接MySql服务器的次数
show status like 'uptime';	-- 服务器工作的时间(秒)
show status like 'handler_read_key';	-- 值越大越好，表示使用索引查询到的次数
show status like 'handler_read_rnd_next'	-- 值越小越好，表示查询低效
show status like 'slow_queries';	-- 慢查询的次数(10秒以上)

show variables like 'long_query_time';	-- 查看参数：慢查询的界限
set long_query_time=1;

xxxx>bin\mysqld.exe -show-query-log	-- 启动MySQL服务，并开启查询日志

explain sql语句; -- 分析sql语句的效率

-- 优化SQL
	-- 对于使用like的查询，查询语句中以%开头的条件不能用到索引
	-- 优化group by语句：默认情况下，group by会自动进行排序，可以使用order by null禁止排序
	-- 使用join代替子查询
	
  -- Oracle索引优化
	-- 不等于表达式是索引无效的。
		WHERE name!='abc' -- 优化前
		WHERE name>'abc' OR name<'abc' -- 优化后
	-- 如果被索引的列在某些行中存在NULL值，就不会使用索引。
	-- 如果被索引的列使用函数查询，就不会使用索引。
		WHERE TRUNC(createDay)='01-MAY-81'-- 优化前
		WHERE createDay<(TO_DATE('01-MAY-81')+0.9999) -- 优化后
		CREATE INDEX index_trunc_createday ON t_student(TRUNC(createDay)); -- 或者创建基于函数的索引
	-- 如果比较不匹配的数据类型，就不会使用索引。
		WHERE account_varchar2 = 990354 -- 优化前
		WHERE account_varchar2 = '990354' -- 优化后
	-- 如果列的取值固定几种(基数少)，请使用位图索引（列尽量用CHAR不要用VARCHAR2）。
		CREATE BITMAP INDEX index_hospital ON wsxt(hospital);

-- MyISAM
	-- 支持全文索引(FULLTEXT)
	-- 不支持事务
	-- 不支持外键
	-- 删除数据后，空间默认不回收，需使用optimize table table_name回收
	-- 大批量插入数据
		-- alter table table_name disable keys;
		-- loading data;
		-- alter table table_name enable keys;
	-- 内存参数配置
		-- innodb_additional_mem_pool_size = 64M
		-- innodb_buffer_pool_size = 1G
-- Innodb
	-- 不支持全文索引
	-- 支持事务
	-- 支持外键
	-- 数据文件：data/ibdata1；data/数据库名/只有一个*.frm表结构文件
	-- 大批量插入数据
		-- 将要导入的数据按照主键排序
		-- set unique_checks=0,关闭唯一性校验
		-- set autocommit=0,关闭自动提交
	-- 内存参数配置
		-- key_buffer_size

	


