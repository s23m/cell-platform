//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.23 at 04:41:58 PM CET 
//


package org.s23m.cell.serialization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for superSetReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="superSetReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="semanticIdentity" type="{http://schemas.gmodel.org/serialization/2010}semanticIdType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://schemas.gmodel.org/serialization/2010}uuid" />
 *       &lt;attribute name="subSetInstance" use="required" type="{http://schemas.gmodel.org/serialization/2010}referenceId" />
 *       &lt;attribute name="superSetInstance" use="required" type="{http://schemas.gmodel.org/serialization/2010}referenceId" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "superSetReferenceType", propOrder = {
    "semanticIdentity"
})
public class SuperSetReferenceType {

    @XmlElement(namespace = "http://schemas.gmodel.org/serialization/2010", required = true)
    protected SemanticIdType semanticIdentity;
    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected String subSetInstance;
    @XmlAttribute(required = true)
    protected String superSetInstance;

    /**
     * Gets the value of the semanticIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link SemanticIdType }
     *     
     */
    public SemanticIdType getSemanticIdentity() {
        return semanticIdentity;
    }

    /**
     * Sets the value of the semanticIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link SemanticIdType }
     *     
     */
    public void setSemanticIdentity(SemanticIdType value) {
        this.semanticIdentity = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the subSetInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubSetInstance() {
        return subSetInstance;
    }

    /**
     * Sets the value of the subSetInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubSetInstance(String value) {
        this.subSetInstance = value;
    }

    /**
     * Gets the value of the superSetInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuperSetInstance() {
        return superSetInstance;
    }

    /**
     * Sets the value of the superSetInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuperSetInstance(String value) {
        this.superSetInstance = value;
    }

}