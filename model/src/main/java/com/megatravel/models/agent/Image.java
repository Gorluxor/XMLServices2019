//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.08 at 02:37:11 AM CEST 
//


package com.megatravel.models.agent;

import com.megatravel.dtos.agent.ImageDTO;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String title;

    protected String uri;

    @ManyToOne
    protected Image belongsToAccommodation;

    public Image() {
    }

    public Image(ImageDTO imageDTO){
        this.id = imageDTO.getId();
        this.title = imageDTO.getTitle();
        this.uri = imageDTO.getUri();
    }

    public Image(String title, String uri) {
        this.title = title;
        this.uri = uri;
    }

    public Image getBelongsToAccommodation() {
        return belongsToAccommodation;
    }

    public void setBelongsToAccommodation(Image belongsToAccommodation) {
        this.belongsToAccommodation = belongsToAccommodation;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String value) {
        this.title = value;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String value) {
        this.uri = value;
    }

}
