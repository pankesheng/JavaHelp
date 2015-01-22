package org.konghao.ws.interceptor;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;
import org.konghao.ws.User;

public class LicenseUserOutInterceptor extends AbstractSoapInterceptor {

	public LicenseUserOutInterceptor(String phase) {
		super(phase);
	}

	public LicenseUserOutInterceptor() {
		super(Phase.WRITE);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		try {
			List<Header> headers = message.getHeaders();
			QName qn = new QName("http://ws.konghao.org/", "licenseInfo", "ns");
			DataBinding binding = new JAXBDataBinding(User.class);
			User u = new User();
			u.setUsername("张三");
			u.setPassword("123");
			Header h = new Header(qn, u, binding);
			headers.add(h);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
