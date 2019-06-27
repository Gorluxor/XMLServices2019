package com.megatravel.service;


import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation confirmReservation(Long reservationId){
        Reservation reservation = reservationRepository.getOne(reservationId);

        reservation.setStayRealized(true);
        reservationRepository.save(reservation);

        return reservation;
    }
}
