package com.xt.touch.ws;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface MyService {

	@WebResult(name="sendbox")
	String findSendBoxByPage(@WebParam(name="userId") String userId, @WebParam(name="offset") Integer offset, @WebParam(name="pagesize") Integer pagesize) throws MyWebServiceException;
	
	@WebResult(name="addResult")
	public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);
}