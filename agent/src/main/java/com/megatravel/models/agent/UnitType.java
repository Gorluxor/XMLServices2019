//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 02:37:11 AM CEST 
//


package com.megatravel.models.agent;

import com.megatravel.dtos.agent.UnitTypeDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@SuppressWarnings("WeakerAccess")
@Entity
public class UnitType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String nameOfUnitType;

    protected Date lastChangedDate;

    public UnitType() {
        this.lastChangedDate = new Date();
    }

    public UnitType(UnitTypeDTO unitTypeDTO) {
        if (unitTypeDTO != null){
            this.id = unitTypeDTO.getId();
            this.nameOfUnitType = unitTypeDTO.getNameOfUnitType();
            this.lastChangedDate = unitTypeDTO.getLastChangedDate();
        }
    }


    public UnitType(com.megatravel.interfaces.UnitTypeDTO unitTypeDTO) {
            this.id = unitTypeDTO.getId();
            this.nameOfUnitType = unitTypeDTO.getNameOfUnitType();
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public UnitType(String nameOfUnitType) {
        this.nameOfUnitType = nameOfUnitType;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getNameOfUnitType() {
        return nameOfUnitType;
    }

    public void setNameOfUnitType(String value) {
        this.nameOfUnitType = value;
    }

}
