<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    response.reset();
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/ms-word");
    
    String fileName = "xxx版.doc";
    
    response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
%>
<?mso-application progid="Word.Document"?>

<w:wordDocument>
</w:wordDocument>


/** 
	1：用office word 打开，Word模板另存为"Word 2003 XML 文档.xml"
	2：复制xml文件内容的<w:wordDocument>部分到jsp页面
	3：修改内容的静态内容为动态
*/