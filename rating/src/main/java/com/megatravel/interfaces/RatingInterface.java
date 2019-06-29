package com.megatravel.interfaces;

import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.dtos.reservations.ReservationDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface RatingInterface {

    @WebMethod
    RatingDTO addRating(@WebParam(name = "ratingDTO") RatingDTO ratingDTO);

    @WebMethod
    RatingDTO removeRating(@WebParam(name = "ratingId") Long ratingId);

    @WebMethod
    RatingDTO approveRating(@WebParam(name = "ratingId") Long id);

    @WebMethod
    List<RatingDTO> ratingForReservation(@WebParam(name = "reservationId") Long reservationId);


}
