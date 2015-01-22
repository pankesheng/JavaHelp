

function setCookie(name,value) { 
    var Days = 30;
    var exp = new Date(); 
    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
} 
function getCookie(name) { 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]); 
    else 
        return null; 
}

/*数据类型*/
	function init(){
		var a = "5";	//String
		var b = 5;		//Number
		var c = false;	//boolean
		var d;			//undefined
		var e = null;	//null
		var f = new String("abc");				//Object
		var g = ["first", "second", "third"]; 	//数组
		var h = parseInt(b);	//Number
		if(15)					//非0数字=true、其他=false
		alert(typeof a);		//弹出a的数据类型String。
	}

	function ArrayTest() {
		var markersArray = [];
		markersArray.push("1");// 添加元素
		markersArray.push("2");
		markersArray.sort();// 排序
		// markersArray.join(",");转成字符串
		if (markersArray) {
			for (i in markersArray) {
				alert(markersArray[i]);
			}
	    }
		markersArray.length = 0;
	}

/*对象(Math/Date/String)*/
	function testMath() {
		var money = document.getElementById("money").value;
		alert(Math.round(money));	//四舍五入
		alert(Math.random());	//取0-1中的随机值
		alert(Math.floor(1.1111));	//去掉小数点
		alert(Math.floor(Math.random()*10));//取0-30中的随机整数值
	}
	function testDate() {
		var d = new Date();
		var date = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDay();
		var time = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		var day = d.getDay();	//取得星期几
		alert(date + day + time);
		setTimeout("testDate()",1000);//一秒运行testDate方法一次
	}
	function testString() {
		var em = document.getElementById("money").value;
		alert(em.replace(new RegExp(/(,)/g),'\r'));// 逗号替换成换行，弹出
		$('#message').html(em.replace(new RegExp(/(,)/g),'<br/>'));// 逗号替换成换行，输出
	}
	
	
正则表达式
	function check() {
		var pattern = /^[0-9]$/;	//表示0-9任一数字的正则表达式
		var v = document.getElementById("userName").value;
		var flag = pattern.test(v);	//用正则表达式pattern验证v,返回Boolean型
	}


对象的定义和访问
	function obj1(){
		obj1.prototype.username="zero";
		obj1.prototype.sayHello1=obj1_sayHello1_fun;
		obj1.prototype.sayHello2=function(s){
			alert(s);
		}
	}
	function obj1_sayHello1_fun(){
		alert("Hello!");
	}
	function obj2(){
		this.name="zero";
		this.age=18;
		this.sayHello=function(s){
			alert(s);
		}
	}
	function main(){
		var _o1 = new obj1();	/* prototype定义对象 */
		alert(_o1.username);
		_o1.sayHello1();
		_o1.sayHello2("Hello");
		
		var _o2 = new obj2();	/* this定义对象 */
		_o2.sayHello("Hello");
		
		var _o3 = 	/* JSON、键值对方式 */
		{		
				name : "abc",
				age : 18,
				sayHello : function(){alert("Hello");}
		};	
		alert(_o3.age);
		_o3.sayHello();
		
		var _str1 = "{name:'bcd',age:22}";	/* String转成JSON */
		alert(eval("("+_str1+")").name);
	}

对象的继承
	function Person(){
		Person = function(e) {alert(e);};	/* 构造方法 */
		Person.cityName="北京";	/* static属性 */
		Person.prototype.name="张三";
		Person.prototype.age=30;
		Person.prototype.sayHello=function(){
			alert("Hello!");
		}
	}
	function Student(){
		var gfName = "lingling"; /* private属性 */
		Student.prototype.schoolName="北风网";
	}
	function main(){
		Student.prototype = new Person(); /* Student继承Person */
		var stu = new Student();
		alert(stu.name);
		new Person();	/* 访问对象的static属性前要先new对象 */
		alert(Person.cityName);	/* 访问static属性 */
	}
