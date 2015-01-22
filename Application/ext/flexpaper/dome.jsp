<html>
<head>
<title>FlexPaper--SWF阅读器</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1,user-scalable=0,width=device-width" />
<script type="text/javascript" src="../jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/flexpaper.js"></script>
</head>
<body>

	<div id="documentViewer" class="flexpaper_viewer" style="position: absolute; left: 10px; top: 10px; width: 98%; height: 98%"></div>

	<script type="text/javascript">
		var startDocument = "Paper";
		$('#documentViewer').FlexPaperViewer({
			config : {
				SWFFile : 'test.swf',
				jsDirectory : '../../ext/flexpaper/js/',
				key : '$659bda0e09811de23c6',
				Scale : 1,
				ZoomTransition : 'easeOut',
				ZoomTime : 0.5,
				ZoomInterval : 0.2,
				FitPageOnLoad : false,
				FitWidthOnLoad : false,
				FullScreenAsMaxWindow : false,
				ProgressiveLoading : true,
				MinZoomSize : 0.2,
				MaxZoomSize : 5,
				SearchMatchAll : false,
				InitViewMode : 'Portrait',
				RenderingOrder : 'flash,html',
				MixedMode : true,
				ViewModeToolsVisible : true,
				ZoomToolsVisible : true,
				NavToolsVisible : true,
				CursorToolsVisible : true,
				SearchToolsVisible : true,
				PrintEnabled : true,
				PrintVisible : true,
				localeChain : 'zh_CN'
			}
		});
	</script>

</body>
</html>
