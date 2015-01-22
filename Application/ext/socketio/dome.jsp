<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Socket.io客户端</title>

<script type="text/javascript" src="<%=request.getContextPath() %>/ext/socketio/socket.io.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/ext/jquery/jquery-1.7.1.min.js"></script>
<script>

var roomUrl = 'http://<%=request.getRemoteHost()%>:1105/chat';
var chat1Socket = io.connect(roomUrl);

chat1Socket.on('connect', function() {
	alert("客户端连上后触发");
});
chat1Socket.on('disconnect', function() {
	alert("客户端断开后触发");
});
chat1Socket.on('my_chat_fromServer', function(data) {
	alert("接收服务端发来的my_chat_fromServer命令");
});
function sendDisconnect() {
	chat1Socket.disconnect();
}
function sendMessage() {
	chat1Socket.emit("my_chat_toServer", {
		name : "zzz",
		message : "fsadfdsf"
	});
	alert("向服务器发起my_chat_toServer命令");
}
</script>
</head>