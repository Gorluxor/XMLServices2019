package com.megatravel.controller;

import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/rating", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO ratingDTO){
        return new ResponseEntity<>(this.ratingService.addRating(ratingDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<RatingDTO> removeRating(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.ratingService.removeRating(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RatingDTO> approveRating(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.ratingService.approveRating(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/accommodation/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<RatingDTO>> accommodationRatings(@PathVariable("id") Long accommodationId){
        return new ResponseEntity<>(this.ratingService.ratingForReservation(accommodationId), HttpStatus.OK);
    }


    @RequestMapping(value = "/accommodation/{id}/average/")
    public ResponseEntity<Double> averageRatingForAccommodation(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.ratingService.averageRating(id), HttpStatus.OK);
    }


}
