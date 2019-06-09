package com.megatravel.service;
import com.megatravel.dtos.reservations.ReservationDTO;
import org.springframework.stereotype.Service;


import javax.jws.WebService;

@WebService(endpointInterface = "com.megatravel.interfaces.ReservationService")
@Service
public class ReservationServiceImpl implements com.megatravel.interfaces.ReservationService {

    public static final String ENDPOINT = "/reservation";


    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {
        return null;
    }

    @Override
    public ReservationDTO confirmReservation(Long reservationId) {
        return null;
    }
}
