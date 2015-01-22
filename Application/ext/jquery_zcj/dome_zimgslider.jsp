<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>zimgslider--多图片上传显示插件</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/ext/jquery_zcj/jquery.zimgslider.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/ext/jquery_zcj/jquery.zimgslider.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	
	// 初始化渲染图片(编辑状态,imgUrls是用英文逗号隔开的图片地址)
	$("#addOrModify_imgs").zImgslider_init(imgUrls,true);
	
	// 初始化渲染图片(不可编辑状态,imgUrls是用英文逗号隔开的图片地址)
	$("#addOrModify_imgs").zImgslider_init(imgUrls);
	
});

// 上传成功后调用(8表示最多8张)
$("#addOrModify_imgs").zImgslider_addImg(imgUrl,8);

// 获取图片的地址集合(保存时调用，取值后传给后台)
$("#addOrModify_imgs").zImgslider_getImgUrls();

</script>
</head>
<body>

		<td>
			<div id="addOrModify_imgs"></div>
			<input id="file_upload" type="file"/>
		</td>

</body>
</html>