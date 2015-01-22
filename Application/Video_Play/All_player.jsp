<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


object的参数：
<div>
<object classid="clsid:05589FA1-C356-11CE-BF01-00AA0055595A" id="ActiveMovie1" width="320" height="300">
align		定义围绕该对象的文本对齐方式。 TF 
archive		预先下载包含一个或多个档案中的对象集。
			值是一个用引号括起来的 URL 列表，其中每个 URL 都指向一个在显示或执行对象之前浏览器需要加载的档案文件。
border  	定义对象周围的边框。 TF 
classid  	定义嵌入 Windows Registry 中或某个 URL 中的类的 ID 值，此属性可用来指定浏览器中包含的对象的位置，通常是一个 Java 类。 STF 
codebase 	提供一个基准 URL,该 URL 指向的目录包含了 classid 属性所引用的对象。STF 
codetype	用于标识程序代码类型。只有在浏览器无法根据 classid 属性决定 applet 的 MIME 类型，
			或者如果在下载某个对象时服务器没有传输正确的 MIME 类型的情况下，才需要使用 codetype 属性。STF 
data  		定义引用对象数据的 URL。如果有需要对象处理的数据文件,要用 data 属性来指定这些数据文件。 STF 
declare 	可定义此对象仅可被声明，但不能被创建或例示，能够延迟下载对象的时间，直到此对象得到应用为止。 STF 
height  	定义对象的高度。 STF 
hspace  	定义对象周围水平方向的空白。 TF 
name  		为对象定义唯一的名称（以便在脚本中使用）。 STF 
standby  	定义当对象正在加载时所显示的文本。 STF 
type  		定义被规定在 data 属性中指定的文件中出现的数据的 MIME 类型。 STF 
usemap  	规定与对象一同使用的客户端图像映射的 URL。 STF 
vspace  	定义对象的垂直方向的空白。 TF 
width  		定义对象的宽度。 STF 
</object>
</div>

