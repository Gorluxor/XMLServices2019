package com.megatravel.interfaces;

import com.megatravel.dtos.reservations.ReservationDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ReservationInterface {
    @WebMethod
    ReservationDTO confirmReservation(@WebParam(name = "ReservationId") Long reservationId);

    @WebMethod
    ReservationDTO createReservation(@WebParam(name = "ReservationDTO") ReservationDTO reservationDTO);

    @WebMethod
    ReservationDTO cancelReservation(@WebParam(name = "ReservationId") Long reservationId);

    @WebMethod
    List<ReservationDTO> getListReservationsForUser(@WebParam(name = "userId") Long userId);

}
