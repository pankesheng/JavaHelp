<%@ page language="java" contentType="text/html; charset=GB18030" pageEncoding="GB18030"%>

//�����ǩ��
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


//�����Ķ�д
<c:set value="123" var="temp"/>
<c:out value="${temp}" default="123" escapeXml="false"/>//ȱʡֵΪ123������XML
<c:remove var="temp"/>

//if���
<c:if test="true" var="v">
������ִ�е����
</c:if>

//ѡ�����
<c:choose>
	<c:when test="ture">���</c:when>
	<c:otherwise>���</c:otherwise>
</c:choose>

//ѭ�����
<c:forEach items="${userList}" var="u">
	<c:out value="${u.username}"/>
	<c:out value="${u.group.name}"/>
</c:forEach>

//ʱ���ʽת��
<fmt:formatDate value="${today}" pattern="yyyy-MM-dd HH:mm:ss"/>

//��ʾ�������֣�ǰ81���֣�
${fn:substring(Article.summary, 0, 80)

//�Զ��庯����
1:src/util/Functions.java
2:WebRoot/WEB-INF/drp-functions.tld
3:<%@ taglib prefix="drp" uri="http://www.zcj.com/functions"%> 
	${drp:getLylxList()}



//ѭ���̶�����
<table>
	<c:forEach items="${artList}" var="article" varStatus="current">
		<c:if test="${current.index<5}">//current.index��0��ʼ
			<tr><td></td></tr>
		</c:if>	
	</c:forEach>
</table>

<table>
	<c:forEach begin="0" end="3" var="article" varStatus="articleStatus">
		<tr><td></td></tr>//articleStatus.index��0��3
	</c:forEach>
</table>


</body>
</html>