param参数
<div>
<object>
<param name="AudioStream" value="-1"> 				//1、 设置音频流的编号(用于多音频流的剪辑，默认为-1)
<param name="AutoSize" value="-1"> 					//2、 设置是否自动调整控件大小来适应载入的媒体
<param name="AutoStart" value="-1"> 				//3、 设置在载入媒体文件之后是否自动开始播放
<param name="AnimationAtStart" value="-1"> 			//4、 设置控件开始播放之前是否先播放一个动画序列
<param name="AllowScan" value="-1"> 				//5、 设置是否允许扫描
<param name="AllowChangeDisplaySize" value="-1"> 	//6、 设置最终用户是否能设置显示尺寸
<param name="AutoRewind" value="0"> 				//7、 设置媒体文件播放完毕后是否自动回绕
<param name="Balance" value="0"> 					//8、 设置指定立体声媒体文件的播放声道
<param name="BaseURL" value> 						//9、 返回基本的 HTTP URL
<param name="BufferingTime" value="5"> 				//10、 返回缓冲的时间
<param name="CaptioningID" value> 					//11、 返回在标题中显示的帧或控件的名称
<param name="ClickToPlay" value="-1"> 				//12、 设置是否可以通过点击图像暂停或播放视频
<param name="CursorType" value="0"> 				//13、 设置指针类型
<param name="CurrentPosition" value="-1"> 			//14、 设置剪辑的当前位置
<param name="CurrentMarker" value="0"> 				//15、 设置当前书签号码
<param name="DefaultFrame" value> 					//16、 设置控件的默认目标 Http 帧
<param name="DisplayBackColor" value="0"> 			//17、 设置显示面板的背景色(OLE_COLOR 值)
<param name="DisplayForeColor" value="16777215"> 	//18、 设置显示面板的前景色(OLE_COLOR 值)
<param name="DisplayMode" value="0"> 				//19、 设置显示面板是否用秒或帧的形式显示当前位置(MPDisplayModeConstants 值)
<param name="DisplaySize" value="0"> 				//20、 设置图像显示窗口的大小(MPDisplaySizeConstant 值)
<param name="Enabled" value="-1"> 					//21、 设置控件是否可用
<param name="EnableContextMenu" value="-1"> 		//22、 设置是否允许在图像窗口右击鼠标可以打开关联菜单
<param name="EnablePositionControls" value="-1"> 	//23、 设置位置控制是否可用
<param name="EnableFullScreenControls" value="0">	//24、 设置全屏幕控制是否可用
<param name="EnableTracker" value="-1"> 			//25、 设置搜索栏控制是否可用
<param name="Filename" value> 						//26、 设置要播放的剪辑的文件名称
<param name="InvokeURLs" value="-1"> 				//27、 设置 URL 是否自动发送请求
<param name="Language" value="-1"> 					//28、 设置用于本地化语言支持的当前区域语言
<param name="Mute" value="0"> 						//29、 设置控件是否播放声音
<param name="PlayCount" value="1"> 					//30、 设置一个剪辑播放的次数
<param name="PreviewMode" value="0"> 				//31、 设置控件是否处在预览模式
<param name="Rate" value="1"> 						//32、 设置回放帧频
<param name="SAMILang" value> 						//33、 设置 .smi 文件(字幕)的语言
<param name="SAMIStyle" value> 						//34、 设置 .smi 文件(字幕)的风格
<param name="SAMIFileName" value> 					//35、 指定 .smi 文件(字幕)的名字
<param name="SelectionStart" value="-1"> 			//36、 设置流的起始位置
<param name="SelectionEnd" value="-1"> 				//37、 设置流的结束位置
<param name="SendWarningEvents" value="-1"> 		//39、 设置控件是否发送警告事件
<param name="SendOpenStateChangeEvents" value="-1"> //38、 设置控件是否发送打开状态改变事件
<param name="SendPlayStateChangeEvents" value="-1"> //44、 设置控件是否发送播放状态改变事件
	<div>
	流状态属性包括：
	PlayState：播放状态；
	OpenState：打开状态；
	Bandwidth：带宽；
	支持的事件有：
	OpenStateChange：打开状态改变(仅当SendOpenStateChangeEvents属性为true时触发)
	PlayStateChange：播放状态改变(仅当SendPlayStateChangeEvents属性为true时触发)
	EndOfStream：流结束时触发；
	NewStream：打开新流时触发；
	网络接收属性包括：
	ReceptionQuality：接收质量；
	ReceivedPackets：已经收到的包；
	LostPackets：丢失的包；
	监测缓冲的属性有：
	BufferingTime：缓冲时间；
	BufferingCount：缓冲次数；
	BufferingProgress：缓冲进程；
	Buffering：缓冲事件；
	</div>
<param name="SendErrorEvents" value="-1"> 			//40、 设置控件是否发送错误事件
	<div>
	如果 SendErrorEvents 属性设置为 true，将不会显示错误框，而是发送错误事件；
	如果 SendErrorEvents 属性设置为 false，将显示错误框，而不发送错误事件
	Error 事件，指有危险性错误发生；
	Warning 事件，指发生了非危险性的错误；
	当你的应用程序接收到一个错误事件，你可以检测下面的属性来确定具体的错误信息：
	HasError：检测目前的媒体播放器是否有错误；
	ErrorCode：提供与该类型错误相关的代码值；
	ErrorDescription：提供错误的描述信息；
	ErrorCorrection：指定媒体播放器对该类型的错误进行校正；
	</div>
<param name="SendKeyboardEvents" value="0"> 		//41、 设置控件是否发送键盘事件
<param name="SendMouseClickEvents" value="0"> 		//42、 设置控件是否发送鼠标单击事件
<param name="SendMouseMoveEvents" value="0"> 		//43、 设置控件是否发送鼠标移动事件
	<div>
	MouseDown	当用户按下鼠标时产生；
	MouseUp		当用户释放鼠标时产生；
	MouseMove	当用户移动鼠标时产生；
	Click		当用户在媒体播放器上单击鼠标按钮时产生；
	DbClick		当用户在媒体播放器上双击鼠标按钮时产生；
	KeyDown		当用户按下一个键时产生；
	KeyUp		当用户释放一个键时产生；
	KeyPress	当用户按下并释放一个键时产生；
	</div>
