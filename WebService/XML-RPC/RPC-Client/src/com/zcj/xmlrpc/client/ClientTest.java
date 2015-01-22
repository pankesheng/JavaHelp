package com.zcj.xmlrpc.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.TimingOutCallback;
import org.apache.xmlrpc.client.TimingOutCallback.TimeoutException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class ClientTest {
	
	private static final String WS_URL = "http://localhost:8080/xmlrpc";
	private static final String WS_METHODNAME_CALCULATOR_ADD = "MyService.add";// Calculator.add
	
	public static void main(String[] args) throws Throwable {
		ClientTest.xmlRpcClientTest();
//		ClientTest.timeout();
	}

	/** 基本操作 */
	public static void xmlRpcClientTest() throws MalformedURLException, XmlRpcException {
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(WS_URL));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		Integer result = (Integer) client.execute(WS_METHODNAME_CALCULATOR_ADD, new Object[] { new Integer(33), new Integer(9) });
		System.out.println(result);
	}
	
	/** 设置超时时间 */
	public static void timeout() throws Throwable {
		TimingOutCallback callback = new TimingOutCallback(10 * 1000);
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(WS_URL));
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
	    client.executeAsync(WS_METHODNAME_CALCULATOR_ADD, new Object[] { new Integer(30), new Integer(9) }, callback);
	    try {
	        Object result = callback.waitForResponse();
	        System.out.println(result);
	    } catch (TimeoutException e) {
	        System.out.println("No response from server.");
	    } catch (Exception e) {
	        System.out.println("Server returned an error message.");
	    }
	}

}
