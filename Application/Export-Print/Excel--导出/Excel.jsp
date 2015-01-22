<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	response.reset();
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/ms-excel");
    
	String fileName = "xxx版.xls";
	
    response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
%>
<?mso-application progid="Excel.Sheet"?>
<Workbook ...>
	...
</Workbook>

/** 	
	1：Excel模板另存为转成xml文件
	2：复制xml文件内容的<Workbook>部分到jsp页面
	3：修改内容的静态内容为动态(注意：table标签的ss:ExpandedRowCount属性为最大行数)
	4: <s:property value="content" escape="false"/>
*/
