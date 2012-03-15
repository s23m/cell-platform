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
 * <p>Java class for visibilityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="visibilityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="semanticIdentity" type="{http://schemas.S23M.org/serialization/2010}semanticIdType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://schemas.S23M.org/serialization/2010}uuid" />
 *       &lt;attribute name="sourceInstance" use="required" type="{http://schemas.S23M.org/serialization/2010}referenceId" />
 *       &lt;attribute name="targetInstance" use="required" type="{http://schemas.S23M.org/serialization/2010}referenceId" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visibilityType", propOrder = {
    "semanticIdentity"
})
public class VisibilityType {

    @XmlElement(namespace = "http://schemas.S23M.org/serialization/2010", required = true)
    protected SemanticIdType semanticIdentity;
    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected String sourceInstance;
    @XmlAttribute(required = true)
    protected String targetInstance;

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
     * Gets the value of the sourceInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceInstance() {
        return sourceInstance;
    }

    /**
     * Sets the value of the sourceInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceInstance(String value) {
        this.sourceInstance = value;
    }

    /**
     * Gets the value of the targetInstance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetInstance() {
        return targetInstance;
    }

    /**
     * Sets the value of the targetInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetInstance(String value) {
        this.targetInstance = value;
    }

}
