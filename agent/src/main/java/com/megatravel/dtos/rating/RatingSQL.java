package com.megatravel.dtos.rating;

import java.util.Date;

public class RatingSQL {

    protected long id;
    protected double rating_value;
    protected String comment;
    protected Date date;
    protected boolean admin_approved;
    protected long user_id;
    protected long reservation_id;
    protected long accommodation_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getRating_value() {
        return rating_value;
    }

    public void setRating_value(double rating_value) {
        this.rating_value = rating_value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAdmin_approved() {
        return admin_approved;
    }

    public void setAdmin_approved(boolean admin_approved) {
        this.admin_approved = admin_approved;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public long getAccommodation_id() {
        return accommodation_id;
    }

    public void setAccommodation_id(long accommodation_id) {
        this.accommodation_id = accommodation_id;
    }

    public RatingSQL(long id, double rating_value, String comment, Date date, boolean admin_approved, long user_id, long reservation_id, long accommodation_id) {
        this.id = id;
        this.rating_value = rating_value;
        this.comment = comment;
        this.date = date;
        this.admin_approved = admin_approved;
        this.user_id = user_id;
        this.reservation_id = reservation_id;
        this.accommodation_id = accommodation_id;
    }

    public RatingSQL(){

    }
}
