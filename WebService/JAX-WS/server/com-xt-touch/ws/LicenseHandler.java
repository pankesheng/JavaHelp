package com.xt.touch.ws;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * 处理客户端传过来的头部中的信息
 * 
 * @author ZCJ
 * @data 2013-8-1
 */
public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			Boolean out = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (!out) {// 调用服务器方法之前运行
				SOAPMessage message = context.getMessage();
				SOAPEnvelope enve = message.getSOAPPart().getEnvelope();
				SOAPHeader header = enve.getHeader();
				SOAPBody body = enve.getBody();

				String partname = body.getChildNodes().item(0).getLocalName();
				if ("findSendBoxByPage".equals(partname) || "add".equals(partname)) {
					if (header == null || !header.extractAllHeaderElements().hasNext()) {
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不能为空!");
						throw new SOAPFaultException(fault);
					}
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	@Override
	public void close(MessageContext context) {

	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