<param name="ShowCaptioning" value="0"> 			//45、 设置是否显示字幕
<param name="ShowControls" value="-1"> 				//46、 设置控制面板是否可见
<param name="ShowAudioControls" value="-1"> 		//47、 设置是否显示音频控制
<param name="ShowDisplay" value="0"> 				//48、 设置是否显示显示面板
<param name="ShowGotoBar" value="0"> 				//49、 设置是否显示跳转栏
<param name="ShowPositionControls" value="-1"> 		//50、 设置是否显示位置控制
<param name="ShowStatusBar" value="0"> 				//51、 设置是否显示状态栏
<param name="ShowTracker" value="-1"> 				//52、 设置是否显示搜索栏
<param name="TransparentAtStart" value="0"> 		//53、 设置在开始播放之前和停止之后控件是否透明
<param name="VideoBorderWidth" value="0"> 			//54、 设置视频边框的宽度
<param name="VideoBorderColor" value="0"> 			//55、 设置视频边框的颜色(OLE_颜色)
<param name="VideoBorder3D" value="0"> 				//56、 设置视频边框是否显示为 3D 效果
<param name="Volume" value="-600"> 					//57、 设置音量
<param name="WindowlessVideo" value="0"> 			//58、 设置全屏如果是0可以允许全屏,否则只能在窗口中查看
</object>
	<div>
	播放列表
	
	媒体播放器提供下面的方法来访问播放列表中的剪辑：
	Next 方法，跳到节目（播放列表）中的下一个剪辑；
	Previous 方法，跳回到节目中的上一个剪辑；
	CanPreview 属性，决定媒体播放器能否处于预览模式；
	在windows 媒体元文件中，可以为每一个剪辑指定预览时间——PREVIEWDURATION,如果没有指定，那么默认的预览时间是10秒钟。
	你也可以用Windows 媒体元文件来添加 watermarks 与 banners，元文件也支持插入广告时的无间隙流切换。
	
	节目信息
	使用 GetMediaInfoString 方法可以返回相关剪辑或节目的如下信息：
	文件名：File name
	标题：Title
	描述：Description
	作者：Author
	版权：Copyright
	级别：Rating
	URLs：logo icon、watermark、banner的地址
	剪辑信息可以放在媒体文件中，也可以放在Windows 媒体元文件中，或者两者都放。如果在元文件中指定了剪辑信息，那么用 GetMediaInfoString 方法返回的就是元文件中的信息，而不会返回剪辑中包含的信息。
	在元文件中，附加信息可以放置在每一个剪辑或节目的 PARAM标签中。你可以为每个剪辑添加任意多个 PARAM 标签，用来存储自定义的信息或链接到相关站点。在 PARAM 标签中的信息可以通过 GetMediaParameter 方法来访问。
	下面的属性返回有关大小和时间的信息：
	ImageSourceHeight、ImageSourceWidth：返回图像窗口的显示尺寸；
	Duration 属性，返回剪辑的长度(秒)， 要检测这个属性是否包含有效的数值，请检查IsDurationValid 属性。(对于广播的视频，其长度是不可预知的)。
	</div>
</div>

embed参数
<div>
<EMBED src="music.mid" width="80" height="30"> 
src：音乐文件的路径及文件名
ShowTracker:为是否显示播放进度条 
ShowPositionControls:为是否显示播放控制按钮如快进等 
ShowAudioControls: 为控制是否显示音量按钮 
ShowStatusBar: 是否显示咨询窗
ShowDisplay: 为显示更完整的咨询视窗
EnableContextMenu: 防止使用右键
autostart：true为音乐文件上传完后自动开始播放，默认为false（否） 
loop：true为无限次重播，false为不重播，某一具体值（整数）为重播多少次 
volume：取值范围为"0-100"，设置音量，默认为系统本身的音量 
starttime："分：秒"，设置歌曲开始播放的时间，如，starttime="00:10"，从第10开始播放 
endtime： "分：秒"，设置歌曲结束播放的时间 
width：控制面板的宽 
height：控制面板的高 
controls：控制面板的外观 
controls="console/smallconsole/playbutton/pausebutton/stopbutton/volumelever" 
	·console：正常大小的面板 ·smallconsole：较小的面板 ·playbutton：显示播放按钮
	·pausebutton：显示暂停按钮 ·stopbutton：显示停止按钮 ·volumelever：显示音量调节按钮 
