//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.22 at 09:49:36 PM CEST
//


package com.megatravel.dtos.rating;

import com.megatravel.dtos.admin.UserDTO;
import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.models.rating.Rating;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 *         &lt;element name="ratingValue" type="{http://www.megatravel.com/types}rating"/>
 *         &lt;element name="comment" type="{http://www.megatravel.com/types}msg" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.megatravel.com/types}Date"/>
 *         &lt;element name="adminApproved" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{http://www.megatravel.com/admin}userDTO"/>
 *         &lt;element ref="{http://megatravel.com/reservations}reservationDTO"/>
 *         &lt;element ref="{http://www.megatravel.com/agent}accommodationDTO"/>
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
        "ratingValue",
        "comment",
        "date",
        "adminApproved",
        "userDTO",
        "reservationDTO",
        "accommodationDTO",
        "lastChangedDate"
})
@XmlRootElement(name = "ratingDTO")
public class RatingDTO {

    protected long id;
    protected double ratingValue;
    protected String comment;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date date;
    protected boolean adminApproved;
    @XmlElement(namespace = "http://www.megatravel.com/admin", required = true)
    protected UserDTO userDTO;
    @XmlElement(namespace = "http://megatravel.com/reservations", required = true)
    protected ReservationDTO reservationDTO;
    @XmlElement(namespace = "http://www.megatravel.com/agent", required = true)
    protected AccommodationDTO accommodationDTO;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    protected Date lastChangedDate;

    public RatingDTO() {
    }

    public RatingDTO(Rating rating) {
        this.id = rating.getId();
        this.ratingValue = rating.getRatingValue();
        this.comment = rating.getComment();
        this.date = rating.getDate();
        this.adminApproved = rating.isAdminApproved();
        this.userDTO = new UserDTO(rating.getUser());
        this.reservationDTO = new ReservationDTO(rating.getReservation());
        this.lastChangedDate = rating.getLastChangedDate();
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
     * Gets the value of the ratingValue property.
     *
     */
    public double getRatingValue() {
        return ratingValue;
    }

    /**
     * Sets the value of the ratingValue property.
     *
     */
    public void setRatingValue(double value) {
        this.ratingValue = value;
    }

    /**
     * Gets the value of the comment property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the value of the comment property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Gets the value of the date property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * Gets the value of the adminApproved property.
     *
     */
    public boolean isAdminApproved() {
        return adminApproved;
    }

    /**
     * Sets the value of the adminApproved property.
     *
     */
    public void setAdminApproved(boolean value) {
        this.adminApproved = value;
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
     * Gets the value of the reservationDTO property.
     *
     * @return
     *     possible object is
     *     {@link ReservationDTO }
     *
     */
    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }

    /**
     * Sets the value of the reservationDTO property.
     *
     * @param value
     *     allowed object is
     *     {@link ReservationDTO }
     *
     */
    public void setReservationDTO(ReservationDTO value) {
        this.reservationDTO = value;
    }

    /**
     * Gets the value of the accommodationDTO property.
     *
     * @return
     *     possible object is
     *     {@link AccommodationDTO }
     *
     */
    public AccommodationDTO getAccommodationDTO() {
        return accommodationDTO;
    }

    /**
     * Sets the value of the accommodationDTO property.
     *
     * @param value
     *     allowed object is
     *     {@link AccommodationDTO }
     *
     */
    public void setAccommodationDTO(AccommodationDTO value) {
        this.accommodationDTO = value;
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
