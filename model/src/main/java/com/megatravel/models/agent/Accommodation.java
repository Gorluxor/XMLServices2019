//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 02:37:11 AM CEST 
//


package com.megatravel.models.agent;


import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.models.admin.User;
import com.megatravel.models.types.Location;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuppressWarnings("WeakerAccess")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String name;

    protected String description;

    protected BigInteger cancelationDays;

    @ManyToOne
    protected Location location;

    @ManyToOne
    protected AccommodationType accommodationType;

    @OneToOne
    protected User user; // User that administrates the accommodation

    @OneToMany(mappedBy = "accommodation")
    protected List<AccommodationUnit> accommodationUnit = new ArrayList<>();

    @OneToMany(mappedBy = "serviceForAcc")
    protected List<Service> service = new ArrayList<>();

    @OneToMany(mappedBy = "belongsToAccommodation")
    protected List<Image> image = new ArrayList<>();

    public Accommodation() {
        super();
    }

    public Accommodation(AccommodationDTO accommodationDTO){
        this.id = accommodationDTO.getId();
        this.name = accommodationDTO.getName();
        this.description = accommodationDTO.getDescription();
        this.cancelationDays = accommodationDTO.getCancelationDays();
        this.location = new Location(accommodationDTO.getLocationDTO());
    }

    public Accommodation(String name, String description, BigInteger cancelationDays) {
        this.name = name;
        this.description = description;
        this.cancelationDays = cancelationDays;
    }




    public long getId() {
        return id;
    }


    public void setId(long value) {
        this.id = value;
    }


    public String getName() {
        return name;
    }


    public void setName(String value) {
        this.name = value;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String value) {
        this.description = value;
    }


    public BigInteger getCancelationDays() {
        return cancelationDays;
    }


    public void setCancelationDays(BigInteger value) {
        this.cancelationDays = value;
    }


    public Location getLocation() {
        return location;
    }


    public void setLocation(Location value) {
        this.location = value;
    }


    public AccommodationType getAccommodationType() {
        return accommodationType;
    }


    public void setAccommodationType(AccommodationType value) {
        this.accommodationType = value;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User value) {
        this.user = value;
    }


    public void setAccommodationUnit(List<AccommodationUnit> accommodationUnit) {
        this.accommodationUnit = accommodationUnit;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public List<AccommodationUnit> getAccommodationUnit() {
        if (accommodationUnit == null) {
            accommodationUnit = new ArrayList<AccommodationUnit>();
        }
        return this.accommodationUnit;
    }


    public List<Service> getService() {
        if (service == null) {
            service = new ArrayList<Service>();
        }
        return this.service;
    }


    public List<Image> getImage() {
        if (image == null) {
            image = new ArrayList<Image>();
        }
        return this.image;
    }

}