hidden：为true时可以隐藏面板
</div>

swf格式(FrontPage自动生成)
<div>
<object classid="clsid:D27CDB6E-AE6D-11CF-96B8-444553540000" id="obj1" 
		codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,40,0" 
		border="0" width="160" height="160">
	<param name="movie" value="videos/123.swf">
	<param name="quality" value="High">
	<embed src="videos/123.swf" pluginspage="http://www.macromedia.com/go/getflashplayer" 
		   type="application/x-shockwave-flash" name="obj1" width="160" height="160">
</object>
</div>

avi格式 
<div>
	<object id="video" width="400" height="200" border="0" classid="clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA">
	<param name="ShowDisplay" value="0">
	<param name="ShowControls" value="1">
	<param name="AutoStart" value="1">
	<param name="AutoRewind" value="0">
	<param name="PlayCount" value="0">
	<param name="Appearance" value="0">
	<param name="BorderStyle" value="0">
	<param name="MovieWindowHeight" value="240">
	<param name="MovieWindowWidth" value="320">
	<param name="FileName" value="/Mbar.avi">
	<embed width="400" height="200" border="0" showdisplay="0" showcontrols="1" autostart="1" 
		autorewind="0" playcount="0" moviewindowheight="240" moviewindowwidth="320" filename="/Mbar.avi" src="Mbar.avi">
	</embed>
	</object>
</div>

mpg格式(IE，FF，TheWorld已测试可用)
<div>
  <object classid="clsid:05589FA1-C356-11CE-BF01-00AA0055595A" id="ActiveMovie1" width="320" height="300">
	<param name="Appearance" value="0">					<!--设置面板的外观（平面或3D） -->
	<param name="AutoStart" value="1">					<!--设置在载入媒体文件之后是否自动开始播放 -->
	<param name="AllowChangeDisplayMode" value="0">		<!--运行时是否允许改变显示的模式；  -->
	<param name="AllowHideDisplay" value="0">			<!--运行时显示/隐藏显示面板；  -->
	<param name="AllowHideControls" value="0">			<!--运行时显示/隐藏控制面板；  -->
	<param name="AutoRewind" value="0">					<!--设置媒体文件播放完毕后是否自动回绕 -->
	<param name="Balance" value="0">					<!--设置指定立体声媒体文件的播放声道 -->
	<param name="CurrentPosition" value="0">			<!--设置剪辑的当前位置 -->
	<param name="DisplayBackColor" value="0">			<!--设置显示面板的背景色(OLE_COLOR 值) -->
	<param name="DisplayForeColor" value="16777215">	<!--设置显示面板的前景色(OLE_COLOR 值) -->
	<param name="DisplayMode" value="0">				<!--设置显示面板是否用秒或帧的形式显示当前位置(MPDisplayModeConstants 值) -->
	<param name="Enabled" value="1">					<!--设置控件是否可用 -->
	<param name="EnableContextMenu" value="1">			<!--当在画面中右键单击时是否允许出现控制菜单 -->
	<param name="EnablePositionControls" value="1">		<!--设置位置控制是否可用 -->
	<param name="EnableSelectionControls" value="1">
	<param name="EnableTracker" value="0">				<!--设置搜索栏控制是否可用 -->
	<param name="Filename" value="http://192.168.68.160:8080/AgrAnime/videos/345.mpg" valuetype="ref">
	<param name="FullScreenMode" value="0">
	<param name="MovieWindowSize" value="0">
	<param name="PlayCount" value="1">					<!--设置播放次数；  -->
	<param name="Rate" value="1">						<!--设置播放时的速率，为1时为正常播放，大于1时为快进（过大时可能无声、无影）； --> 
	<param name="SelectionStart" value="0">				<!--设置播放的开始位置（缺省值为0）；  -->
	<param name="SelectionEnd" value="-1">				<!--设置播放的结束位置（缺省值为-1）；  -->
	<param name="ShowControls" value="1">				<!--显示/隐藏控制面板；  -->
	<param name="ShowDisplay" value="0">				<!--显示/隐藏显示面板；  -->
	<param name="ShowPositionControls" value="1">		<!--在控制面板中显示/隐藏位置按钮；  -->
	<param name="ShowTracker" value="1">				<!--在控制面板中显示/隐藏音轨栏；  -->
	<param name="Volume" value="50">					<!--设置音量 -->
	<embed height="300" width="320" AutoStart="true"
		   type="audio/x-pn-realaudio-plugin" src="http://192.168.68.160:8080/AgrAnime/videos/345.mpg"></embed>
  </object>
