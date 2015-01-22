
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

<#if (user.name)??>...</#if>	<#-- ���user.name�����Ҳ�Ϊ�� -->
	
	
<#list users as s>
	${s.c}
	<#if s.c==1><#break></#if>	<#-- �������������������ѭ�� -->
	
	${s_index}<#if s_has_next>,</#if>	<#-- ��ǰ���� �� �����Ƿ��� -->
	
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
	
<#assign myMaps={"1":"����","2":"����"}/>
<#list myMaps?keys as key>
${key}--${myMaps[key]}
</#list>
	
	
<#include "a.ftl" />	<#-- �������δ��룬·������������õĸ�·�� -->

<#import "a.ftl" as myHead/>	<#-- �������δ��� -->
${myHead.username}--<@myHead.myFun/>

<@include_page path="path/to/some.jsp" inherit_params=true params={"foo": "99", "bar": ["a", "b"]}/>	<#-- ��ʼһ��������HTTP������ -->


${user!}			<#-- userΪ��ʱ����ʾ�մ� -->
${user!"my"}		<#-- userΪ��ʱ����ʾ��my�� -->
${(user.name)!"my"}	<#-- userΪ�ջ�user.nameΪ��ʱ����ʾ��my�� -->
${true?string}		<#-- "true" -->
${myDatetime?string("yyyy-MM-dd HH:mm:ss")}	<#-- 2014-06-06 15:25:05 -->
${r"${foo}"}		<#-- "${foo}" -->
${"Hello ${user}!"}	<#-- "Hello Jone!" -->
${"Hello"+user+"!"}		<#-- "Hello Jone!" -->
${"123456789!"[0..5]}...	<#-- "123456..." -->
${.globals.username}	<#-- ����root�еı���username -->
${10035125?c}		<#-- "10035125" -->
${stack.findValue("@com.xt.poh.entity.User@ROLE_ADMIN")}	<#-- ���ʾ�̬ -->

?replace('--', '-')


<#-- assign(�Զ������) -->
	<#assign sname="����"/>
	<#assign sabc=true/>
	<#assign myDate="2014-06-06 15:25:05"?date("yyyy-MM-dd")>
	<#assign myDatetime="2014-06-06 15:25:05"?datetime("yyyy-MM-dd HH:mm:ss")>
	
	
<#-- macro nested local return(�Զ��庯��) -->
	<#macro he>
		Hello
		<#return>	<#-- �������� -->
	</#macro>
	<@he/>	<#-- "Hello" -->
	
	<#macro hee num=2><#-- ����num��Ĭ��ֵΪ2������ʱ����ʡ�Բ�������������õĻ�������ʱ���봫���� -->
		<#list 1..num as n>Hello${n}</#list>
	</#macro>
	<@hee/>			<#-- "Hello1Hello2" -->
	<@hee num=3/>	<#-- "Hello1Hello2Hello3" -->
	
	<#macro heee>
		<#nested />
	</#macro>
	<@heee>Hello ${username}</@heee>	<#-- "Hello Jone" -->
	
	<#macro heeee>
		<#local username="wang">	<#-- ����ֲ����� -->
	</#macro>
	

<#-- function return(�Զ��巽��) -->	
	<#function kv_catalog_type k>
		<#if k==1>
			<#return "����">
		<#elseif k==2>
			<#return "����">
		<#elseif k==3>
			<#return "�ⲿ����">
		<#else>
			<#return "��">
		</#if>
	</#function>
	${kv_catalog_type(1)}
	
	
	<#-- /lib/yourcompany.com/your_library.ftl -->
	
	<#compress>����Ŀհ׻ᱻ����</#compress>
	<@compress single_line=true>����Ŀհ׻ᱻ�����������ݱ��һ��</@compress>
	
	
<#-- �쳣���� -->
	<#attempt>
		${obj.id}
	<#recover>
		id������ʱִ�У�����:${.error}
	</#attempt>
	
	
	
	
	





	<#if ((obj.tops)!0)==1>checked</#if>	<#-- checkbox�Ƿ�ѡ�� -->
	
	${((obj.showtime)?string("yyyy-MM-dd HH:mm:ss"))!}		<#-- ʱ���ֶε���ʾ -->
	${.now}	<#-- ��ʾ��ǰʱ�� -->
	
	${vlist?seq_contains('abc')?string("checked", "")}		<#-- �����Ƿ����ĳԪ�� -->
	
	${"<br/>"?html}		${((obj.content)!'')?html}
	
	<#assign mcount=0/>
	<#list objs as d>
		<input type="hidden" name="dayList[${mcount}].id" value="${(d.id)!}"/>
		<#assign mcount=mcount+1/>
	</#list>
	
	${"abc"?right_pad(5)}
















