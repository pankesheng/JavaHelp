
package com.xt.touch.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.xt.touch.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindSendBoxByPage_QNAME = new QName("http://ws.touch.xt.com/", "findSendBoxByPage");
    private final static QName _Add_QNAME = new QName("http://ws.touch.xt.com/", "add");
    private final static QName _FindSendBoxByPageResponse_QNAME = new QName("http://ws.touch.xt.com/", "findSendBoxByPageResponse");
    private final static QName _AddResponse_QNAME = new QName("http://ws.touch.xt.com/", "addResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.xt.touch.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindSendBoxByPageResponse }
     * 
     */
    public FindSendBoxByPageResponse createFindSendBoxByPageResponse() {
        return new FindSendBoxByPageResponse();
    }

    /**
     * Create an instance of {@link FindSendBoxByPage }
     * 
     */
    public FindSendBoxByPage createFindSendBoxByPage() {
        return new FindSendBoxByPage();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindSendBoxByPage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.touch.xt.com/", name = "findSendBoxByPage")
    public JAXBElement<FindSendBoxByPage> createFindSendBoxByPage(FindSendBoxByPage value) {
        return new JAXBElement<FindSendBoxByPage>(_FindSendBoxByPage_QNAME, FindSendBoxByPage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.touch.xt.com/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindSendBoxByPageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.touch.xt.com/", name = "findSendBoxByPageResponse")
    public JAXBElement<FindSendBoxByPageResponse> createFindSendBoxByPageResponse(FindSendBoxByPageResponse value) {
        return new JAXBElement<FindSendBoxByPageResponse>(_FindSendBoxByPageResponse_QNAME, FindSendBoxByPageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.touch.xt.com/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

}
