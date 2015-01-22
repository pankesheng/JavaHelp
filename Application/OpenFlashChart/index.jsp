<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ofc/swfobject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ofc/json2.js"></script>
<script type="text/javascript">
	swfobject.embedSWF(
	"<%=request.getContextPath() %>/js/ofc/open-flash-chart.swf", 
	"my_chart", 
	"800", 
	"250", 
	"9.0.0",
	"expressInstall.swf",
	{"data-file":"<%=request.getContextPath()%>/abc.do"},
	{wmode:"transparent"});
</script>
<title>首页</title>
</head>
<body>
<div id="my_chart"></div>
</body>
</html>