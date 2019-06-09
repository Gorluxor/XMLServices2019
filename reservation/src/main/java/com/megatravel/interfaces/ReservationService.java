package com.megatravel.interfaces;

import com.megatravel.dtos.messages.ChatRoomDTO;
import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.dtos.reservations.ReservationDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ReservationService {


    @WebMethod
    ReservationDTO createReservation(@WebParam(name = "ReservationDTO") ReservationDTO reservationDTO);

    @WebMethod
    ReservationDTO cancelReservation(@WebParam(name = "ReservationId") Long reservationId);

    @WebMethod
    ReservationDTO confirmReservation(@WebParam(name = "ReservationId") Long reservationId);






}