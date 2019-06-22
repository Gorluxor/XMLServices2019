package com.megatravel.service;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.models.admin.User;
import com.megatravel.models.rating.Rating;
import com.megatravel.models.reservations.Reservation;
import com.megatravel.repository.RatingRepository;
import com.megatravel.repository.ReservationRepository;
import com.megatravel.repository.UserRepository;
import com.megatravel.utils.PageRequestProvider;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@Service
public class RatingServiceImpl {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ReservationRepository reservationRepository;


    public RatingDTO addRating(RatingDTO ratingDTO) {

        Rating rating = new Rating(ratingDTO);

        rating.setAdminApproved(false);

        if (rating.getUser() == null || rating.getReservation() == null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "User or Reservation is null");
        }

        User user = userRepository.getOne(rating.getUser().getId());
        Reservation reservation = reservationRepository.getOne(rating.getReservation().getId());

        rating.setReservation(reservation);
        rating.setUser(user);
        ratingRepository.save(rating);
        return new RatingDTO(rating);
    }

    public RatingDTO removeRating(Long ratingId) throws ResponseStatusException{

        Rating rating = ratingRepository.getOne(ratingId);
        if (rating == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such rating id");
        }
        RatingDTO retVal = new RatingDTO(rating);
        ratingRepository.delete(rating);
        return retVal;
    }

    public RatingDTO approveRating(Long ratingId){
        Rating rating = ratingRepository.getOne(ratingId);
        if (rating == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such rating id");
        }
        rating.setAdminApproved(true);
        ratingRepository.save(rating);
        return new RatingDTO(rating);
    }

    public List<RatingDTO> ratingForReservation(Long accommodationId) {
        List<Rating> results =  ratingRepository.forAccommodation(accommodationId);
        List<RatingDTO> retVal = new ArrayList<>();
        for (Rating r : results){
            retVal.add(new RatingDTO(r));
        }
        return retVal;
    }

    public Double averageRating(Long accommodationId){
        return ratingRepository.forAccommodationAverage(accommodationId);
    }


    public List<RatingDTO> ratingNotApproved(){
        List<Rating> ratings =  ratingRepository.findAllByAdminApprovedIsFalse();
        List<RatingDTO> retVal = new ArrayList<>();
        for (Rating r : ratings){
            retVal.add(new RatingDTO(r));
        }
        return retVal;
    }


}
