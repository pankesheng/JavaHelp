<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.security.cert.X509Certificate"%>
<%@ page import="java.security.cert.CertificateFactory"%>
<%@ page import="java.io.ByteArrayInputStream"%>
<%@ page import="java.util.Vector"%>
<%-- <%@ page import="com.jit.attr.JitAcComp"%>
<%@ page import="com.jit.attr.GenGACode"%>
<%@ page import="com.jit.attr.GAACInfo"%> --%>
<html>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<%
//本例中IP地址等参数为举例用。
//    String myinfo = null;
    try{
       out.println("=============证书登陆程序==============<br>");
       X509Certificate[] certs=(X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");//使用JAVA的证书API通过Web服务器获取浏览器端证书信息，只有取到证书信息，PKI中间件才能开始工作
       if(certs==null){
      	 out.println("错误！证书不存在！请插入数字证书，关闭浏览器，再重新打开浏览器，然后点击访问！");//单向SSL，不插证书时会执行到这一步
      	 return;
       }
       X509Certificate gaX509Cert=null;
       gaX509Cert=certs[0];            
       ByteArrayInputStream bais = new ByteArrayInputStream(gaX509Cert.getEncoded());
       CertificateFactory cf = CertificateFactory.getInstance("X.509");
       gaX509Cert = (X509Certificate)cf.generateCertificate(bais);
       bais.close();
       
       //获取证书信息
       String dn = gaX509Cert.getSubjectDN().toString();
       out.println("证书DN项包括姓名、身份证号码等信息：" + dn + "<br>");
       String userName = dn.substring(3,dn.indexOf(" "));
       String identifyCode = dn.substring(dn.indexOf(" "),dn.indexOf(","));
       identifyCode = identifyCode.trim();//去掉前面的空格
       out.println("姓名："+ userName + "<br>");
       out.println("身份证号码："+ identifyCode + "<br>");
       
     
//       com.jit.attr.JitAcComp jitaccomp = new com.jit.attr.JitAcComp();//创建中间件对象
//       jitaccomp.setBaseDN("c=cn");
//       jitaccomp.setPrivilegeSetType(2);//设置读取权限类型
//       jitaccomp.setPKICertificate(gaX509Cert);//设置用户公钥证书
//       //客户端IP获取。
//       String ip = request.getRemoteAddr();//获取用户的IP地址
//       jitaccomp.setClientIP(ip);//设定用户的IP地址
//       //设置审计参数。
//       jitaccomp.setAuditParameter("10.118.1.87","3000"); //10.118.1.87为审计服务器IP地址 、3000为使用UDP协议发送审计信息的端口
//      
//       jitaccomp.setParameter("10.1.1.104","389");//PKI目录服务器IP及端口，如果是2台就是"IP1,IP2","389,389"的格式，请注意语句中“,”均为英文逗号。
//       jitaccomp.isCheckCRL(true);      //CRL即注销证书列表验证 
//       jitaccomp.isCheckCertPath(true); //证书链即根证书验证
//       
//       myinfo = jitaccomp.getPrivilegeList("x.x.x.x","389","23191","330000");//获取用户的权限列表码。PMI目录服务器IP及端口，如果是2台就是"IP1,IP2","389,389"的格式，5位应用码、6位区域码。
//      out.println("权限列表：" + myinfo + "<br>");
//       out.println("当你取到权限列表为null时表示失败，如果是zjsjzxt--此时应用码是23191区域码是330000，其他应用码有其他的值，说明调试成功，即SSL通道配置及安装中间件调用中间件均已成功！！！请继续进行程序的编写完善工作，注释或取消不需要的语句或显示，也可优化语句，将获取的证书信息或权限码运用到程序中，也可将相关语句放在主程序中！！！");
//       //response.sendRedirect("sy.htm");//跳转语句，假如使用注销证书，会出现证书已注销或CRL验证未通过等提示信息，程序会立即停止执行，不会执行此跳转。
	   }
     catch(Exception e){
			 out.println("错误！"+e.getMessage());//－－此处即为获取并显示统一提示信息的方法。
			 e.printStackTrace();
     }
%>
</body>
</html>
