<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name=Generator content="Microsoft Word 12 (filtered)">
<title>温州市名师工作站（室）学员申报表</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/ext/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){window.print();});
</script>
</head>
</html>

<%-- 
打印功能的步骤
	1、编辑word文档（格式尽量简单，不需要分页）
	2、使用office word打开，另存为“筛选过的网页(*.htm;*.html)”
	3、用记事本打开*.htm，拷贝内容到*.jsp里（包括style和body）
	4、在该分页的地方添加：<P class=normal style="PAGE-BREAK-BEFORE: always">&nbsp;</P>
		>> 选择表格--表格属性--单元格--选项--去掉“自动换行”
		>> 选择表格--居中
		>> 选择表格--表格属性--指定宽度100%（或去掉指定宽度）
	5、静态改为动态(图片使用<%=request.getContextPath()%><s:property value="obj.zp"/>替代)
	6、JS里添加：$(document).ready(function(){window.print();});
	7、请求打印的页面添加：
		function _print() {
			window.open("<%=request.getContextPath() %>/2027?id=<s:property value="obj.id"/>");
		}
		
 --%>