
package com.xt.touch.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findSendBoxByPageResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findSendBoxByPageResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sendbox" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findSendBoxByPageResponse", propOrder = {
    "sendbox"
})
public class FindSendBoxByPageResponse {

    protected String sendbox;

    /**
     * Gets the value of the sendbox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendbox() {
        return sendbox;
    }

    /**
     * Sets the value of the sendbox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendbox(String value) {
        this.sendbox = value;
    }

}
