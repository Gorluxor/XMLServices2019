package com.megatravel.controller;

import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RatingDTO> addRating(@RequestBody RatingDTO ratingDTO){
        return new ResponseEntity<>(this.ratingService.addRating(ratingDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RatingDTO> removeRating(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.ratingService.RemoveRating(id), HttpStatus.OK);
    }



}