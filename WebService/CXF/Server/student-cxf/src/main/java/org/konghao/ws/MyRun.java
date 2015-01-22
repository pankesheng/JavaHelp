package org.konghao.ws;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.konghao.ws.interceptor.LicenseUserInInterceptor;

public class MyRun {
	public static void main(String[] args) {
		JaxWsServerFactoryBean fac = new JaxWsServerFactoryBean();
		fac.setAddress("http://localhost:8878/ms");
		fac.setServiceBean(new MyServiceImpl());
		fac.setServiceClass(MyService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getInInterceptors().add(new LicenseUserInInterceptor());
		fac.create();
	}
}
