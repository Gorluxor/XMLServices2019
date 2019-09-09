package com.megatravel.SOAPEndpoints;

import com.megatravel.interfaces.*;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.models.agent.Pricing;
import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.AccommodationRepository;
import com.megatravel.repository.AccommodationUnitRepository;
import com.megatravel.repository.ReservationRepository;
import com.megatravel.repository.UserRepository;
import com.megatravel.service.AccommodationServiceImpl;
import com.megatravel.service.PricingServiceImpl;
import com.megatravel.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Endpoint
public class ReservationEndpoint {
    final String NAMESPACE = "http://interfaces.megatravel.com/";

    @Autowired
    ReservationServiceImpl reservationService;

    @Autowired
    private PricingServiceImpl pricingService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;


    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetListReservationsForAgent")
    public GetListReservationsForAgentResponse getAll(@RequestPayload GetListReservationsForAgent input) {
        GetListReservationsForAgentResponse response = new GetListReservationsForAgentResponse();

        System.out.println("REZERVACIJA");

        List<Reservation> reservationList = reservationService.getListReservationForAgent(input.getEmail());

        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation r:reservationList) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setArrivalDate(r.getArrivalDate());
            reservationDTO.setDepartureDate(r.getDepartureDate());
            reservationDTO.setId(r.getId());
            reservationDTO.setReservationPrice(r.getReservationPrice());
            reservationDTO.setStayRealized(r.isStayRealized());
            UserDTO userDTO = new UserDTO();
            userDTO.setActivatedUser(r.getUser().isActivatedUser());
            userDTO.setBirthday(r.getUser().getBirthday());
            userDTO.setCountry(r.getUser().getCountry());
            userDTO.setEmail(r.getUser().getEmail());
            userDTO.setId(r.getUser().getId());
            userDTO.setLastName(r.getUser().getLastName());
            userDTO.setName(r.getUser().getName());
         /*   LocationDTO locationDTO = new LocationDTO();
            locationDTO.setCity(r.getUser().getLocation().getCity());
            locationDTO.setCountry(r.getUser().getLocation().getCountry());
            locationDTO.setId(r.getUser().getLocation().getId());
            locationDTO.setLatitude(r.getUser().getLocation().getLatitude());
            locationDTO.setLongitude(r.getUser().getLocation().getLongitude());
            locationDTO.setNumber(r.getUser().getLocation().getNumber());
            locationDTO.setPostalCode(r.getUser().getLocation().getPostalCode());
            locationDTO.setStreet(r.getUser().getLocation().getStreet());

            userDTO.setLocationDTO(locationDTO);*/
            userDTO.setPassword(r.getUser().getPassword());
        //    userDTO.setPib(r.getUser().getPib());
          //  userDTO.setPhoneNumber(r.getUser().getPhoneNumber());

            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(r.getUser().getRole().getId());
            roleDTO.setName(r.getUser().getRole().getRoleName());

            userDTO.setRoleDTO(roleDTO);
            reservationDTO.setUserDTO(userDTO);


