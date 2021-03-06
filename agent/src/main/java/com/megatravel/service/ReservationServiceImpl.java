package com.megatravel.service;


import com.megatravel.dtos.admin.UserDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;

    @Autowired
    private PricingServiceImpl pricingService;

    public ReservationDTO findOne(Long id) {
        Optional<Reservation> res = reservationRepository.findById(id);
        if(res.isPresent()) {

            return new ReservationDTO(res.get());
        }
        else {
            System.out.println("NO RESERVATION");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No reservation with requested id");
        }
    }

    public Reservation confirmReservationWithUser(Long reservationId, String email){

        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");
        }


        Reservation reservation = reservationRepository.getOne(reservationId);
        if (!reservation.getAccommodationUnit().get(0).getAccommodation().getUser().equals(user)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "");
        }
        reservation.setLastChangedDate(new Date());
        reservation.setStayRealized(true);
        reservationRepository.save(reservation);

        return reservation;
    }


    public Reservation confirmReservation(Long reservationId){

        System.out.println("USAO OVDE");
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
     //   Reservation reservation = reservationRepository.findById(reservationId);
        reservation.get().setLastChangedDate(new Date());
        reservation.get().setStayRealized(true);
        System.out.println("CUVANJE");
        reservationRepository.save(reservation.get());
          System.out.println("RETURN");
        return reservation.get();
    }

    public Reservation createReservation(@NotNull ReservationDTO reservationDTO, String email) {

      User user = userRepository.findByEmail(email);
//
//        if (user == null){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No user");
//        }else {
//            boolean CanMakeReservation = user.getRole().getRoleName().contains("AGENT") || user.getRole().getRoleName().contains("USER");
//            if (!CanMakeReservation){
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User can't make reservation!");
//            }
//        } //Security stuff

        if (reservationDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad reservationDTO");
        }

        Reservation reservation = new Reservation(reservationDTO);

        reservation.setStayRealized(false);

        reservation.setLastChangedDate(new Date());

       if (reservationDTO.getUserDTO() == null){
           if (user == null){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No user");
           }else {
               reservation.setUser(user);
           }
       }else {
           reservation.setUser(userRepository.getOne(reservation.getUser().getId()));
       }



        if (reservation.getAccommodationUnit() == null || reservation.getAccommodationUnit().size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Accommodation Units reserved");
        }
        if (reservation.getDepartureDate().before(reservation.getArrivalDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departure before arrival? time travel?");
        }

        List<AccommodationUnit> list = new ArrayList<>();
        BigDecimal priceTotal = new BigDecimal(0); //Shouldn't trust frontend calculation for price
        for (AccommodationUnitDTO accommodationUnitDTO : reservationDTO.getAccommodationUnitDTO()){
            list.add(accommodationUnitRepository.getOne(accommodationUnitDTO.getId()));
            priceTotal = priceTotal.add(new BigDecimal(pricingService.getPriceForDatesUnitId(accommodationUnitDTO.getId(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate())));
        }
        reservation.setAccommodationUnit(list);
        reservation.setReservationPrice(priceTotal);
        reservation.setLastChangedDate(new Date());

        reservationRepository.save(reservation);
        return reservation;

    }

    public Reservation createRes(@NotNull com.megatravel.interfaces.ReservationDTO reservationDTO, String email) {

        User user = userRepository.findByEmail(email);
//
//        if (user == null){
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No user");
//        }else {
//            boolean CanMakeReservation = user.getRole().getRoleName().contains("AGENT") || user.getRole().getRoleName().contains("USER");
//            if (!CanMakeReservation){
//                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User can't make reservation!");
//            }
//        } //Security stuff

        if (reservationDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad reservationDTO");
        }

        Reservation reservation = new Reservation(reservationDTO);

        reservation.setStayRealized(false);

        reservation.setLastChangedDate(new Date());

        if (reservationDTO.getUserDTO() == null){
            if (user == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No user");
            }else {
                reservation.setUser(user);
            }
        }else {
            reservation.setUser(userRepository.getOne(reservation.getUser().getId()));
        }



        if (reservation.getAccommodationUnit() == null || reservation.getAccommodationUnit().size() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Accommodation Units reserved");
        }
        if (reservation.getDepartureDate().before(reservation.getArrivalDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departure before arrival? time travel?");
        }

        List<AccommodationUnit> list = new ArrayList<>();
        BigDecimal priceTotal = new BigDecimal(0); //Shouldn't trust frontend calculation for price
        for (com.megatravel.interfaces.AccommodationUnitDTO accommodationUnitDTO : reservationDTO.getAccommodationUnitDTO()){
            list.add(accommodationUnitRepository.getOne(accommodationUnitDTO.getId()));
            priceTotal = priceTotal.add(new BigDecimal(pricingService.getPriceForDatesUnitId(accommodationUnitDTO.getId(), reservationDTO.getArrivalDate(), reservationDTO.getDepartureDate())));
        }
        reservation.setAccommodationUnit(list);
        reservation.setReservationPrice(priceTotal);
        reservation.setLastChangedDate(new Date());

        reservationRepository.save(reservation);
        return reservation;

    }

    public void cancelReservation(Long reservationId, String email) {

        User user = userRepository.findByEmail(email);

        Reservation reservation = reservationRepository.getOne(reservationId);

        if (!user.getId().equals(reservation.getUser().getId()) ){
            //Checks if the user is the one that booked it, it is agent if he put the reservation
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Only the person that booked can cancel");
        }
        user.getReservations().remove(reservation);

        userRepository.save(user);

        reservationRepository.delete(reservation);
    }

    public List<Reservation> getListReservationsForUser(Long id, String email){
        User user;
        if (id == 0){
            user = userRepository.findByEmail(email);
        }else {
            user = userRepository.getOne(id);
        }

        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        return reservationRepository.findAllByUserId(user.getId());
    }

    public List<Reservation> getRealised(Long id, String email){
        User user;
        if (id == 0){
            user = userRepository.findByEmail(email);
        }else {
            user = userRepository.getOne(id);
        }

        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
        }
        List<Reservation> reservations = reservationRepository.findAllByUserId(user.getId());

        List<Reservation> realised = new ArrayList<>();

        for (Reservation r: reservations) {

            if(r.isStayRealized())
            {
                realised.add(r);
            }

        }

        return realised;

    }

    public List<Reservation> getListReservationForAgent(String email){

        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jwt...");
        }

        return reservationRepository.allByAgent(user.getId());
    }

    public List<Reservation> getListUnrealizedForAgent(String email){

        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jwt...");
        }

        return reservationRepository.allUnrealizedByAgent(user.getId());


    }


}
