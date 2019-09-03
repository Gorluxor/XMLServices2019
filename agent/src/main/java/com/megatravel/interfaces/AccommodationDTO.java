//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.09.03 at 10:54:55 PM CEST 
//


package com.megatravel.interfaces;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="id" type="{http://interfaces.megatravel.com/}id"/>
 *         &lt;element name="name" type="{http://interfaces.megatravel.com/}name"/>
 *         &lt;element name="description" type="{http://interfaces.megatravel.com/}description"/>
 *         &lt;element name="cancelationDays" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element ref="{http://interfaces.megatravel.com/}locationDTO"/>
 *         &lt;element ref="{http://interfaces.megatravel.com/}accommodationTypeDTO"/>
 *         &lt;element ref="{http://interfaces.megatravel.com/}userDTO" minOccurs="0"/>
 *         &lt;element ref="{http://interfaces.megatravel.com/}accommodationUnitDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://interfaces.megatravel.com/}extraServiceDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://interfaces.megatravel.com/}imageDTO" maxOccurs="unbounded" minOccurs="0"/>
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
    "name",
    "description",
    "cancelationDays",
    "locationDTO",
    "accommodationTypeDTO",
    "userDTO",
    "accommodationUnitDTO",
    "extraServiceDTO",
    "imageDTO"
})
@XmlRootElement(name = "accommodationDTO")
public class AccommodationDTO {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger cancelationDays;
    @XmlElement(required = true)
    protected LocationDTO locationDTO;
    @XmlElement(required = true)
    protected AccommodationTypeDTO accommodationTypeDTO;
    protected UserDTO userDTO;
    protected List<AccommodationUnitDTO> accommodationUnitDTO;
    protected List<ExtraServiceDTO> extraServiceDTO;
    protected List<ImageDTO> imageDTO;

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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the cancelationDays property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCancelationDays() {
        return cancelationDays;
    }

    /**
     * Sets the value of the cancelationDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCancelationDays(BigInteger value) {
        this.cancelationDays = value;
    }

    /**
     * Gets the value of the locationDTO property.
     * 
     * @return
     *     possible object is
     *     {@link LocationDTO }
     *     
     */
    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    /**
     * Sets the value of the locationDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationDTO }
     *     
     */
    public void setLocationDTO(LocationDTO value) {
        this.locationDTO = value;
    }

    /**
     * Gets the value of the accommodationTypeDTO property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationTypeDTO }
     *     
     */
    public AccommodationTypeDTO getAccommodationTypeDTO() {
        return accommodationTypeDTO;
    }

    /**
     * Sets the value of the accommodationTypeDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationTypeDTO }
     *     
     */
    public void setAccommodationTypeDTO(AccommodationTypeDTO value) {
        this.accommodationTypeDTO = value;
    }

    /**
     * Gets the value of the userDTO property.
     * 
     * @return
     *     possible object is
     *     {@link UserDTO }
     *     
     */
    public UserDTO getUserDTO() {
        return userDTO;
    }

    /**
     * Sets the value of the userDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserDTO }
     *     
     */
    public void setUserDTO(UserDTO value) {
        this.userDTO = value;
    }

    /**
     * Gets the value of the accommodationUnitDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationUnitDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationUnitDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationUnitDTO }
     * 
     * 
     */
    public List<AccommodationUnitDTO> getAccommodationUnitDTO() {
        if (accommodationUnitDTO == null) {
            accommodationUnitDTO = new ArrayList<AccommodationUnitDTO>();
        }
        return this.accommodationUnitDTO;
    }

    /**
     * Gets the value of the extraServiceDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extraServiceDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtraServiceDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtraServiceDTO }
     * 
     * 
     */
    public List<ExtraServiceDTO> getExtraServiceDTO() {
        if (extraServiceDTO == null) {
            extraServiceDTO = new ArrayList<ExtraServiceDTO>();
        }
        return this.extraServiceDTO;
    }

    /**
     * Gets the value of the imageDTO property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageDTO property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageDTO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImageDTO }
     * 
     * 
     */
    public List<ImageDTO> getImageDTO() {
        if (imageDTO == null) {
            imageDTO = new ArrayList<ImageDTO>();
        }
        return this.imageDTO;
    }

}
