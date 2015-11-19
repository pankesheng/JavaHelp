
无需安装JDK，双击bat文件直接运行程序
	1、安装Eclipse插件fatjar
	2、打包项目为my.jar
	3、桌面新建目录myproject
	4、拷贝my.jar到myproject
	5、拷贝jre7到myproject
	6、新建my.bat，内容为：start jre7\bin\javaw -jar my.jar
	7、双击my.bat运行程序