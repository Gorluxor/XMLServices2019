package com.megatravel.service;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.interfaces.ReservationInterface;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.AccommodationUnitRepository;
import com.megatravel.repository.ReservationRepository;
import com.megatravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;

    public Reservation createReservation(@NotNull ReservationDTO reservationDTO) {

        if (reservationDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad reservationDTO");
        }

        Reservation reservation = new Reservation(reservationDTO);

        reservation.setStayRealized(false);

        reservation.setLastChangedDate(new Date());
        reservation.setUser(userRepository.getOne(reservation.getUser().getId()));

        if (reservation.getAccommodationUnit() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Accommodation Units reserved");
        }
        if (reservation.getDepartureDate().before(reservation.getArrivalDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departure before arrival? time travel?");
        }

        List<AccommodationUnit> list = new ArrayList<>();
        for (AccommodationUnitDTO accommodationUnitDTO : reservationDTO.getAccommodationUnitDTO()){
            list.add(accommodationUnitRepository.getOne(accommodationUnitDTO.getId()));
        }
        reservation.setAccommodationUnit(list);

        reservationRepository.save(reservation);
        return null;

    }

    public Reservation cancelReservation(Long reservationId, String email) {

        User user = userRepository.findByEmail(email);

        Reservation reservation = reservationRepository.getOne(reservationId);

        if (!user.getId().equals(reservation.getUser().getId()) ){
            //Checks if the user is the one that booked it, it is agent if he put the reservation
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Only the person that booked can cancel");
        }

        user.getReservations().remove(reservation);
        reservationRepository.delete(reservation);

        return  reservation;

    }

}