</div>

smi格式
<div>
	<OBJECT id=RVOCX classid=clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA width=240 height=180>
	<param name="_ExtentX" value="6350">
	<param name="_ExtentY" value="4763">
	<param name="AUTOSTART" value="-1">
	<param name="SHUFFLE" value="0">
	<param name="PREFETCH" value="0">
	<param name="NOLABELS" value="-1">
	<param name="SRC" value="rm.rm">
	<param name="CONTROLS" value="ImageWindow">
	<param name="CONSOLE" value="console1">
	<param name="LOOP" value="0">
	<param name="NUMLOOP" value="0">
	<param name="CENTER" value="0">
	<param name="MAINTAINASPECT" value="0">
	<param name="BACKGROUNDCOLOR" value="#000000">
	<embed src="real.smi" type="audio/x-pn-realaudio-plugin" 
		console="Console1" controls="ImageWindow" height="180" width="240" autostart="true">
	</OBJECT>
</div>

rm格式(IE，FF，TheWorld已测试可用)
<div>					
  <OBJECT id=video1 height=256 width=320 classid=clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA>
	<PARAM NAME="_ExtentX" VALUE="9313">
	<PARAM NAME="_ExtentY" VALUE="7620">
	<PARAM NAME="AUTOSTART" VALUE="1">
	<PARAM NAME="SHUFFLE" VALUE="0">
	<PARAM NAME="PREFETCH" VALUE="0">
	<PARAM NAME="NOLABELS" VALUE="0">
	<PARAM NAME="SRC" VALUE="http://192.168.68.160:8080/AgrAnime/videos/345.rm">
	<PARAM NAME="CONTROLS" VALUE="ImageWindow">
	<PARAM NAME="CONSOLE" VALUE="Clip1">
	<PARAM NAME="LOOP" VALUE="0">
	<PARAM NAME="NUMLOOP" VALUE="0">
	<PARAM NAME="CENTER" VALUE="0">
	<PARAM NAME="MAINTAINASPECT" VALUE="0">
	<PARAM NAME="BACKGROUNDCOLOR" VALUE="#000000">	
	<embed name="aa" height=256 width=320 type=audio/x-pn-realaudio-plugin loop="no" 
			console="Clip1" reset="false" controls="ImageWindow" 
			src="http://192.168.68.160:8080/AgrAnime/videos/345.rm"></embed>						
  </OBJECT>
  <OBJECT id=video1 height=60 width=320 classid=clsid:CFCDAA03-8BE4-11cf-B84B-0020AFBBCCFA>
	<PARAM NAME="_ExtentX" VALUE="7276">
	<PARAM NAME="_ExtentY" VALUE="1588">
	<PARAM NAME="AUTOSTART" VALUE="1">
	<PARAM NAME="SHUFFLE" VALUE="0">
	<PARAM NAME="PREFETCH" VALUE="0">
	<PARAM NAME="NOLABELS" VALUE="0">
	<PARAM NAME="CONTROLS" VALUE="ControlPanel,StatusBar">
	<PARAM NAME="CONSOLE" VALUE="Clip1">
	<PARAM NAME="LOOP" VALUE="0">
	<PARAM NAME="NUMLOOP" VALUE="0">
	<PARAM NAME="CENTER" VALUE="0">
	<PARAM NAME="MAINTAINASPECT" VALUE="0">
	<PARAM NAME="BACKGROUNDCOLOR" VALUE="#000000">							
	<embed type="audio/x-pn-realaudio-plugin" console="Clip1" CONTROLS="ControlPanel,StatusBar" 
			height="60" width="320" AUTOSTART="true"></embed>
  </OBJECT>
