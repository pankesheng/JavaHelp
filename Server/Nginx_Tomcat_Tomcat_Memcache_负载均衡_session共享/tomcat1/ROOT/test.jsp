<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<title>tomcat1</title>
</head>
<body>
	Server Info:
	<%
		out.println(request.getLocalAddr() + ":" + request.getLocalPort());
	%>
	<br>
	<br>
	<%
		out.println("<br> ID " + session.getId()+"<br>");
		String dataName = request.getParameter("dataName");
		if (dataName != null && dataName.length() > 0) {
			String dataValue = request.getParameter("dataValue");
			session.setAttribute(dataName, dataValue);
		}
		out.print("<b>Session list</b> <br>");
		Enumeration e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String name = (String)e.nextElement();
			String value = session.getAttribute(name).toString();
			out.println( name + " = " + value+"<br>");
			System.out.println( name + " = " + value);
		}
	%>
	<form action="test.jsp" method="POST">
		name:<input type=text size=20 name="dataName"> <br> 
		key:<input type=text size=20 name="dataValue"> <br> 
		<input type=submit>
	</form>
</body>
</html>
