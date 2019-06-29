//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 02:37:11 AM CEST 
//


package com.megatravel.models.reservations;


import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.AccommodationUnit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected Date arrivalDate;

    protected Date departureDate;

    protected boolean stayRealized;

    protected BigDecimal reservationPrice;

    @ManyToOne
    protected User user;

    @ManyToMany
    protected List<AccommodationUnit> accommodationUnit;

    protected Date lastChangedDate;

    public Reservation() {
        this.lastChangedDate = new Date();
    }


    public Reservation(ReservationDTO reservationDTO) {
        this.id = reservationDTO.getId();
        this.arrivalDate = reservationDTO.getArrivalDate();
        this.departureDate = reservationDTO.getDepartureDate();
        this.stayRealized = reservationDTO.isStayRealized();
        this.reservationPrice = reservationDTO.getReservationPrice();
        this.user = reservationDTO.getUserDTO() == null ? null : new User(reservationDTO.getUserDTO());

        if (reservationDTO.getAccommodationUnitDTO() == null){
            this.accommodationUnit = null;
        }else {
            List<AccommodationUnit> list = new ArrayList<>();
            for (AccommodationUnitDTO accommodationUnitDTO : reservationDTO.getAccommodationUnitDTO()){
                list.add(new AccommodationUnit(accommodationUnitDTO));
            }
            this.accommodationUnit = list;
        }

        this.lastChangedDate = reservationDTO.getLastChangedDate();
    }


    public Reservation(Date arrivalDate, Date departureDate, boolean stayRealized, BigDecimal reservationPrice, User user) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.stayRealized = stayRealized;
        this.reservationPrice = reservationPrice;
        this.user = user;
        this.accommodationUnit = new ArrayList<AccommodationUnit>();
    }


    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public long getId() {
        return id;
    }


    public void setId(long value) {
        this.id = value;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }


    public void setArrivalDate(Date value) {
        this.arrivalDate = value;
    }


    public Date getDepartureDate() {
        return departureDate;
    }


    public void setDepartureDate(Date value) {
        this.departureDate = value;
    }


    public boolean isStayRealized() {
        return stayRealized;
    }

    public void setStayRealized(boolean value) {
        this.stayRealized = value;
    }

    public BigDecimal getReservationPrice() {
        return reservationPrice;
    }

    public void setReservationPrice(BigDecimal value) {
        this.reservationPrice = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User value) {
        this.user = value;
    }


    public List<AccommodationUnit> getAccommodationUnit() {
        if (accommodationUnit == null) {
            accommodationUnit = new ArrayList<AccommodationUnit>();
        }
        return this.accommodationUnit;
    }

    public void setAccommodationUnit(List<AccommodationUnit> accommodationUnit) {
        this.accommodationUnit = accommodationUnit;
    }


}