</div>

wmv格式(IE，FF，TheWorld已测试可用)
<div>
  <object id="NSPlay" width=320 height=300 classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" 
	codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,4,5,715" 
	standby="load..." type="application/x-oleobject" align="middle" hspace="5">
	<param name="AutoRewind" value="1">
	<param name="FileName" value="http://192.168.68.160:8080/AgrAnime/videos/345.wmv">
	<param name="ShowControls" value="1">
	<param name="ShowPositionControls" value="1">
	<param name="ShowAudioControls" value="1">
	<param name="ShowTracker" value="0">
	<param name="ShowDisplay" value="0">
	<param name="ShowStatusBar" value="1">
	<param name="ShowGotoBar" value="0">
	<param name="ShowCaptioning" value="0">
	<param name="AutoStart" value=1>
	<param name="Volume" value="50">
	<param name="AnimationAtStart" value="0">
	<param name="TransparentAtStart" value="0">
	<param name="AllowChangeDisplaySize" value="0">
	<param name="AllowScan" value="0">
	<param name="EnableContextMenu" value="1">
	<param name="ClickToPlay" value="1">
	<embed height="300" width="320" loop="no" console="Clip1" reset="false" 
			src="http://192.168.68.160:8080/AgrAnime/videos/345.wmv"></embed>	
  </object>
</div>

wma格式
<div>
<object classid="clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95" id="MediaPlayer1" > 
<param name="Filename" value="/blog/1.Wma">			<!--你文件的位置-->
<param name="PlayCount" value="1">					<!--控制重复次数: “x”为几重复播放几次; x=0，无限循环。--> 
<param name="AutoStart" value="0">					<!--控制播放方式: x=1，打开网页自动播放; x=0，按播放键播放。--> 
<param name="ClickToPlay" value="1">				<!--控制播放开关: x=1，可鼠标点击控制播放或暂停状态; x=0，禁用此功能。-->
<param name="DisplaySize" value="0">				<!--控制播放画面: x=0，原始大小; x=1，一半大小; x=2，2倍大小。--> 
<param name="EnableFullScreen Controls" value="1">	<!--控制切换全屏: x=1，允许切换为全屏; x=0，禁用此功能。--> 
<param name="ShowAudio Controls" value="1">			<!--控制音量: x=1，允许调节音量; x=0，禁止音量调节。-->
<param name="EnableContext Menu" value="1">			<!--控制快捷菜单: x=1，允许使用右键菜单; x=0，禁用右键菜单。--> 
<param name="ShowDisplay" value="1">				<!--控制版权信息: x=1，显示电影及作者信息;x=0，不显示相关信息-->
</object>
</div>

media player(wmv)
<SCRIPT language="JavaScript">   
<!--   
	//Player1.settings.enableErrorDialogs=true;	
	//Player1.controls.play();
 
	function changeSize(i_type){
	
	switch (i_type){
	case 1:{	
		i_rate=Player1.height/Player1.width
		Player1.width=Player1.width*1.5 
		if (Player1.width>=760) {
			Player1.width=760;
			}
	
		Player1.height=Player1.width*i_rate
		//Player1.currentMedia.imageSourceHeight=Player1.currentMedia.imageSourceWidth*i_rate;
		break;		
		}
 
	case 2:{	
		i_rate=Player1.height/Player1.width
		Player1.width=Player1.width/1.5
		if (Player1.width<=320) {
			Player1.width=320;
			}
		
	
		Player1.height=Player1.width*i_rate
		break;		
		}
	case 3:{
		Player1.fullScreen=true;
		break;
		}
	
	}
	
	}
