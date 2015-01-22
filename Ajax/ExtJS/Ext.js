
	Ext.namespace('Ext.zcj');	//定义包(命名空间)
	Dc = Ext.zcj;	//定义别名
	var tValue = Ext.getCmp("myId").getValue();	//getCmp:得到组件id=myId的对象的值
	
	/** 默认事件-触发窗口显示
	 * handler : function() {new Ext.Window({...}).show();}
	 */
	
	/** 各类事件解析
	 * render--组件渲染(正确构造)之后触发
	 */

	/** 渲染组件到页面指定的DIV中
		//<body>
		//	<div id="loginpanel"></div>
		//</body>
		 
		var panel = new Ext.Panel({
			renderTo : 'loginpanel'
		})
	 */
	
	/**	元素居中
	 	方法一： Ext.get('loginpanel').setStyle('position','absolute').center(Ext.getBody());
		方法二：
			.contain {
				width:100%;
				height:100%;
				top:0;
				left:0;
			}
			.center {
				position:absolute;
				top:30%;
				left:43%;
				text-align:left;
			}
			_panel.applyToMarkup(	//注册到class为center的div之上
					Ext.DomHelper.append(
							Ext.getBody(),
							{
								tag : "div",
								cls : "contain",
								cn : [{tag : "div", cls : "center"}]
							}, 
							true
					).child("div")
			);
	 */
		
	/** Ext.extend
		Ext.zcj.Person = function() {	//Person的构造方法
			this.addEvents(
				"namechange",
				"sexchange"
			);
		};
		Ext.extend(	//Person继承Observable
			Ext.zcj.Person,
			Ext.util.Observable,
			{
				name : "",
				sex : "",
				setName : function(_name){
					if (this.name != _name) {
						this.fireEvent("namechange", this, this.name, _name);
						this.name = _name;
					}
				},
				setSex : function(_sex){
					if (this.sex != _sex) {
						this.fireEvent("sexchange", this, this.sex, _sex);
						this.sex = _sex;
					}
				}
			}
		);
	
		var _person = null;
		button_click = function() {
			_person.setName(prompt("请输入姓名:", ""));
			_person.setSex(prompt("请输入性别", ""));
		}
		Ext.onReady(
			function() {
				var txt_name = Ext.get("txt_name");
				var txt_sex = Ext.get("txt_sex");
				_person = new Ext.zcj.Person();
				_person.on(
						"namechange",
						function(_person, _old, _new) {
							txt_name_dom.value = _new;
						}
				);
				_person.on(
						"sexchange",
						function(_person, _old, _new) {
							txt_sex_dom.value = _new;
						}
				);
			}	
		)
		//<input type="text" id="txt_name" />
		//<input type="text" id="txt_sex" />
		//<input type="button" value="输入" onclick="button_click()" />
	/* */	
		
	/** Ext.form
		Ext.onReady(function(){	//人员添加表单
			new Ext.Window({
				title: "添加人员",
				width: 500,
				height: 350,
				plain: true,	//渲染window的背景为透明
				layout: "form",
				labelWidth: 59,
				defaults: {xtype: "textfield"},	//设置items里的组件的默认属性
				items: [{
					baseCls: "x-plain",	//作用在面板元素上的CSS样式类 （默认为 'x-panel'）。
					style: "padding: 5px",	
					layout: "column",
					xtype: "panel",	//默认为panel
					items: [{
						columnWidth: .5,
						layout: "form",
						labelWidth: 55,
						baseCls: "x-plain",
						defaults: {xtype: "textfield", width: 160},
						items: [{
							fieldLabel: "姓名"
						},{
							fieldLabel: "年龄"
						},{
							xtype: "datefield",
							fieldLabel: "出生日期",
							format: "Y-m-d",
							value: new Date()	//也可取值"1999-09-09"
						},{
							fieldLabel: "联系电话"
						},{
							fieldLabel: "手机号码"
						},{
							fieldLabel: "电子邮件"
						},{
							xtype: "combo",
							fieldLabel: "性别",
							mode: "local",
							displayField: "sex",
							triggerAction: "all",
							value: "男",
							store: new Ext.data.SimpleStore({
										fields: ["sex"],
										data: [["男"],["女"]]
									})
						}]
					},{
						columnWidth: .5,
						layout: "form",
						labelWidth: 55,
						baseCls: "x-plain",
						items: {
							id: "photo",
							xtype: "textfield",
							fieldLabel: "个人照片",
							width: 170,
							height: 177,
							inputType: "image"
						}
					}]
				},{
					fieldLabel: "身份证号",
					width: 400
				},{
					fieldLabel: "具体地址",
					width: 400
				},{
					fieldLabel: "职位",
					anchor: "50%"
				}],
				showLock: false,
				listeners: {
					"show": function(_window){
						if(!_window["showLock"]){
							_window.findById("photo").getEl().dom.src = "B019.jpg";
							_window["showLock"] = true;
						}
					}
				},
				buttons: [{
				    text: "确定",
				    listeners: {"click": function() {	//click事件触发时调用
						alert("Hello World");
					}}
				},{
					text: "取消",
					handler: function() {	//handler:默认事件触发时调用
						alert("Hello");
					}
				}]
			}).show();
		});
	*/
	
	/** Ext.MessageBox
		Ext.MessageBox.alert("欢迎","很高兴能够和大家一起来学习ExtJS!",function(e) {
			if (e == "ok") {
				document.write("你单击了OK按钮");
			} else if (e == "cancel") {
				document.write("你单击了cancel按钮");
			}
		});
	
		Ext.MessageBox.confirm("保存文件","是否保存文件",function(e) {
			if(e == "yes") {
				Ext.MessageBox.alert("成功","保存文件成功");
			} else if(e == "no") {
				Ext.MessageBox.alert("失败","不保存文件");
			}
		});
	
		Ext.MessageBox.prompt("姓名","请输入您的姓名",function(e,text) {
			if(e == "ok") {
				document.write(text);
			}
		},null,200);
		
		Ext.MessageBox.show({
			title:"欢迎",
			msg:"很高兴能够和大家一起来学习ExtJS!",
			buttons:Ext.MessageBox.OKCANCEL,
			prompt:true,
			multiline:true,
			defaultTextHeight:300,
			icon:Ext.MessageBox.ERROR
		});
		Ext.MessageBox.show({
			title:"进度条",
			msg:"5秒后自动进入系统",
			progress:true,
			width:400,
			wait:true,
			waitConfig:{
				interval:540,
				duration:5000,
				fn:function() {
					Ext.MessageBox.hide();
				}
			}
		});
	/* */
		
	/** Ext.Ajax
	//POST:
		Ext.Ajax.on(
			"requestcomplete", 
			function(_conn, _response, _options) {
				alert(_response.responseXML.xml);
			}
		);
		Ext.Ajax.request({url: "a.jsp", params: {name: "邹崇锦", sex: "男"}});	
		<% Write.writeXML(XMLMessage.getPostInstance()); %>
			
	//GET:
		Ext.Ajax.on(
			"requestcomplete", 
			function(_conn, _response, _options) {
				alert(_response.responseXML.xml);
			}
		);
		Ext.Ajax.request({url: "a.jsp", method: "GET", params: {name: "邹崇锦", sex: "男"}});	
		<% Write.writeXML(XMLMessage.getGetInstance()); %>
		
	//XML:
		Ext.Ajax.on(
			"requestcomplete", 
			function(_conn, _response, _options) {
				alert(_response.responseXML.xml);
			}
		);
		var _msg = new Message();
		_msg.setVariable("name", "邹崇锦");
		_msg.setVariable("sex", "男");
		Ext.Ajax.request({url: "a.jsp", xmlData: _msg.getXML});	
		<% Write.writeXML(XMLMessage.getAjaxInstance()); %>
		
	//JSON:	
		Ext.Ajax.on(
			"requestcomplete", 
			function(_conn, _response, _options) {
				alert(_response.responseText);
			}
		);
		Ext.Ajax.request({url: "a.jsp", jsonDate: {name: "邹崇锦", sex: "男"}});	
		<% Write.writeHTML(); %>	//GBK乱码
		<% Write.writeXML(XMLMessage.getPostInstance()); %>
		
	//图书管理系统
		Ext.Ajax.request({
			url : 'updateCompany.action',
			params : {
				fieldName : e.field,
				fieldValue : e.value,
				companyId : e.record.data.companyId
			}
		})
		
		//String fieldName = getRequest().getParameter("fieldName");
		//String fieldValue = getRequest().getParameter("fieldValue");
		//String strCompanyId = getRequest().getParameter("companyId");
		//success = true;
		//...
		//return SUCCESS;
		
		//<package name="json" extends="json-default">
		//	<action name="updateCompany" class="companyAction" method="updateCompany">
		//		<result type="json">
		//			<param name="includeProperties">success</param>
		//		</result>
		//		<result type="json">	true过滤空值
		//			<param name="excludeNullProperties">true</param>
		//		</result>
		//		<result type="json">	false同时返回父类属性
		//			<param name="ignoreHierarchy">false</param>
		//		</result>
		//		<result type="json">	需要包含的属性值
		//			<param name="includeProperties">person.*, id</param>
		//		</result>
		//		<result type="json"> 	剔除掉id和name
		//			<param name="excludeProperties">id, name</param>
		//		</result>
		//		<result type="json"> 	取对象或对象下的属性
		//			<param name="root">person</param>
		//		</result>
		//	</action>
		//</package>
	/* */
	
	/** Ext.Panel
	//自动加载面板体的默认连接
		var panel = new Ext.Panel({
			autoLoad: "page1.html"
		});
	//加载本地资源
		var panel = new Ext.Panel({
			contentEl: "localElement"
		});
		//<table border=1 id='localElement'>
		//	...
		//</table>		
	//使用html配置项自定义面板内容
		Ext.onReady(function(){
			var htmlArray = [
								'<table border=1>',
									'<tr><td>6</td><td>杨八</td></tr>',
									'<tr><td>7</td><td>刘九</td></tr>',
								'</table>'
							];
			var panel = new Ext.Panel({
				html: htmlArray.join('')
			})
		});
	//使用items配置项在面板中添加组件
		Ext.onReady(function(){
			var panel = new Ext.Panel({
				items: new Ext.DatePicker()
			})
		});
	/* */	
		
	/** Ext.layout.CardLayout
		Ext.onReady(function(){
			var panel = new Ext.Panel({
				layout : 'card',
				activeItem : 0,                //设置默认显示第一个子面板
				items: [
					{
						title : '嵌套面板一',
						html : '说明一',
						id : 'p1'
					},{
						title : '嵌套面板二',
						html : '说明二',
						id : 'p2'
					}
				],
				buttons:[
					{
						text : '上一页',
						handler : changePage
					},{
						text : '下一页',
						handler : changePage
					}
				]
			})
			function changePage(btn){
				var index = Number(panel.layout.activeItem.id.substring(1));
				if(btn.text == '上一页'){
					index -= 1;
					if(index < 1){
						index = 1;
					}
				}else{
					index += 1;
					if(index > 3){
						index = 3;
					}
				}
				panel.layout.setActiveItem('p'+index);
			}
		});
	 */
	
	/** Ext.TabPanel
		Ext.onReady(function(){
			var tabPanel = new Ext.TabPanel({
				applyTo: 'panel',		 //指明哪个div
				height: 50,
				width: 300,
				autoTabs: true,         //查询DOM中任何带"x-tab'样式类的div元素,转化为tab加入到此面板中
				deferredRender: false,  //不进行延时渲染
				activeTab: 0,           //默认激活第一个tab页
				animScroll: true,       //使用动画滚动效果
				enableTabScroll: true   //tab标签超宽时自动出现滚动按钮
			});
		});
		//	<div id='panel'>
		//	<div class='x-tab' title='tab标签页1'>tab标签页1内容</div>
		//	<div class='x-tab' title='tab标签页2'>tab标签页2内容</div>
		//	<div class='x-tab' title='tab标签页3'>tab标签页3内容</div>
		//	<div class='x-tab' title='tab标签页4'>tab标签页4内容</div>
		//	<div class='x-tab' title='tab标签页5'>tab标签页5内容</div>
		//	</div>
	*/
	
	
	
	
	
	
	
	
	

	