            reservationDTOS.add(reservationDTO);

        }

        response.setReservationDTO(reservationDTOS);

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "ConfirmReservation")
    public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservation input) {
        ConfirmReservationResponse response = new ConfirmReservationResponse();

        System.out.println("Confirm");

        Reservation r = reservationService.confirmReservation(input.getReservationId());

        //System.out.println("Res " + r);

        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setArrivalDate(r.getArrivalDate());
        reservationDTO.setDepartureDate(r.getDepartureDate());
        reservationDTO.setId(r.getId());
        reservationDTO.setReservationPrice(r.getReservationPrice());
        reservationDTO.setStayRealized(r.isStayRealized());
        UserDTO userDTO = new UserDTO();
       // userDTO.setActivatedUser(r.getUser().isActivatedUser());
      ///  userDTO.setBirthday(r.getUser().getBirthday());
      //  userDTO.setCountry(r.getUser().getCountry());
        userDTO.setEmail(r.getUser().getEmail());
        userDTO.setId(r.getUser().getId());
      //  userDTO.setLastName(r.getUser().getLastName());
     //   userDTO.setName(r.getUser().getName());
      /*  LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCity(r.getUser().getLocation().getCity());
        locationDTO.setCountry(r.getUser().getLocation().getCountry());
        locationDTO.setId(r.getUser().getLocation().getId());
        locationDTO.setLatitude(r.getUser().getLocation().getLatitude());
        locationDTO.setLongitude(r.getUser().getLocation().getLongitude());
        locationDTO.setNumber(r.getUser().getLocation().getNumber());
        locationDTO.setPostalCode(r.getUser().getLocation().getPostalCode());
        locationDTO.setStreet(r.getUser().getLocation().getStreet());

        userDTO.setLocationDTO(locationDTO);
        userDTO.setPassword(r.getUser().getPassword());
        userDTO.setPib(r.getUser().getPib());
        userDTO.setPhoneNumber(r.getUser().getPhoneNumber());

        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(r.getUser().getRole().getId());
        roleDTO.setName(r.getUser().getRole().getRoleName());

        userDTO.setRoleDTO(roleDTO);*/
        reservationDTO.setUserDTO(userDTO);

        response.setReservationDTO(reservationDTO);

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "CreateReservation")
    public CreateReservationResponse create(@RequestPayload CreateReservation input) {
        CreateReservationResponse response = new CreateReservationResponse();

        System.out.println("create");

       // Reservation reservation = new Reservation(input.getReservationDTO());

      /*  for(AccommodationUnitDTO accommodationUnitDTO : input.getReservationDTO().getAccommodationUnitDTO())
        {

                PricingDTO pricingDTO = new PricingDTO();
                pricingDTO.setId(1);
                pricingDTO.setPrice(BigDecimal.valueOf(100));

                accommodationUnitDTO.getPricingDTO().add(pricingDTO);
        }*/

       // Reservation r = reservationService.createRes(input.getReservationDTO(),input.getReservationDTO().getUserDTO().getEmail());

        //System.out.println("Res " + r);

        User user = userRepository.findByEmail(input.getReservationDTO().getUserDTO().getEmail());

        Reservation reservation = new Reservation(input.getReservationDTO());

        reservation.setStayRealized(false);

        reservation.setLastChangedDate(new Date());

        if (input.getReservationDTO().getUserDTO() == null){
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
        for (com.megatravel.interfaces.AccommodationUnitDTO accommodationUnitDTO : input.getReservationDTO().getAccommodationUnitDTO()){
            list.add(accommodationUnitRepository.getOne(accommodationUnitDTO.getId()));
            priceTotal = priceTotal.add(new BigDecimal(130));
        }
        reservation.setAccommodationUnit(list);
        reservation.setReservationPrice(priceTotal);
        reservation.setLastChangedDate(new Date());

        Reservation r = reservationRepository.save(reservation);

///////////////////////////
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setArrivalDate(r.getArrivalDate());
        reservationDTO.setDepartureDate(r.getDepartureDate());
        reservationDTO.setId(r.getId());
        reservationDTO.setReservationPrice(r.getReservationPrice());
        reservationDTO.setStayRealized(r.isStayRealized());
        UserDTO userDTO = new UserDTO();
       // userDTO.setActivatedUser(r.getUser().isActivatedUser());
      //  userDTO.setBirthday(r.getUser().getBirthday());
      //  userDTO.setCountry(r.getUser().getCountry());
      //  userDTO.setEmail(r.getUser().getEmail());
      //  userDTO.setId(r.getUser().getId());
      //  userDTO.setLastName(r.getUser().getLastName());
       // userDTO.setName(r.getUser().getName());
     /*   LocationDTO locationDTO = new LocationDTO();
        locationDTO.setCity(r.getUser().getLocation().getCity());
        locationDTO.setCountry(r.getUser().getLocation().getCountry());
        locationDTO.setId(r.getUser().getLocation().getId());*/
       // locationDTO.setLatitude(r.getUser().getLocation().getLatitude());
        //locationDTO.setLongitude(r.getUser().getLocation().getLongitude());
       // locationDTO.setNumber(r.getUser().getLocation().getNumber());
       // locationDTO.setPostalCode(r.getUser().getLocation().getPostalCode());
      //  locationDTO.setStreet(r.getUser().getLocation().getStreet());

       // userDTO.setLocationDTO(locationDTO);
       // userDTO.setPassword(r.getUser().getPassword());
       // userDTO.setPib(r.getUser().getPib());
       // userDTO.setPhoneNumber(r.getUser().getPhoneNumber());

      //  RoleDTO roleDTO = new RoleDTO();
      //  roleDTO.setId(r.getUser().getRole().getId());
      //  roleDTO.setName(r.getUser().getRole().getRoleName());

      //  userDTO.setRoleDTO(roleDTO);
        reservationDTO.setUserDTO(userDTO);

        response.setReservationDTO(reservationDTO);

        return response;
    }

}

