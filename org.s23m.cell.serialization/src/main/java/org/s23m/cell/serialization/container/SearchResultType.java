//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.15 at 06:58:13 PM CEST 
//


package org.s23m.cell.serialization.container;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for searchResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="searchResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="instanceIdentity" type="{http://schemas.S23M.org/container/2010}instanceIdentityType"/>
 *         &lt;element name="metaInstanceIdentity" type="{http://schemas.S23M.org/container/2010}instanceIdentityType"/>
 *         &lt;element name="containerIdentity" type="{http://schemas.S23M.org/container/2010}instanceIdentityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchResultType", propOrder = {
    "instanceIdentity",
    "metaInstanceIdentity",
    "containerIdentity"
})
public class SearchResultType {

    @XmlElement(namespace = "http://schemas.S23M.org/container/2010", required = true)
    protected InstanceIdentityType instanceIdentity;
    @XmlElement(namespace = "http://schemas.S23M.org/container/2010", required = true)
    protected InstanceIdentityType metaInstanceIdentity;
    @XmlElement(namespace = "http://schemas.S23M.org/container/2010", required = true)
    protected InstanceIdentityType containerIdentity;

    /**
     * Gets the value of the instanceIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentityType }
     *     
     */
    public InstanceIdentityType getInstanceIdentity() {
        return instanceIdentity;
    }

    /**
     * Sets the value of the instanceIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentityType }
     *     
     */
    public void setInstanceIdentity(InstanceIdentityType value) {
        this.instanceIdentity = value;
    }

    /**
     * Gets the value of the metaInstanceIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentityType }
     *     
     */
    public InstanceIdentityType getMetaInstanceIdentity() {
        return metaInstanceIdentity;
    }

    /**
     * Sets the value of the metaInstanceIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentityType }
     *     
     */
    public void setMetaInstanceIdentity(InstanceIdentityType value) {
        this.metaInstanceIdentity = value;
    }

    /**
     * Gets the value of the containerIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link InstanceIdentityType }
     *     
     */
    public InstanceIdentityType getContainerIdentity() {
        return containerIdentity;
    }

    /**
     * Sets the value of the containerIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link InstanceIdentityType }
     *     
     */
    public void setContainerIdentity(InstanceIdentityType value) {
        this.containerIdentity = value;
    }

}
