//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.04 at 07:10:31 PM CEST 
//


package com.megatravel.interfaces;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://interfaces.megatravel.com/}id"/>
 *         &lt;element name="nameOfUnitType" type="{http://interfaces.megatravel.com/}name"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "nameOfUnitType"
})
@XmlRootElement(name = "unitTypeDTO")
public class UnitTypeDTO {

    @XmlElement(name = "Id")
    protected long id;
    @XmlElement(required = true)
    protected String nameOfUnitType;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the nameOfUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfUnitType() {
        return nameOfUnitType;
    }

    /**
     * Sets the value of the nameOfUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfUnitType(String value) {
        this.nameOfUnitType = value;
    }

}
