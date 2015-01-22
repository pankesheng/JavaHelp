<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>OGNL表达式语言学习</title> 
</head>
<body>

<s:if test="%{obj.sysd.indexOf('aaa') >= 0}">字符串包含某字符串</s:if>

<s:date name="obj.c" format="yyyy-MM-dd HH:mm:ss"/>


	<!-- 取得Value Stack Contents里的某值 -->
	<s:property value="errors.name[0]"/>
	
	<!-- #相当于ActionContext.getContext() -->
	<s:property value="#parameters.userName[0]"/>作用相当于<%=request.getParameter("userName") %>相当于$ { pageScope.userName }<!-- 包含当前HTTP请求参数的Map  -->
	<s:property value="#request.userName"/>作用相当于<%=request.getAttribute("userName") %>相当于${ requestScope.userName }<!-- 包含当前HttpServletRequest的属性的Map -->
	<s:property value="#session.userName"/>作用相当于<%=session.getAttribute("userName") %>相当于${ sessionScope.userName } <!-- 包含当前HttpSession的属性的Map -->
	<s:property value="#application.userName"/>作用相当于<%=application.getAttribute("userName") %>相当于${ applicationScope.userName }<!-- 包含当前应用的ServletContext的属性的Map -->
	<s:property value="#attr.userName"/>	按page>request>session>application顺序读取userName属性，直到找到为止
	
	<s:set var="personName" value="person.name"/>
	<s:property value="#personName"/>
	<s:set var="janesName">Jane Doe</s:set>
	<s:property value="#janesName"/>

	<s:set var="adminName" value="username" scope="session"/>
	<s:property value="#session.getAttribute('adminName')"/>
	
	<!-- format="yyyy-MM-dd HH:mm:ss" -->
	
	<s:elseif test="obj.sffctp==\"是\""></s:elseif>

	<li>访问值栈中的action的普通属性: username = <s:property value="username"/> </li>
	<li>property 设定默认值: <s:property value="admin" default="管理员"/> </li>
	<li>property 设定HTML: <s:property value="'<hr/>'" escape="false"/> </li>
	<li>访问值栈中对象的普通属性(get set方法)：<s:property value="user.age"/>
	<li>访问值栈中对象的普通方法：<s:property value="password.length()"/></li>
	<li>访问值栈中对象的普通方法：<s:property value="cat.miaomiao()" /></li>
	<li>访问值栈中action的普通方法：<s:property value="m()" /></li>
	<li>访问静态方法：<s:property value="@com.bjsxt.struts2.ognl.S@s()"/></li>
	<li>访问静态属性：<s:property value="@com.bjsxt.struts2.ognl.S@STR"/></li>
	<li>访问Math类的静态方法：<s:property value="@@max(2,3)" /></li>
	<li>访问普通类的构造方法：<s:property value="new com.bjsxt.struts2.ognl.User(8)"/></li>
	<li>访问List:<s:property value="users"/></li>
	<li>访问List中某个元素:<s:property value="users[1]"/></li>
	<li>访问List中元素某个属性的集合:<s:property value="users.{age}"/></li>
	<li>访问List中元素某个属性的集合中的特定值:<s:property value="users[0].age"/></li>
	<li>访问Set:<s:property value="dogs"/></li>
	<li>访问Set中某个元素:<s:property value="dogs[1]"/></li>
	<li>访问Map:<s:property value="dogMap"/></li>
	<li>访问Map中某个元素:<s:property value="dogMap.dog101"/>
	<li>访问Map中所有的key:<s:property value="dogMap.keys"/></li>
	<li>访问Map中所有的value:<s:property value="dogMap.values"/></li>
	<li>访问容器的大小：<s:property value="dogMap.size()"/> | <s:property value="users.size"/> </li>
	<li>投影(过滤)：<s:property value="users.{?#this.age==1}[0]"/></li>
	<li>投影：<s:property value="users.{^#this.age>1}.{age}"/></li>
	<li>投影：<s:property value="users.{$#this.age>1}.{age}"/></li>
	<li>投影：<s:property value="users.{$#this.age>1}.{age} == null"/></li>
		
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

	定义bean,并使用param来设定新的属性值:
	<s:bean name="com.bjsxt.struts2.tags.Dog" var="myDog">
		<s:param name="name" value="'oudy'"/>
		<s:param name="name">oudy</s:param>
	</s:bean>
	拿出值：<s:property value="#myDog.name"/>
	
	<!-- ''的作用:把里面的东西当字符串解析(value本来的类型是Object) -->
	<s:set var="incPage" value="'_include.html'" />
	<!-- %{}的作用:把里面的东西当OGNL解析(value本来的类型是String) -->
	<s:include value="%{#incPage}"/>
	
	<!-- 根据样式simple显示fielderror里的信息 -->
	<s:fielderror fieldName="fielderror.test" theme="simple"/>
	
	<s:iterator value="{'aaa', 'bbb', 'ccc'}" status="status">
		<s:property/> | 
		遍历过的元素总数：<s:property value="#status.count"/> |
		遍历过的元素索引：<s:property value="#status.index"/> |
		当前是偶数？：<s:property value="#status.even"/> |
		当前是奇数？：<s:property value="#status.odd"/> |
		是第一个元素吗？：<s:property value="#status.first"/> |
		是最后一个元素吗？：<s:property value="#status.last"/>
		<br />
	</s:iterator>
	
	<s:iterator value="xs.imgs.split(\",\")">
		<s:property/>
	</s:iterator>
	
</body>
</html>