
package com.demo.ws.endpoints;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calculateScoring complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="calculateScoring">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="extSysId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="appSpecId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mapScheId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="appDoc" type="{http://endpoints.ws.demo.com/}applicationDocument" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateScoring", propOrder = {
    "extSysId",
    "appSpecId",
    "mapScheId",
    "appDoc"
})
public class CalculateScoring {

    protected Long extSysId;
    protected Long appSpecId;
    protected Long mapScheId;
    protected ApplicationDocument appDoc;

    /**
     * Gets the value of the extSysId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getExtSysId() {
        return extSysId;
    }

    /**
     * Sets the value of the extSysId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setExtSysId(Long value) {
        this.extSysId = value;
    }

    /**
     * Gets the value of the appSpecId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAppSpecId() {
        return appSpecId;
    }

    /**
     * Sets the value of the appSpecId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAppSpecId(Long value) {
        this.appSpecId = value;
    }

    /**
     * Gets the value of the mapScheId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMapScheId() {
        return mapScheId;
    }

    /**
     * Sets the value of the mapScheId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMapScheId(Long value) {
        this.mapScheId = value;
    }

    /**
     * Gets the value of the appDoc property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationDocument }
     *     
     */
    public ApplicationDocument getAppDoc() {
        return appDoc;
    }

    /**
     * Sets the value of the appDoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationDocument }
     *     
     */
    public void setAppDoc(ApplicationDocument value) {
        this.appDoc = value;
    }

}
