
-- 获取imgs字符串中以‘-’分隔的第二个值
PARSENAME(REPLACE(imgs,'-','.'),2)

-- 是否存在子栏目
SELECT
	t1.*,(CASE WHEN t2.id IS NULL THEN 0 ELSE 1 END) multicata
FROM
	t_catalog t1
	left join t_catalog t2 on t1.id=t2.pid
	
-- 获取月份
DATEPART(mm,t_contacts.birthdate)

-- 取 abcdefg 的第一个字符串开始，删除0个字符串，并和 ddd 合并 ==>abcdefgddd
stuff("abcdefg",1,0,"ddd")