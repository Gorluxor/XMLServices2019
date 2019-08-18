package com.megatravel.controller;

import com.megatravel.dtos.admin.UserDTO;
import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.dtos.rating.RatingSQL;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.models.agent.Accommodation;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.models.rating.Rating;
import com.megatravel.service.AccommodationServiceImpl;
import com.megatravel.service.ReservationServiceImpl;
import com.megatravel.service.UserServiceImpl;
import com.megatravel.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/acc")
@RestController
public class AccommodationController {

    @Autowired
    private AccommodationServiceImpl accommodationService;

    @Autowired
    private RestTemplate template;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ReservationServiceImpl reservationService;

    @RequestMapping(value = "/adminApprove", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> adminApprove(@RequestBody RatingSQL ratingSQL, HttpServletRequest request){

        HttpEntity<RatingSQL> rating = new HttpEntity<RatingSQL>(ratingSQL);

        String response = template.postForObject("http://localhost:8098/adminApproveComment", rating, String.class);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/addRating", method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addRating(@RequestBody RatingSQL ratingSQL, HttpServletRequest request){



        HttpEntity<RatingSQL> rating = new HttpEntity<RatingSQL>(ratingSQL);
        String response = template.postForObject("http://localhost:8096/addRating", rating, String.class);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/getRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getRatings()
    {
        System.out.println("*********************");
        ResponseEntity<List<RatingSQL>> ratings = template.exchange(
                "http://localhost:8090/getAllRatings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingSQL>>(){});
        System.out.println("****************************");
        List<RatingSQL> ratings1 = ratings.getBody();
        System.out.println(ratings1);
        System.out.println(ratings);
        System.out.println(ratings.getBody());
        RatingDTO pom;
        ArrayList<RatingDTO> ratingsFinal = new ArrayList<RatingDTO>();
        for(RatingSQL r : ratings1){
            System.out.println(r.getComment());
            pom = new RatingDTO();
            pom.setComment(r.getComment());
            pom.setAdminApproved(r.isAdmin_approved());
            pom.setId(r.getId());
            pom.setDate(r.getDate());
            pom.setRatingValue(r.getRating_value());

            try{
                UserDTO u = new UserDTO();
                u = userService.findOne(r.getUser_id());
                pom.setUserDTO(u);
            }catch(Exception e){

            }
            try{
                ReservationDTO res = new ReservationDTO();
                res = reservationService.findOne(r.getReservation_id());
                pom.setReservationDTO(res);
            }catch(Exception e1){

            }
            try{
                AccommodationDTO a = new AccommodationDTO();
                a = accommodationService.findOne(r.getAccommodation_id());


                pom.setAccommodationDTO(a);
            }catch(Exception e2){

            }

            ratingsFinal.add(pom);

        }
        return new ResponseEntity<>(ratingsFinal, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllApprovedRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getAllApprovedRatings()
    {
        System.out.println("*********************");
        ResponseEntity<List<RatingSQL>> ratings = template.exchange(
                "http://localhost:8092/getAllApprovedRatings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingSQL>>(){});
        System.out.println("****************************");
        List<RatingSQL> ratings1 = ratings.getBody();
        System.out.println(ratings1);
        System.out.println(ratings);
        System.out.println(ratings.getBody());
        RatingDTO pom;
        ArrayList<RatingDTO> ratingsFinal = new ArrayList<RatingDTO>();
        for(RatingSQL r : ratings1){
            System.out.println(r.getComment());
            pom = new RatingDTO();
            pom.setComment(r.getComment());
            pom.setAdminApproved(r.isAdmin_approved());
            pom.setId(r.getId());
            pom.setDate(r.getDate());
            pom.setRatingValue(r.getRating_value());

            try{
                UserDTO u = new UserDTO();
                u = userService.findOne(r.getUser_id());
                pom.setUserDTO(u);
            }catch(Exception e){

            }
            try{
                ReservationDTO res = new ReservationDTO();
                res = reservationService.findOne(r.getReservation_id());
                pom.setReservationDTO(res);
            }catch(Exception e1){

            }
            try{
                AccommodationDTO a = new AccommodationDTO();
                a = accommodationService.findOne(r.getAccommodation_id());


                pom.setAccommodationDTO(a);
            }catch(Exception e2){

            }

                ratingsFinal.add(pom);


        }
        return new ResponseEntity<>(ratingsFinal, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUnapprovedRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUnapprovedRatings()
    {
        System.out.println("*********************");
        ResponseEntity<List<RatingSQL>> ratings = template.exchange(
                "http://localhost:8094/getAllUnapprovedRatings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingSQL>>(){});
        System.out.println("****************************");
        List<RatingSQL> ratings1 = ratings.getBody();
        System.out.println(ratings1);
        System.out.println(ratings);
        System.out.println(ratings.getBody());
        RatingDTO pom;
        ArrayList<RatingDTO> ratingsFinal = new ArrayList<RatingDTO>();
        for(RatingSQL r : ratings1){
            System.out.println(r.getComment());
            pom = new RatingDTO();
            pom.setComment(r.getComment());
            pom.setAdminApproved(r.isAdmin_approved());
            pom.setId(r.getId());
            pom.setDate(r.getDate());
            pom.setRatingValue(r.getRating_value());

            try{
                UserDTO u = new UserDTO();
                u = userService.findOne(r.getUser_id());
                pom.setUserDTO(u);
            }catch(Exception e){

            }
            try{
                ReservationDTO res = new ReservationDTO();
                res = reservationService.findOne(r.getReservation_id());
                pom.setReservationDTO(res);
            }catch(Exception e1){

            }
               try{
                   AccommodationDTO a = new AccommodationDTO();
                   a = accommodationService.findOne(r.getAccommodation_id());


                   pom.setAccommodationDTO(a);
               }catch(Exception e2){

               }




                ratingsFinal.add(pom);



        }
        return new ResponseEntity<>(ratingsFinal, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(){
        List<Accommodation> list = accommodationService.getAllAccommodations();
        return new ResponseEntity<>(convertToAccDTO(list), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.getAccommodation(accommodationId)), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.createAccommodation(accommodationDTO)), HttpStatus.CREATED);
    }


    @Deprecated
    @RequestMapping(method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.updateAccommodation(accommodationDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccommodation(@PathVariable("accId") Long accommodationId){
        accommodationService.deleteAccommodation(accommodationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("This is a hello from Reservation", HttpStatus.OK);
    }


    @RequestMapping(value = "/unitAgent", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationDTO>> getAgentAccommodations(HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        return new ResponseEntity<>(convertToAccDTO(accommodationService.getAccommodationForAgent(email)), HttpStatus.OK);
    } // should be only one, but can still by database

    private List<AccommodationDTO> convertToAccDTO(List<Accommodation> list){
        List<AccommodationDTO> values = new ArrayList<>();
        for (Accommodation accommodation : list){
            values.add(new AccommodationDTO(accommodation));
        }
        return values;
    }
}
