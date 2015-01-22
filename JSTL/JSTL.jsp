<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>

//导入标签包
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>

<c:if test="${aclForm.principalType eq 'User' }">
......
</c:if>


//变量的读写
<c:set value="123" var="temp"/>
<c:out value="${temp}" default="123" escapeXml="false"/>//缺省值为123；解析XML
<c:remove var="temp"/>

//if语句
<c:if test="true" var="v">
这里填执行的语句
</c:if>

//选择语句
<c:choose>
	<c:when test="ture">语句</c:when>
	<c:otherwise>语句</c:otherwise>
</c:choose>

//循环语句
<c:forEach items="${userList}" var="u">
	<c:out value="${u.username}"/>
	<c:out value="${u.group.name}"/>
</c:forEach>

//时间格式转换
<fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm:ss"/>

//显示部分文字（前81个字）
${fn:substring(Article.summary, 0, 80)

//自定义函数库
1:src/util/Functions.java
2:WebRoot/WEB-INF/drp-functions.tld
3:<%@ taglib prefix="drp" uri="http://www.zcj.com/functions"%> 
	${drp:getLylxList()}



//循环固定次数
<table>
	<c:forEach items="${artList}" var="article" varStatus="current">
		<c:if test="${current.index<5}">//current.index从0开始
			<tr><td></td></tr>
		</c:if>	
	</c:forEach>
</table>

<table>
	<c:forEach begin="0" end="3" var="article" varStatus="articleStatus">
		<tr><td></td></tr>//articleStatus.index从0到3
	</c:forEach>
</table>


</body>
</html>