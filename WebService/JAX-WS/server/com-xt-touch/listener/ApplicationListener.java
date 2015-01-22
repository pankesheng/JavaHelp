package com.xt.touch.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

import com.xt.touch.ws.MyServiceImpl;

public class ApplicationListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		Endpoint.publish("http://localhost:8888/ns", new MyServiceImpl());
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}