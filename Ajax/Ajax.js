
//原理：使用DOM进行动态显示和交互；使用XMLHttpRequest进行异步数据交互
function myVerify(){
	var userName = document.getElementById("userName").value;
	
	//创建对象，不同的浏览器有不同的代码
	var Xmlhttprequest;
	if(window.XMLHttpRequest){
		Xmlhttprequest = new XMLHttpRequest();
	}else{
		Xmlhttprequest = new ActiveXObject("Msxml2.XMLHTTP");
	}
	
	//注册回调函数
	Xmlhttprequest.onreadystatechange = callback;
	
	//设置连接信息，true表示异步
	Xmlhttprequest.open("GET","my.do?name=" + userName,true);
	
	//发送数据，开始和服务器交互
	Xmlhttprequest.send(null);
}

/*
onreadystatechange:
	是XMLHttpRequest中的一个事件,当服务器响应或状态发生变化的时候就会触发的事件
	服务器响应404：页面未找到
	服务器响应500：服务器内部错误
	服务器响应200：一切正常
xmlHttp.readyState:
	==0 : 已经创建一个XMLHttpRequest对象,但"未初始化"
	==1 : 已经调用XMLHttpRequest.open()方法并已经准备好把一个请求发送到服务器
	==2 : 已经通过send()方法把一个请求发送到服务器,但没收到响应
	==3 : 已经接受到HTTP响应头部信息,消息体没完全接受
	==4 : 已经完全接受响应
*/
function callback(){
	//判断是否交互成功
	if(Xmlhttprequest.readyState==4){
		//判断是否交互成功
		if(Xmlhttprequest.States==200){
			//获取服务器返回的文本数据
			alert(xmlhttprequest.responseText);
		}
	}
}
