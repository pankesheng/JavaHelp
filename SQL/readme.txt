
Oracle
	停止PL/SQL的运行：Shift+ESC
	Window Server 2008 无法安装 Oracle9i，只能在 Server 2008 上装 Server2003虚拟机再装Oracle9i
	
Mysql字段类型

	tinyint：-128~127 或 0~255
	smallint：-32768~32767 或 0~65535
	mediumint：-8388608~8388607 或 0~16777215
	int：-2147383648~2147483647 或 0~4294967295
	bigint：-9223372036854775808~9223372036854775807 或 0~18446744073709551615
	text：65535个字符
	mediumtext：16777215个字符
	longtext：4294967295个字符
	
SqlServer2008数据库备份到另一台电脑

	SqlServer 2008 必须为企业版
	1、准备一台存储备份文件的服务器
	2、在服务器的硬盘上新建一个文件夹
	3、右键新建的文件夹，选择“属性”--“共享”，点击“高级共享”，勾选“共享此文件夹”，点击“权限”，勾选Everyone的权限“允许”“完全控制”，点击确定保存。在“共享”的设置界面可以看到此文件夹的网络路径，记下此路径，在第五步有用。
	4、在数据库所在的电脑上开启“SQL Server代理”服务，开启步骤如下：点击“开始”--“所有程序”--“Microsoft SQL Server 2008 R2”--“配置工具”--“Sql Server 配置管理器”--“Sql Server 服务”，启动右侧的“SQL Server 代理”服务。
	5、通过以下设置数据库的备份http://jingyan.baidu.com/article/ea24bc39baa5dcda63b33156.html
	6、如果可以备份到本地，但是不能备份到另一台电脑，则使用如下步骤跨电脑拷贝文件夹
		6-1、新建一个bat文件，内容如下：xcopy F:\111\*.* \\172.16.31.131\sqldata_bak\
		6-2、配置一个计划任务定时执行bat，步骤如下：http://www.myhack58.com/Article/48/65/2014/48515.htm