<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>OGNL���ʽ����ѧϰ</title> 
</head>
<body>

<s:if test="%{obj.sysd.indexOf('aaa') >= 0}">�ַ�������ĳ�ַ���</s:if>

<s:date name="obj.c" format="yyyy-MM-dd HH:mm:ss"/>


	<!-- ȡ��Value Stack Contents���ĳֵ -->
	<s:property value="errors.name[0]"/>
	
	<!-- #�൱��ActionContext.getContext() -->
	<s:property value="#parameters.userName[0]"/>�����൱��<%=request.getParameter("userName") %>�൱��$ { pageScope.userName }<!-- ������ǰHTTP���������Map  -->
	<s:property value="#request.userName"/>�����൱��<%=request.getAttribute("userName") %>�൱��${ requestScope.userName }<!-- ������ǰHttpServletRequest�����Ե�Map -->
	<s:property value="#session.userName"/>�����൱��<%=session.getAttribute("userName") %>�൱��${ sessionScope.userName } <!-- ������ǰHttpSession�����Ե�Map -->
	<s:property value="#application.userName"/>�����൱��<%=application.getAttribute("userName") %>�൱��${ applicationScope.userName }<!-- ������ǰӦ�õ�ServletContext�����Ե�Map -->
	<s:property value="#attr.userName"/>	��page>request>session>application˳���ȡuserName���ԣ�ֱ���ҵ�Ϊֹ
	
	<s:set var="personName" value="person.name"/>
	<s:property value="#personName"/>
	<s:set var="janesName">Jane Doe</s:set>
	<s:property value="#janesName"/>

	<s:set var="adminName" value="username" scope="session"/>
	<s:property value="#session.getAttribute('adminName')"/>
	
	<!-- format="yyyy-MM-dd HH:mm:ss" -->
	
	<s:elseif test="obj.sffctp==\"��\""></s:elseif>

	<li>����ֵջ�е�action����ͨ����: username = <s:property value="username"/> </li>
	<li>property �趨Ĭ��ֵ: <s:property value="admin" default="����Ա"/> </li>
	<li>property �趨HTML: <s:property value="'<hr/>'" escape="false"/> </li>
	<li>����ֵջ�ж������ͨ����(get set����)��<s:property value="user.age"/>
	<li>����ֵջ�ж������ͨ������<s:property value="password.length()"/></li>
	<li>����ֵջ�ж������ͨ������<s:property value="cat.miaomiao()" /></li>
	<li>����ֵջ��action����ͨ������<s:property value="m()" /></li>
	<li>���ʾ�̬������<s:property value="@com.bjsxt.struts2.ognl.S@s()"/></li>
	<li>���ʾ�̬���ԣ�<s:property value="@com.bjsxt.struts2.ognl.S@STR"/></li>
	<li>����Math��ľ�̬������<s:property value="@@max(2,3)" /></li>
	<li>������ͨ��Ĺ��췽����<s:property value="new com.bjsxt.struts2.ognl.User(8)"/></li>
	<li>����List:<s:property value="users"/></li>
	<li>����List��ĳ��Ԫ��:<s:property value="users[1]"/></li>
	<li>����List��Ԫ��ĳ�����Եļ���:<s:property value="users.{age}"/></li>
	<li>����List��Ԫ��ĳ�����Եļ����е��ض�ֵ:<s:property value="users[0].age"/></li>
	<li>����Set:<s:property value="dogs"/></li>
	<li>����Set��ĳ��Ԫ��:<s:property value="dogs[1]"/></li>
	<li>����Map:<s:property value="dogMap"/></li>
	<li>����Map��ĳ��Ԫ��:<s:property value="dogMap.dog101"/>
	<li>����Map�����е�key:<s:property value="dogMap.keys"/></li>
	<li>����Map�����е�value:<s:property value="dogMap.values"/></li>
	<li>���������Ĵ�С��<s:property value="dogMap.size()"/> | <s:property value="users.size"/> </li>
	<li>ͶӰ(����)��<s:property value="users.{?#this.age==1}[0]"/></li>
	<li>ͶӰ��<s:property value="users.{^#this.age>1}.{age}"/></li>
	<li>ͶӰ��<s:property value="users.{$#this.age>1}.{age}"/></li>
	<li>ͶӰ��<s:property value="users.{$#this.age>1}.{age} == null"/></li>
		
		<s:iterator value="tj" status="st1">
        	<s:iterator value="tj[#st1.index]" status="st2">
        		<s:property />
        	</s:iterator>
        </s:iterator>
		
	<s:debug></s:debug>
	
	ApplicationMessages.properties
	HelloWorld=Hello Wrold!
	<s:i18n name="ApplicationMessages">
        <s:text name="HelloWorld" />
    </s:i18n>

	����bean,��ʹ��param���趨�µ�����ֵ:
	<s:bean name="com.bjsxt.struts2.tags.Dog" var="myDog">
		<s:param name="name" value="'oudy'"/>
		<s:param name="name">oudy</s:param>
	</s:bean>
	�ó�ֵ��<s:property value="#myDog.name"/>
	
	<!-- ''������:������Ķ������ַ�������(value������������Object) -->
	<s:set var="incPage" value="'_include.html'" />
	<!-- %{}������:������Ķ�����OGNL����(value������������String) -->
	<s:include value="%{#incPage}"/>
	
	<!-- ������ʽsimple��ʾfielderror�����Ϣ -->
	<s:fielderror fieldName="fielderror.test" theme="simple"/>
	
	<s:iterator value="{'aaa', 'bbb', 'ccc'}" status="status">
		<s:property/> | 
		��������Ԫ��������<s:property value="#status.count"/> |
		��������Ԫ��������<s:property value="#status.index"/> |
		��ǰ��ż������<s:property value="#status.even"/> |
		��ǰ����������<s:property value="#status.odd"/> |
		�ǵ�һ��Ԫ���𣿣�<s:property value="#status.first"/> |
		�����һ��Ԫ���𣿣�<s:property value="#status.last"/>
		<br />
	</s:iterator>
	
	<s:iterator value="xs.imgs.split(\",\")">
		<s:property/>
	</s:iterator>
	
</body>
</html>