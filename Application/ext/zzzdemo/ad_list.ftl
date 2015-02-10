<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/admin/styles/reset.css?v=${sversion}" media="screen" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/admin/styles/common.css?v=${sversion}" media="screen" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/admin/styles/form.css?v=${sversion}" media="screen" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/admin/styles/table.css?v=${sversion}" media="screen" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/admin/styles/button.css?v=${sversion}" media="screen" />
	
	<script type="text/javascript" src="${contextPath}/ext/jquery/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/ext/layer/layer.min.js"></script>
	<script type="text/javascript" src="${contextPath}/ext/zw/grid.js?v=${sversion}"></script>
	<script type="text/javascript" src="${contextPath}/ext/zcommon.js?v=${sversion}" basepath="${contextPath}"></script>
</head>

<body>
<div class="guide">
    <a href="#" title="">广告设置</a>
</div>
<div class="space">
    <div>
    	<#-- 
    	<div id="searchDiv">
    		<select id="depSelect" size="1">
    			<option value="">--请选择部门--</option>
			</select>
			<input id="checkDea" type="checkbox" name="defaults" value="1" ><label for="checkDea">只看默认</label>
			<input id="searchKey" type="text" size="20"><a id="searchBtn" href="javascript:void(0);" class="c-btn primary-label">搜索</a>
    	</div>
    	 -->
    	<div class="list-hd">
        	<a onclick="add()" href="#" title="" class="c-btn primary-label">添加</a>
        	<a onclick="removeItems()" href="#" title="" class="c-btn primary-label">删除</a>
            <b class="round-left"></b>
            <b class="round-right"></b>
        </div>
    	<table id="grid" class="list-bd"></table>
        <div class="flip-box">
            <div class="flip"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
	var grid = '';
	$(function(){
		grid = $('#grid').grid({
			url: '${contextPath}/ad/list.ajax',
			start: 0,
			limit: 10,
			bbar: {
				pagingBar: true
			},
			columns: [
				{
					type: 'checkbox',
					name: 'ids',
					dataIndex: 'id'
				},
				{
					dataIndex: 'name',
					align: 'left',
					header: '名称'
				},
				{
					type: 'custom',
					dataIndex: 'type',
					header: '类型',
					renderer: function(value) {
						if (value == 10) {
							return "弹出";
						} else if (value == 11) {
							return "自由移动";
						} else {
							return value;
						}
					}
				},
				{
					dataIndex: 'orders',
					header: '排序号'
				},
				{
					type: 'custom',
					dataIndex: 'state',
					header: '状态',
					renderer: function(value){
						if(value){
							return '<span style="color:blue">启用</span>';
						}else{
							return '<span style="color:red">停用</span>';
						}
					}
				},
				{
					type: 'custom',
					dataIndex: 'logo',
					align: 'left',
					header: '文件',
					renderer: function(value) {
						return "<a href=\"${contextPath}"+value+"\" target=\"_blank\" >"+value+"</a>";
					}
				},
				{
					type: 'custom',
					dataIndex: 'url',
					align: 'left',
					header: '链接地址',
					renderer: function(value) {
						return "<a href='"+value+"' target='_blank'>"+value+"</a>";
					}
				},
				{
					dataIndex: 'ctime',
					header: '添加日期'
				},
				{
					type: 'custom',
					dataIndex: 'id',
					renderer: function(value, record){
						var states = record.states;
						var content = '<a href="#" onclick="editItem($(this));" class="c-btn primary-label">编辑</a>';
						//显示隐藏切换
						value ? (content += '<a href="#" onclick="showHide($(this));" class="c-btn primary-label" >隐藏</a>'):(content += '<a href="#" onclick="showHide($(this));" class="c-btn primary-label" >显示</a>');
						return content;
					},
                    header: '操作'
				}
			]
		});
	});
	
	//添加
	function add(){
		z_openIframe('新增', 620, 500, '${contextPath}/ad/toadd.do');
		
		// window.location.href = '${contextPath}/ztTopicArticle/toadd.do';
	}
	
	//编辑
	function editItem(that){
		var data = that.parents('tr').data();
		z_openIframe('编辑', 620, 500, '${contextPath}/ad/tomodify/' + data.id + '.do');
		
		// window.location.href = '${contextPath}/ztTopicArticle/tomodify/' + data.id + '.do';
	}

	//删除
	function removeItems(){
		var data = $('#grid').getSelectedValuesString();
		z_delete(data, '${contextPath}/ad/delete.ajax');
	}
	
	//显示隐藏
	function showHide(that){
		var data = that.parents('tr').data();
		//已显示--隐藏文章
		if(data.valid == 1){
			$.post('${contextPath}/article/cancelvalid/' + data.id + '.do', function(json) {
	            if(json.s){
	                grid.reload();
	            }else{
	                layer.alert(json.d, 8);
	            }
	        }, "json");
		}else{//未显示--显示文章
			$.post('${contextPath}/article/valid/' + data.id + '.do', function(json) {
	            if(json.s){
	                grid.reload();
	            }else{
	                layer.alert(json.d, 8);
	            }
	        }, "json");
		}
	}
	
	<#-- 
	$('#searchBtn').click(function(){
    	var params = {
    		departmentId: $('#depSelect').val(),
    		searchKey: $('#searchKey').val(),
    		defaults: $('#checkDea').is(':checked')?1:0,
    		start: 0
    	};
    	//读取数据
    	grid.load(params);
    });
    $('#searchKey').keydown(function(e){
		if(e.keyCode==13){
		   $('#searchBtn').click(); //处理事件
		}
	});
	$('#depSelect').add('#checkDea').change(function(){
		$('#searchBtn').click();
	});
	-->
</script>
</body>
</html>