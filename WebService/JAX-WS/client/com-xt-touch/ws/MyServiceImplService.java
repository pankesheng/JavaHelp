
package com.xt.touch.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "MyServiceImplService", targetNamespace = "http://ws.touch.xt.com/", wsdlLocation = "http://localhost:8888/ns?wsdl")
public class MyServiceImplService
    extends Service
{

    private final static URL MYSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.xt.touch.ws.MyServiceImplService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.xt.touch.ws.MyServiceImplService.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8888/ns?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8888/ns?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        MYSERVICEIMPLSERVICE_WSDL_LOCATION = url;
    }

    public MyServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyServiceImplService() {
        super(MYSERVICEIMPLSERVICE_WSDL_LOCATION, new QName("http://ws.touch.xt.com/", "MyServiceImplService"));
    }

    /**
     * 
     * @return
     *     returns MyService
     */
    @WebEndpoint(name = "MyServiceImplPort")
    public MyService getMyServiceImplPort() {
        return super.getPort(new QName("http://ws.touch.xt.com/", "MyServiceImplPort"), MyService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MyService
     */
    @WebEndpoint(name = "MyServiceImplPort")
    public MyService getMyServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.touch.xt.com/", "MyServiceImplPort"), MyService.class, features);
    }

}
