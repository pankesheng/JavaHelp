package com.xt.lamster.action;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.xt.touch.ws.MyService;

@Scope("prototype")
@Component("userAction")
public class UserAction extends BasicAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MyService myService;

	public String login() {
		
		try {
			System.out.println(myService.add(5, 9));
			System.out.println(myService.findSendBoxByPage("133cbe8ab5364adab97840e69c00c57d", 0, 5));
		} catch (Exception e) {
			jsonString = initErrorResponseString(e.getMessage());
			return JSON_RESULT;
		}

		jsonString = initSuccessResponseString("登陆成功！");
		return JSON_RESULT;
	}
	
}
