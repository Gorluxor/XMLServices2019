package com.megatravel.service;

import com.megatravel.dtos.rating.RatingSQL;
import com.megatravel.dtos.rating.Rezultat;
import com.megatravel.models.admin.User;
import com.megatravel.models.rating.Rating;
import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.AccommodationRepository;
import com.megatravel.repository.ReservationRepository;
import com.megatravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private RestTemplate restTemplate;



    public RatingSQL addRating(RatingSQL ratingDTO) {
        System.out.println("Dodavanje recenzija");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        Optional<Reservation> reservation = reservationRepository.findById(ratingDTO.getReservation_id());


        RatingSQL ratingSQL = new RatingSQL();

        ratingSQL.setId(ratingDTO.getId());
        //ratingSQL.setAccommodation_id(ratingDTO.getAccommodation_id());
        ratingSQL.setAccommodation_id(reservation.get().getAccommodationUnit().get(0).getAccommodation().getId());
        ratingSQL.setAdmin_approved(false);
        ratingSQL.setComment(ratingDTO.getComment());
        ratingSQL.setDate(ratingDTO.getDate());
        ratingSQL.setRating_value(ratingDTO.getRating_value());
        ratingSQL.setUser_id(ratingDTO.getUser_id());

        HttpEntity<Object> entity = new HttpEntity<Object>(ratingSQL, headers);
        return restTemplate.exchange("http://localhost:8096/addRating",HttpMethod.POST, entity, RatingSQL.class).getBody();

    }
/*
    public RatingSQL removeRating(Long ratingId) throws ResponseStatusException{

        Rating rating = ratingRepository.getOne(ratingId);
        if (rating == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such rating id");
        }
        RatingSQL retVal = new RatingSQL(rating);
        ratingRepository.delete(rating);
        return retVal;
    }
*/
    public RatingSQL approveRating(Long ratingId){
        System.out.println("ID KOD REJTINGA JE "+ratingId );

        System.out.println("approve recenzija");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        //List<RatingSQL> recensions = new ArrayList<RatingSQL>();
        RatingSQL ratingSQL = new RatingSQL();
        ratingSQL.setId(ratingId);
        HttpEntity<Object> entity = new HttpEntity<Object>(ratingSQL, headers);
        return restTemplate.exchange("http://localhost:8098/adminApproveComment/",HttpMethod.PUT, entity, RatingSQL.class).getBody();

    }
/*
    public List<RatingSQL> ratingForReservation(Long accommodationId) {
        List<Rating> results =  ratingRepository.forAccommodation(accommodationId);
        List<RatingSQL> retVal = new ArrayList<>();
        for (Rating r : results){
            retVal.add(new RatingSQL(r));
        }
        return retVal;
    }

    public Double averageRating(Long accommodationId){
        return ratingRepository.forAccommodationAverage(accommodationId);
    }
*/


    public List<RatingSQL> ratingNotApproved(){

        System.out.println("Dobijanje neodobrenih recenzija");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<RatingSQL> recensions = new ArrayList<RatingSQL>();
        HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
        return restTemplate.exchange("http://localhost:8094/getAllUnapprovedRatings", HttpMethod.GET, entity, new ParameterizedTypeReference<List<RatingSQL>>() {
        }).getBody();
    }

    public List<RatingSQL> ratingApproved(){

        System.out.println("Dobijanje odobrenih recenzija");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<RatingSQL> recensions = new ArrayList<RatingSQL>();
        HttpEntity<Object> entity = new HttpEntity<Object>(recensions, headers);
        return restTemplate.exchange("http://localhost:8092/getAllApprovedRatings", HttpMethod.GET, entity, new ParameterizedTypeReference<List<RatingSQL>>() {
        }).getBody();
    }


}
