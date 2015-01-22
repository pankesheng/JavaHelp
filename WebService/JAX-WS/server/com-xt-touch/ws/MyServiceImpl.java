package com.xt.touch.ws;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.xt.touch.service.MessageService;

// 生成客户端代码(D:\>wsimport -d d:/test/ -keep -verbose http://localhost:8888/ns?wsdl)

@WebService(endpointInterface = "com.xt.touch.ws.MyService")
@HandlerChain(file = "com/xt/touch/ws/handler-chain-service.xml")
public class MyServiceImpl extends SpringBeanAutowiringSupport implements MyService {

	@Autowired
	private MessageService messageService;
	
	@Override
	public String findSendBoxByPage(String userId, Integer offset, Integer pagesize) throws MyWebServiceException {
		if (userId == null || offset == null || pagesize == null) {
			throw new MyWebServiceException("参数错误");
		}
		return messageService.findSendBoxByPage(userId, offset, pagesize);
	}

	@Override
	public int add(int a, int b) {
		return a + b;
	}

}
