package com.megatravel.controller;
import com.megatravel.dtos.rating.RatingSQL;
import com.megatravel.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/rating")
@RestController
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<RatingSQL> addRating(@RequestBody RatingSQL ratingDTO){
        return new ResponseEntity<>(this.ratingService.addRating(ratingDTO), HttpStatus.OK);
    }

/*    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<RatingSQL> removeRating(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.ratingService.removeRating(id), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/approve/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RatingSQL> approveRating(@PathVariable("id") Long id){
        System.out.println("ID KOD REJTINGA JE "+id );
        return new ResponseEntity<>(this.ratingService.approveRating(id), HttpStatus.OK);
    }
/*
    @RequestMapping(value = "/accommodation/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<RatingSQL>> accommodationRatings(@PathVariable("id") Long accommodationId){
        return new ResponseEntity<>(this.ratingService.ratingForReservation(accommodationId), HttpStatus.OK);
    }


    @RequestMapping(value = "/accommodation/{id}/average/")
    public ResponseEntity<Double> averageRatingForAccommodation(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.ratingService.averageRating(id), HttpStatus.OK);
    }*/

    @RequestMapping(value = "/AllNotApproved", method = RequestMethod.GET)
    public ResponseEntity<List<RatingSQL>> listNotApproved(){
        List<RatingSQL> ratingDTOS = this.ratingService.ratingNotApproved();

        for (RatingSQL r:ratingDTOS) {

            System.out.println(r + "\n");


        }


        return new ResponseEntity<List<RatingSQL>>(this.ratingService.ratingNotApproved(), HttpStatus.OK);
    }

    @RequestMapping(value = "/AllApproved", method = RequestMethod.GET)
    public ResponseEntity<List<RatingSQL>> listApproved(){
        List<RatingSQL> ratingDTOS = this.ratingService.ratingApproved();

        for (RatingSQL r:ratingDTOS) {

            System.out.println(r + "\n");


        }


        return new ResponseEntity<List<RatingSQL>>(this.ratingService.ratingApproved(), HttpStatus.OK);
    }
}
