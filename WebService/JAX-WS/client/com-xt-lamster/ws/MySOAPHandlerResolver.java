package com.xt.lamster.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import org.springframework.stereotype.Service;

/**
 * 客户端WebService的Handler分解器，在applicationContext.xml里注入
 * @author ZCJ
 * @data 2013-7-31
 */
@SuppressWarnings("rawtypes")
@Service("mySOAPHandlerResolver")
public class MySOAPHandlerResolver implements HandlerResolver {
	private List<Handler> handlers;
	
	public MySOAPHandlerResolver() {
		handlers = new ArrayList<Handler>();
		handlers.add(new LicenseHandler());
	}

	public List<Handler> getHandlerChain(PortInfo arg0) {
		return handlers;
	}

}
