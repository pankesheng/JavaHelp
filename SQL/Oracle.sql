

-- Oracle(function/procedure/trigger/dbms_job/分区表)


	-- 排序后分页
	SELECT * FROM (SELECT A.*, ROWNUM RN FROM (
		select * from wsxt where tel like '135%' order by name
	) A WHERE ROWNUM <= 20 ) WHERE RN > 0
	
	-- 排序后取前几条
	SELECT * FROM (
		select * from wsxt where tel like '135%' order by name
	) WHERE ROWNUM <= 20

	-- 随机生成数字
	SELECT TRUNC(DBMS_RANDOM.VALUE(100,1000)) FROM DUAL; -- 范围：100~999
	
	-- 随机生成字符串
	SELECT DBMS_RANDOM.STRING('A',20) FROM DUAL; -- 'U':大写字母；'L':小写字母；'A':大写+小写字母 ；'X':大写字母+数字；'P':任意字符+包括特殊字符
	
	-- 随机生成日期
	SELECT to_date(
		TRUNC(
			DBMS_RANDOM.VALUE(
				to_number(to_char(to_date('20110101','yyyymmdd'),'J')),
				to_number(to_char(to_date('20111231','yyyymmdd')+1,'J'))
			)
		),'J')+DBMS_RANDOM.VALUE(1,3600)/3600
	FROM dual;
	
	-- 随机生成日期（字符串格式yyyy-mm-dd hh:mm:ss）
	SELECT to_char(
		to_date(TRUNC(
				DBMS_RANDOM.VALUE(
					to_number(to_char(to_date('20130101','yyyymmdd'),'J')),
					to_number(to_char(to_date('20131231','yyyymmdd')+1,'J'))
				)
		),'J')
		+DBMS_RANDOM.VALUE(1,3600)/3600,'yyyy-mm-dd hh24:mi:ss'
	)
	FROM dual;
	
	
	-- 取得两个月后的三天前的时间
	select SYSDATE + INTERVAL '2' month - INTERVAL '3' day from dual; -- year/month/day/hour/minute/
	
	-- 取得两个日期的间隔天数
	select floor(sysdate - to_date('20130305','yyyymmdd')) from dual;
	
	-- 取得多个日期中的最晚日期
	select greatest('20121115','20121124','20121112') from dual;

	
	-- 自定义函数
	create or replace function fun_a(a number) -- 如果函数名没有参数，不要带括号
	return number
	is
		b number;
	begin
		select count(*) into b from WSXT; -- 不能有 insert、update、delete 语句
		return(b+a);
	end fun_a;
	/
	
	-- 调用函数
	select fun_a(5) from dual; -- 如果函数名没有参数，不要带括号
	
	-- 删除函数
	drop function fun_a;
	
	
	-- 自定义存储过程
	create or replace procedure pro_insert_wsxt
	(
		a number,
		b t_student.idcard%TYPE
		-- c in varchar2,
		-- d out number,
		-- e out varchar2
	)
	is
		-- p_a varchar2(30);
		-- p_b number;
	begin
		-- d:=0;
		-- e:='失败';
		
		-- if ... then ... commit;
		-- elseif ... then ... commit;
		-- else ... commit;
		-- end if;
		
		-- case a
		-- 	when 1 then ...;
		-- 	when 2 then ...;
		-- 	else null;
		-- end case;
		
		for i in a..b loop
			-- ...;
		end loop;
		commit;
	end pro_insert_wsxt;
	/
 
 	-- 调用存储过程
	execute pro_insert_wsxt(4,5);
	
	-- 删除存储过程
	drop procedure pro_insert_wsxt;
	
	
	-- 创建触发器
	create or replace trigger INSERT_WSXT_TFBD_TRIGGER
	after insert	-- 同一时间、同一事件、同一类型的触发器只能有一个[before|after]、[insert|update|delete]
	on WSXT
	for each row
	declare
		cnt int;
	begin
		-- 执行部分只能用DML语句（SELECT、INSERT、UPDATE、DELETE），不能使用DDL语句（CREATE、ALTER、DROP），不能使用事务操作方法（commit,rollback）
		select count(t7) into cnt from tfk where t7 = :NEW.IDCARD;
		if (cnt > 0) then
			insert into yjtf(yj_name,yj_idcard,yj_nr,yj_from) values(:NEW.Name,:NEW.Idcard,:NEW.Jztime||'在'||:NEW.Hospital||'（'||:NEW.KS||'）就诊','瓯海卫生系统');
		end if;
	end;
	/
	
	-- 删除触发器
	drop trigger trig_a;
	
	
	-- 触发器实例：当表被删除一条记录时，把被删除记录写到临时表中去。
	create or replace trigger tri_del_emp 
	before delete
	on t_emp
	for each row
	begin
		insert into t_emp_temp(deptno , empno, ename , job ,mgr , sal , comm , hiredate) values(:old.deptno, :old.empno, :old.ename , :old.job,:old.mgr, :old.sal, :old.comm, :old.hiredate);
	end;
	/
	
	-- 触发器实例：限制对Departments表修改（包括INSERT,DELETE,UPDATE）的时间范围，即不允许在非工作时间修改departments表。
	create or replace trigger tri_dept_time
	before insert or delete or update
	on t_departments
	begin
		if (TO_CHAR(sysdate,'DAY') IN ('星期六', '星期日')) OR (TO_CHAR(sysdate, 'HH24:MI') NOT BETWEEN '08:30' AND '18:00') THEN
     		RAISE_APPLICATION_ERROR(-20001, '不是上班时间，不能修改departments表');
		end if;
	end;
	/
	
	-- 触发器实例：限定只对id为80的记录进行行触发器操作。
	create or replace trigger tri_emp_sal_comm
	before insert or update of a_tel, a_address or delete
	on t_employees
	for each row when (old.a_id = 80)
	begin
		CASE
		 WHEN INSERTING THEN
		 	RAISE_APPLICATION_ERROR(-20004, 'xxxxxxx');
	     WHEN UPDATING ('a_tel') THEN
	        IF :NEW.a_tel < :old.a_tel THEN
	           RAISE_APPLICATION_ERROR(-20001, 'xxxxxxx');
	        END IF;
	     WHEN UPDATING ('a_address') THEN
	        IF :NEW.a_address < :old.a_address THEN
	           RAISE_APPLICATION_ERROR(-20002, 'xxxxxxx');
	        END IF;
	     WHEN DELETING THEN
	          RAISE_APPLICATION_ERROR(-20003, 'xxxxxxx');
		END CASE;
	end; 
	/
	
	
	-- 定时任务
	declare job number;
	begin
		dbms_job.submit(job,'pro_update_lsbk_flag;',sysdate,'TRUNC(sysdate,''mi'') + 1 / (24*60)');-- 现在开始，每分钟执行一次（如果每天2点钟执行：TRUNC(sysdate) + 1 + 2 / (24)）
		commit;
	end;
	/
	
	-- 删除定时任务(61为自动生成的编号，需要查看DBMS_Jobs)
	execute dbms_job.remove(61);

	
  -- 分区
  
	-- Range分区(范围)
	CREATE TABLE student(id NUMBER PRIMARY KEY, ctime DATE NOT NULL) PARTITION BY RANGE (ctime) 
	(
		PARTITION p1 VALUES LESS THAN (TO_DATE('2011-01-01','yyyy-mm-dd')),
		PARTITION p2 VALUES LESS THAN (TO_DATE('2012-01-01','yyyy-mm-dd')),
		PARTITION p3 VALUES LESS THAN (TO_DATE('2013-01-01','yyyy-mm-dd')),
		PARTITION p4 VALUES LESS THAN (MAXVALUE)
	);
	
	-- Hash分区(随机)
	CREATE TABLE student(id NUMBER PRIMARY KEY, ctime DATE NOT NULL) PARTITION BY HASH (id) 
	(
		PARTITION p1,
		PARTITION p2,
		PARTITION p3,
		PARTITION p4
	);
	
	-- List分区(枚举)
	CREATE TABLE student(id NUMBER PRIMARY KEY, area NVARCHAR2(6)) PARTITION BY LIST(area)
	(
		PARTITION p1 values('330381'),
		PARTITION p2 values('330382'),
		PARTITION p3 values('330383'),
		PARTITION p4 values(DEFAULT)
	);
	
	-- 创建计划表：执行C:\oracle\ora92\rdbms\admin\utlxplan.sqlutlxplan.sql
	
	-- 创建表分区
	create tablespace tbsname datafile 'C:\oracle\oradata\DZH\mytbs01.dbf' size 500m;
	
	