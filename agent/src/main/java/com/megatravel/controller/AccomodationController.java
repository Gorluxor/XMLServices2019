package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.service.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/accommodation")
public class AccomodationController {


    @Autowired
    private AccomodationService accomodationService;

    @RequestMapping(value = "/all" , method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(){

        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable Long id){
        return null;

    }

    @RequestMapping(value = "/{id}" ,method = RequestMethod.POST , consumes = "application/json")
    public ResponseEntity<AccommodationDTO> saveAccommodation(@PathVariable Long id, @RequestBody AccommodationDTO accommodationDTO){
        return null;

    }

    @RequestMapping(method = RequestMethod.PUT ,consumes = "application/json")
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return null;

    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id){
        return null;

     
    }

}