
添加：C:\szxt\ohszxt.jks

覆盖：C:\Program Files\Java\jdk1.6.0_39\jre\lib\security\cacerts和C:\Program Files\Java\jre6\lib\security\cacerts

修改：Tomcat6.0.36\conf\server.xml
	<Connector port="443" protocol="org.apache.coyote.http11.Http11Protocol" SSLEnabled="true"
               maxThreads="150" scheme="https" secure="true"
               clientAuth="true" sslProtocol="TLS"
               keystoreFile="C:\szxt\ohszxt.jks" keystorePass="changeit" keystoreType="JKS"
               truststoreFile="C:\szxt\ohszxt.jks" truststorePass="changeit" truststoreType="JKS" />
               
测试：IE6 https://192.168.1.120/ 能打开Tomcat首页。