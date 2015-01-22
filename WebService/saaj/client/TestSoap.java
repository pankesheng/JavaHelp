package com.zcj.webservice.saaj.client;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TestSoap {
	
	private String ns = "http://service.saaj.webservice.zcj.com/";
	private String wsdlUrl = "http://localhost:8889/ms?wsdl";
	private String serviceName = "MyServiceImplService";
	private String portName = "MyServiceImplPort";
	
	/**
	 * 提交字符串
	 */
	@Test
	public void test01() {
		try {
			//1、创建服务(Service)
			Service service = Service.create(new URL(wsdlUrl),new QName(ns,serviceName));
			
			//2、创建Dispatch
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,portName),
						SOAPMessage.class, Service.Mode.MESSAGE);
			
			//3、创建SOAPMessage
			SOAPMessage msg = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			
			//添加请求的头信息
			SOAPHeader header = envelope.getHeader();
			if(header==null) header = envelope.addHeader();
			QName hname = new QName(ns,"authInfo","xxx");
			header.addHeaderElement(hname).setValue("xxxxxxxx");
			
//			//请求add方法
//			QName ename = new QName(ns,"add","zcj");
//			SOAPBodyElement ele = body.addBodyElement(ename);
//			ele.addChildElement("a").setValue("22");
//			ele.addChildElement("b").setValue("33");
//			msg.writeTo(System.out);
//			System.out.println();
			
			//请求login方法
			QName ename = new QName(ns,"login","xxx");
			SOAPBodyElement ele = body.addBodyElement(ename);
			ele.addChildElement("username").setValue("admin");
			ele.addChildElement("password").setValue("111111");
			msg.writeTo(System.out);
			System.out.println();
			
			//返回
			SOAPMessage response = dispatch.invoke(msg);
			response.writeTo(System.out);
			System.out.println();
			
//			// 输出add方法返回的字符串信息
//			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
//			String str = doc.getElementsByTagName("addResult").item(0).getTextContent();
//			System.out.println(str);

//			// 输出list方法返回的对象信息
//			NodeList nl = doc.getElementsByTagName("user");// user:MyService.java里的方法返回名
//			for (int i = 0; i < nl.getLength(); i++) {
//				User u = (User) this.xml2object(nl.item(i), User.class);
//				System.out.println(u.getNickname());
//			}
			
		} catch(SOAPFaultException e){ // 如果服务端提供的方法可能返回异常，则添加此段代码
			System.out.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 提交对象
	 */
	@Test
	public void test02() throws Exception {
		//1、创建服务(Service)
		Service service = Service.create(new URL(wsdlUrl),new QName(ns,serviceName));
		
		//2、创建Dispatch(通过源数据的方式传递)
		Dispatch<Source> dispatch = service.createDispatch(new QName(ns,portName),
					Source.class, Service.Mode.PAYLOAD);
		
		//3、根据用户对象创建相应的xml
		User user = new User(3,"zs","张三","11111");
		String payload = "<xxx:addUser xmlns:xxx=\""+ns+"\">"+this.object2xml(user)+"</xxx:addUser>";
		System.out.println(payload);
		
		//5、通过dispatch传递payload
		Source response = (Source)dispatch.invoke(new StreamSource(new StringReader(payload)));
		
		//6、将Source转化为DOM进行操作，使用Transform对象转换
		DOMResult result = this.source2domResult(response);
		
		//7、处理相应信息(通过xpath处理)
		NodeList nl = (NodeList) XPathFactory.newInstance().newXPath().evaluate("//user", result.getNode(), XPathConstants.NODESET);
		User ru = (User) this.xml2object(nl.item(0), User.class);
		System.out.println(ru.getNickname());
	}
	
	/** Object转XML */
	private String object2xml(Object obj) throws JAXBException {
		Marshaller mar = JAXBContext.newInstance(obj.getClass()).createMarshaller();
		mar.setProperty(Marshaller.JAXB_FRAGMENT, true);
		StringWriter writer = new StringWriter();
		mar.marshal(obj, writer);
		return writer.toString();
	}
	
	/** Source转DOMResult */
	private DOMResult source2domResult(Source source) throws Exception {
		Transformer tran = TransformerFactory.newInstance().newTransformer();
		DOMResult result = new DOMResult();
		tran.transform(source, result);
		return result;
	}
	
	/** XML转Object */
	private Object xml2object(Node node, Class<?> classes) throws JAXBException {
		return JAXBContext.newInstance(classes).createUnmarshaller().unmarshal(node);
	}
	
	/** XML转Object */
	@SuppressWarnings("unused")
	private Object xml2object(String xml, Class<?> classes) throws JAXBException {
		return JAXBContext.newInstance(classes).createUnmarshaller().unmarshal(new StringReader(xml));
	}
	
}
