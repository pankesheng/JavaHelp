<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<script type="text/javascript" src="<%=request.getContextPath() %>/test_vod/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript"  src="<%=request.getContextPath() %>/test_vod/js/jwplayer6.6/jwplayer.js" ></script>
<style type="text/css">
	*{padding: 0;margin: 0;}
	html,body{overflow: hidden;}
</style>
<script type="text/javascript">
	jwconfig.path="<%=request.getContextPath() %>/test_vod/js/jwplayer6.6/";
    var thePlayer;
    var count = 0;
    $(function(){
    	thePlayer = jwplayer('container').setup({
    		flashplayer:'<%=request.getContextPath() %>/test_vod/js/jwplayer6.6/jwplayer.flash.swf',
            file: '<%=request.getContextPath() %>/test_vod/bb_stream.mp4',  
            width:960,  
            height:480,
            image:'<%=request.getContextPath() %>/test_vod/bb_photo.jpg',
            primary:"flash",
            startparam: "start",
            events: {
            	onPlay:function(s){
            		play(++count,s);
            	}
            }
        });
    });
    var domain = '<%=request.getParameter("domain")==null?"":request.getParameter("domain") %>';
    if(domain){
    	try{
    		document.domain = domain;
    	}catch(e){}
    }
    function play(count,s){
    	if(window.parent&&window.parent.play){
    		window.parent.play(count,s);
    	}
    }
   	
</script>
</head>
<body>
	<div>
		<div id="container"></div>
	</div>
</body>
</html>