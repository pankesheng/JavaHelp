<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Pragma" content="no-cache" />
		<script type="text/javascript" src="ajax-pushlet-client.js"></script>		
		<script type="text/javascript">
			PL._init(); /* 对pushlet的初始化，触发web.xml中的servlet */
			PL.joinListen('/cuige/he');
			function onData(event) { 
				document.getElementById("test").innerHTML = event.get("mess");
			}
		</script>
	</head>
	<body>
		<span id="test"></span>
	</body>
</html>


