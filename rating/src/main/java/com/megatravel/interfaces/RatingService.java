package com.megatravel.interfaces;

import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.dtos.reservations.ReservationDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface RatingService {



    @WebMethod
    RatingDTO addRating(@WebParam(name = "ratingDTO") RatingDTO ratingDTO);

    @WebMethod
    RatingDTO RemoveRating(@WebParam(name = "ratingId") Long ratingId);






}