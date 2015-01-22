package com.xt.lamster.ws;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * 客户端WebService的一个Handler,用于在head里放数据
 * @author ZCJ
 * @data 2013-7-31
 */
public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext ctx) {
		try {
			Boolean out = (Boolean)ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if(out) {
				SOAPEnvelope enve = ctx.getMessage().getSOAPPart().getEnvelope();
				SOAPBody body = enve.getBody();
				String name = body.getFirstChild().getLocalName();
				if("findSendBoxByPage".equals(name) || "add".equals(name)) {// 如果是调用findSendBoxByPage或add方法
					SOAPHeader header = enve.getHeader();
					if(header==null) header = enve.addHeader();
					QName qn = new QName("http://ws.touch.xt.com/","licenseInfo","ns");
					header.addHeaderElement(qn).setValue("aaa");
				}
			}
			return true;
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public void close(MessageContext ctx) {
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext ctx) {
		return false;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
