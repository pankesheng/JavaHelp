package org.konghao.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MyService {
	
	@WebMethod
	public @WebResult(name="hello")String sayHello(@WebParam(name="name")String name);
}
