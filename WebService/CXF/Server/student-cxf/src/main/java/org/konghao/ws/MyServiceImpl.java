package org.konghao.ws;

import javax.jws.WebService;

@WebService(endpointInterface="org.konghao.ws.MyService")
public class MyServiceImpl implements MyService {

	public String sayHello(String name) {
		System.out.println(name);
		return "hello:"+name;
	}

}
