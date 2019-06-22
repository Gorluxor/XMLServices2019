//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 02:37:11 AM CEST 
//


package com.megatravel.dtos.admin;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.megatravel.dtos.reservations.Adapter1;
import com.megatravel.dtos.types.LocationDTO;
import com.megatravel.models.admin.User;


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
 *         &lt;element name="Id" type="{http://www.megatravel.com/types}id" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.megatravel.com/types}name"/>
 *         &lt;element name="lastName" type="{http://www.megatravel.com/types}name"/>
 *         &lt;element name="email" type="{http://www.megatravel.com/types}email"/>
 *         &lt;element name="password" type="{http://www.megatravel.com/types}password" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.megatravel.com/types}name"/>
 *         &lt;element name="birthday" type="{http://www.megatravel.com/types}Date"/>
 *         &lt;element name="phoneNumber" type="{http://www.megatravel.com/types}phoneNumber"/>
 *         &lt;element name="pib" type="{http://www.megatravel.com/types}pib"/>
 *         &lt;element name="activatedUser" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.megatravel.com/types}locationDTO" minOccurs="0"/>
 *         &lt;element ref="{http://www.megatravel.com/admin}roleDTO"/>
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
    "lastName",
    "email",
    "password",
    "country",
    "birthday",
    "phoneNumber",
    "pib",
    "activatedUser",
    "locationDTO",
    "roleDTO"
})
@XmlRootElement(name = "userDTO")
public class UserDTO {

    @XmlElement(name = "Id")
    protected Long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(required = true)
    protected String email;
    protected String password;
    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date birthday;
    @XmlElement(required = true)
    protected String phoneNumber;
    @XmlElement(required = true)
    protected String pib;
    protected boolean activatedUser;
    @XmlElement(namespace = "http://www.megatravel.com/types")
    protected LocationDTO locationDTO;
    @XmlElement(required = true)
    protected RoleDTO roleDTO;

    public UserDTO() {
    }

    public UserDTO(User user) {

        this.id = user.getId();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        //this.password = user.getPassword(); // Should not give back to frontned
        this.country = user.getCountry();
        this.birthday = user.getBirthday();
        this.phoneNumber = user.getPhoneNumber();
        this.pib = user.getPib();
        this.activatedUser = user.isActivatedUser();
        this.locationDTO = user.getLocation() == null ? null : new LocationDTO(user.getLocation());
        this.roleDTO = new RoleDTO(user.getRole());
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
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
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthday(Date value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the pib property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPib() {
        return pib;
    }

    /**
     * Sets the value of the pib property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPib(String value) {
        this.pib = value;
    }

    /**
     * Gets the value of the activatedUser property.
     * 
     */
    public boolean isActivatedUser() {
        return activatedUser;
    }

    /**
     * Sets the value of the activatedUser property.
     * 
     */
    public void setActivatedUser(boolean value) {
        this.activatedUser = value;
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
     * Gets the value of the roleDTO property.
     * 
     * @return
     *     possible object is
     *     {@link RoleDTO }
     *     
     */
    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    /**
     * Sets the value of the roleDTO property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleDTO }
     *     
     */
    public void setRoleDTO(RoleDTO value) {
        this.roleDTO = value;
    }

}
