

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

/*��������*/
	function init(){
		var a = "5";	//String
		var b = 5;		//Number
		var c = false;	//boolean
		var d;			//undefined
		var e = null;	//null
		var f = new String("abc");				//Object
		var g = ["first", "second", "third"]; 	//����
		var h = parseInt(b);	//Number
		if(15)					//��0����=true������=false
		alert(typeof a);		//����a����������String��
	}

	function ArrayTest() {
		var markersArray = [];
		markersArray.push("1");// ���Ԫ��
		markersArray.push("2");
		markersArray.sort();// ����
		// markersArray.join(",");ת���ַ���
		if (markersArray) {
			for (i in markersArray) {
				alert(markersArray[i]);
			}
	    }
		markersArray.length = 0;
	}

/*����(Math/Date/String)*/
	function testMath() {
		var money = document.getElementById("money").value;
		alert(Math.round(money));	//��������
		alert(Math.random());	//ȡ0-1�е����ֵ
		alert(Math.floor(1.1111));	//ȥ��С����
		alert(Math.floor(Math.random()*10));//ȡ0-30�е��������ֵ
	}
	function testDate() {
		var d = new Date();
		var date = d.getFullYear() + "-" + (d.getMonth()+1) + "-" + d.getDay();
		var time = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
		var day = d.getDay();	//ȡ�����ڼ�
		alert(date + day + time);
		setTimeout("testDate()",1000);//һ������testDate����һ��
	}
	function testString() {
		var em = document.getElementById("money").value;
		alert(em.replace(new RegExp(/(,)/g),'\r'));// �����滻�ɻ��У�����
		$('#message').html(em.replace(new RegExp(/(,)/g),'<br/>'));// �����滻�ɻ��У����
	}
	
	
������ʽ
	function check() {
		var pattern = /^[0-9]$/;	//��ʾ0-9��һ���ֵ�������ʽ
		var v = document.getElementById("userName").value;
		var flag = pattern.test(v);	//��������ʽpattern��֤v,����Boolean��
	}


����Ķ���ͷ���
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
		var _o1 = new obj1();	/* prototype������� */
		alert(_o1.username);
		_o1.sayHello1();
		_o1.sayHello2("Hello");
		
		var _o2 = new obj2();	/* this������� */
		_o2.sayHello("Hello");
		
		var _o3 = 	/* JSON����ֵ�Է�ʽ */
		{		
				name : "abc",
				age : 18,
				sayHello : function(){alert("Hello");}
		};	
		alert(_o3.age);
		_o3.sayHello();
		
		var _str1 = "{name:'bcd',age:22}";	/* Stringת��JSON */
		alert(eval("("+_str1+")").name);
	}

����ļ̳�
	function Person(){
		Person = function(e) {alert(e);};	/* ���췽�� */
		Person.cityName="����";	/* static���� */
		Person.prototype.name="����";
		Person.prototype.age=30;
		Person.prototype.sayHello=function(){
			alert("Hello!");
		}
	}
	function Student(){
		var gfName = "lingling"; /* private���� */
		Student.prototype.schoolName="������";
	}
	function main(){
		Student.prototype = new Person(); /* Student�̳�Person */
		var stu = new Student();
		alert(stu.name);
		new Person();	/* ���ʶ����static����ǰҪ��new���� */
		alert(Person.cityName);	/* ����static���� */
	}
