package com.zcj.xmlrpc.server;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.xmlrpc.webserver.ServletWebServer;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

public class Server {
	private static final int port = 8080;

	public static void main(String[] args) throws Exception {
		Server.base();
	}
	
	public static void base() throws ServletException, IOException {
		ServletWebServer webServer = new ServletWebServer(new XmlRpcServlet(), port);
		webServer.start();
	}
	
}