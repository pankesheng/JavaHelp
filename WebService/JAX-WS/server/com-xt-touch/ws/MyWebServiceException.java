package com.xt.touch.ws;
public class MyWebServiceException extends Exception {

	private static final long serialVersionUID = -2659274552421979410L;

	public MyWebServiceException() {
		super();
	}

	public MyWebServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyWebServiceException(String message) {
		super(message);
	}

	public MyWebServiceException(Throwable cause) {
		super(cause);
	}

}