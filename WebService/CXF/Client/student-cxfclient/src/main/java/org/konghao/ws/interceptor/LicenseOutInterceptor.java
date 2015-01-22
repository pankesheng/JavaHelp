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

public class LicenseOutInterceptor extends AbstractSoapInterceptor {

	public LicenseOutInterceptor(String phase) {
		super(phase);
	}

	public LicenseOutInterceptor() {
		super(Phase.WRITE);
	}

	public void handleMessage(SoapMessage message) throws Fault {
		try {
			List<Header> headers = message.getHeaders();
			QName qn = new QName("http://ws.konghao.org/", "licenseInfo", "ns");
			DataBinding binding = new JAXBDataBinding(String.class);
			Header h = new Header(qn, "admin", binding);
			headers.add(h);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
