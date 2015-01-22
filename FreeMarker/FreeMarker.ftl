
<#if user.name == "my">
	...
<#else>
	...
</#if>
	
<#if user.age lt 12>
	...
<#elseif user.age lt 18>
	...
<#else>
	...
</#if>

<#if (user.name)??>...</#if>	<#-- 如果user.name存在且不为空 -->
	
	
<#list users as s>
	${s.c}
	<#if s.c==1><#break></#if>	<#-- 如果满足条件，则跳出循环 -->
	
	${s_index}<#if s_has_next>,</#if>	<#-- 当前索引 和 后面是否还有 -->
	
</#list>
	
<#list ["winter", "spring", "summer", "autumn"] as x>
	${x}
</#list>
	
<#list 1..15 as y>
	${y}
</#list>
	
<#list ["winter", "spring"] + ["summer", "autumn"] as user>
	${user}
</#list>
	
<#assign myMaps={"1":"张三","2":"李四"}/>
<#list myMaps?keys as key>
${key}--${myMaps[key]}
</#list>
	
	
<#include "a.ftl" />	<#-- 插入整段代码，路径是相对于配置的根路径 -->

<#import "a.ftl" as myHead/>	<#-- 插入整段代码 -->
${myHead.username}--<@myHead.myFun/>

<@include_page path="path/to/some.jsp" inherit_params=true params={"foo": "99", "bar": ["a", "b"]}/>	<#-- 开始一个独立的HTTP请求处理 -->


${user!}			<#-- user为空时，显示空串 -->
${user!"my"}		<#-- user为空时，显示“my” -->
${(user.name)!"my"}	<#-- user为空或user.name为空时，显示“my” -->
${true?string}		<#-- "true" -->
${myDatetime?string("yyyy-MM-dd HH:mm:ss")}	<#-- 2014-06-06 15:25:05 -->
${r"${foo}"}		<#-- "${foo}" -->
${"Hello ${user}!"}	<#-- "Hello Jone!" -->
${"Hello"+user+"!"}		<#-- "Hello Jone!" -->
${"123456789!"[0..5]}...	<#-- "123456..." -->
${.globals.username}	<#-- 访问root中的变量username -->
${10035125?c}		<#-- "10035125" -->
${stack.findValue("@com.xt.poh.entity.User@ROLE_ADMIN")}	<#-- 访问静态 -->

?replace('--', '-')


<#-- assign(自定义变量) -->
	<#assign sname="张三"/>
	<#assign sabc=true/>
	<#assign myDate="2014-06-06 15:25:05"?date("yyyy-MM-dd")>
	<#assign myDatetime="2014-06-06 15:25:05"?datetime("yyyy-MM-dd HH:mm:ss")>
	
	
<#-- macro nested local return(自定义函数) -->
	<#macro he>
		Hello
		<#return>	<#-- 结束函数 -->
	</#macro>
	<@he/>	<#-- "Hello" -->
	
	<#macro hee num=2><#-- 设置num的默认值为2，调用时可以省略参数；如果不设置的话，调用时必须传参数 -->
		<#list 1..num as n>Hello${n}</#list>
	</#macro>
	<@hee/>			<#-- "Hello1Hello2" -->
	<@hee num=3/>	<#-- "Hello1Hello2Hello3" -->
	
	<#macro heee>
		<#nested />
	</#macro>
	<@heee>Hello ${username}</@heee>	<#-- "Hello Jone" -->
	
	<#macro heeee>
		<#local username="wang">	<#-- 定义局部变量 -->
	</#macro>
	

<#-- function return(自定义方法) -->	
	<#function kv_catalog_type k>
		<#if k==1>
			<#return "文章">
		<#elseif k==2>
			<#return "链接">
		<#elseif k==3>
			<#return "外部数据">
		<#else>
			<#return "无">
		</#if>
	</#function>
	${kv_catalog_type(1)}
	
	
	<#-- /lib/yourcompany.com/your_library.ftl -->
	
	<#compress>里面的空白会被处理</#compress>
	<@compress single_line=true>里面的空白会被处理，所有内容变成一行</@compress>
	
	
<#-- 异常处理 -->
	<#attempt>
		${obj.id}
	<#recover>
		id不存在时执行：错误:${.error}
	</#attempt>
	
	
	
	
	





	<#if ((obj.tops)!0)==1>checked</#if>	<#-- checkbox是否选中 -->
	
	${((obj.showtime)?string("yyyy-MM-dd HH:mm:ss"))!}		<#-- 时间字段的显示 -->
	${.now}	<#-- 显示当前时间 -->
	
	${vlist?seq_contains('abc')?string("checked", "")}		<#-- 集合是否包含某元素 -->
	
	${"<br/>"?html}		${((obj.content)!'')?html}
	
	<#assign mcount=0/>
	<#list objs as d>
		<input type="hidden" name="dayList[${mcount}].id" value="${(d.id)!}"/>
		<#assign mcount=mcount+1/>
	</#list>
	
	${"abc"?right_pad(5)}
