-->   
</SCRIPT>
<div align="right">
<a href="#" onclick="javascript:changeSize(1)" ><font color=black>放大</font></a>&nbsp;
<a href="#" onclick="javascript:changeSize(2)"><font color=black>缩小</font></a>&nbsp;
</div>
<div>
<object id=Player1 height=256 width=320 classid=clsid:6BF52A52-394A-11d3-B153-00C04F79FAA6 VIEWASTEXT>
<param name="URL" value="mms://192.168.68.11/web_data/video/wthd\2009honggehui0.wmv">
<param name="rate" value="1">
<param name="balance" value="0">
<param name="currentPosition" value="0">
<param name="defaultFrame" value="">
<param name="playCount" value="1">
<param name="autoStart" value="-1">
<param name="currentMarker" value="0">
<param name="invokeURLs" value="-1">
<param name="baseURL" value="">
<param name="volume" value="50">
<param name="mute" value="0">
<param name="uiMode" value="full">
<param name="enabled" value="-1">
<param name="enableContextMenu" value="1">
<param name="fullScreen" value="0">
<param name="SAMIStyle" value="">
<param name="SAMILang" value="">
<param name="SAMIFilename" value="">
<param name="captioningID" value="">
</object>
</div>

Windows Media Player
<div>
<object classid=clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95 
		codebase="http://activex.microsoft.com/activex/controls/mplayer/en/nsmp2inf.cab#Version=6,1,5,217"
		id=MediaPlayer type=application/x-oleobject width=210 height=340 
		standby="Loading Microsoft Windows Media Player components..." VIEWASTEXT align=MIDDLE>
<param name=AudioStream value=-1>
<param name=AutoSize value=0>
<param name=AutoStart value=1>
<param name=AnimationAtStart value=0>
<param name=AllowScan value=-1>
<param name=AllowChangeDisplaySize value=0>
<param name=AutoRewind value=0>
<param name=Balance value=0>
<param name=BaseURL value="">
<param name=BufferingTime value=5>
<param name=CaptioningID value="">
<param name=ClickToPlay value=0>
<param name=CursorType value=32512>
<param name=CurrentPosition value=-1>
<param name=CurrentMarker value=0>
<param name=DefaultFrame value=1>
<param name=DisplayBackColor value=0>
<param name=DisplayForeColor value=16777215>
<param name=DisplayMode value=0>
<param name=DisplaySize value=0>
<param name=Enabled value=-1>
<param name=EnableContextMenu value=-1>
<param name=EnablePositionControls value=0>
<param name=EnableFullScreenControls value=0>
<param name=EnableTracker value=1>
<param name=Filename value="http://202.102.*.*/flash/2/fff.swf">
<param name=InvokeURLs value=-1>
<param name=Language value=-1>
<param name=Mute value=0>
<param name=PlayCount value=1>
<param name=PreviewMode value=0>
<param name=Rate value=1>
<param name=SAMILang value="">
<param name=SAMIStyle value="">
<param name=SAMIFileName value="">
<param name=SelectionStart value=0>
<param name=SelectionEnd value=true>
<param name=SendOpenStateChangeEvents value=-1>
<param name=SendWarningEvents value=-1>
<param name=SendErrorEvents value=-1>
<param name=SendKeyboardEvents value=0>
<param name=SendMouseClickEvents value=0>
<param name=SendMouseMoveEvents value=0>
<param name=SendPlayStateChangeEvents value=-1>
<param name=ShowCaptioning value=0>
<param name=ShowControls value=1>
<param name=ShowAudioControls value=1>
<param name=ShowDisplay value=1>
<param name=ShowGotoBar value=1>
<param name=ShowPositionControls value=1>
<param name=ShowStatusBar value=1>
<param name=ShowTracker value=1>
<param name=TransparentAtStart value=0>
<param name=VideoBorderWidth value=0>
<param name=VideoBorderColor value=0>
<param name=VideoBorder3D value=0>
<param name=Volume value=-1070>
<param name=WindowlessVideo value=1>
</object>
</div>
