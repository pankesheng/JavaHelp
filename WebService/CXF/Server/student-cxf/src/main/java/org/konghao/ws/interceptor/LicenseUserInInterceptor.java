package org.konghao.ws.interceptor;

import java.util.List;


import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LicenseUserInInterceptor extends AbstractSoapInterceptor {
	
	public LicenseUserInInterceptor() {
		super(Phase.UNMARSHAL);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		List<Header> headers = message.getHeaders();
		Object o = null;
		for(Header h:headers) {
			o = h.getObject();//Object其实是一个Node(Element)对象
			if(o instanceof Node) {
				Element e = (Element)o;
				Node un = e.getElementsByTagName("username").item(0);
				Node pn = e.getElementsByTagName("password").item(0);
				System.out.println(un.getTextContent()+","+pn.getTextContent());
			}
		}
	}

}
