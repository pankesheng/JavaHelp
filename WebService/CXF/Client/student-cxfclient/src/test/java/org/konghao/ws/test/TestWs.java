package org.konghao.ws.test;

import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.konghao.ws.MyService;
import org.konghao.ws.interceptor.LicenseUserOutInterceptor;

public class TestWs {

	@Test
	public void test03() {
		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setServiceClass(MyService.class);
		fac.setAddress("http://localhost:8878/ms");
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		fac.getOutInterceptors().add(new LicenseUserOutInterceptor());
		MyService ms = (MyService)fac.create();
		System.out.println(ms.sayHello("张三"));
	}
	
}