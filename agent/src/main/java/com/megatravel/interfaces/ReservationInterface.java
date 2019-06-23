package com.megatravel.interfaces;

import com.megatravel.dtos.reservations.ReservationDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ReservationInterface {
    @WebMethod
    ReservationDTO confirmReservation(@WebParam(name = "ReservationId") Long reservationId);


}
