//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 09:37:26 PM CEST
//


package com.megatravel.soap.types;

import com.megatravel.models.types.Location;
import com.megatravel.soap.admin.Adapter1;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.Date;


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
 *         &lt;element name="id" type="{http://www.megatravel.com/types}id"/>
 *         &lt;element name="country" type="{http://www.megatravel.com/types}name"/>
 *         &lt;element name="city" type="{http://www.megatravel.com/types}name"/>
 *         &lt;element name="street" type="{http://www.megatravel.com/types}name"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="postalCode" type="{http://www.megatravel.com/types}postalCode"/>
 *         &lt;element name="latitude" type="{http://www.megatravel.com/types}latitude" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://www.megatravel.com/types}longitude" minOccurs="0"/>
 *         &lt;element name="lastChangedDate" type="{http://www.megatravel.com/types}Date" minOccurs="0"/>
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
        "country",
        "city",
        "street",
        "number",
        "postalCode",
        "latitude",
        "longitude",
        "lastChangedDate"
})
@XmlRootElement(name = "locationDTO")
public class LocationDTO {

    protected long id;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String street;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger number;
    protected int postalCode;
    protected Double latitude;
    protected Double longitude;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    protected Date lastChangedDate;

    public LocationDTO() {
    }

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.country = location.getCountry();
        this.city = location.getCity();
        this.street = location.getStreet();
        this.number = location.getNumber();
        this.postalCode = location.getPostalCode();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.lastChangedDate = location.getLastChangedDate();
    }


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
     * Gets the value of the country property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the street property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the number property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Gets the value of the postalCode property.
     *
     */
    public int getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     *
     */
    public void setPostalCode(int value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the latitude property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setLatitude(Double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     *
     * @return
     *     possible object is
     *     {@link Double }
     *
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     *
     * @param value
     *     allowed object is
     *     {@link Double }
     *
     */
    public void setLongitude(Double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the lastChangedDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    /**
     * Sets the value of the lastChangedDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastChangedDate(Date value) {
        this.lastChangedDate = value;
    }

}