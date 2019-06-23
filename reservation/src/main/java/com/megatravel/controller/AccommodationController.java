package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.models.agent.Accommodation;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.service.AccommodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/acc")
@RestController
public class AccommodationController {

    @Autowired
    private AccommodationServiceImpl accommodationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(){
        List<Accommodation> list = accommodationService.getAllAccommodations();
        List<AccommodationDTO> values = new ArrayList<>();
        for (Accommodation accommodation : list){
            values.add(new AccommodationDTO(accommodation));
        }
        return new ResponseEntity<>(values, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.getAccommodation(accommodationId)), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.createAccommodation(accommodationDTO)), HttpStatus.CREATED);
    }


    @Deprecated
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.updateAccommodation(accommodationDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationDTO> deleteAccommodation(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.deleteAccommodation(accommodationId)), HttpStatus.OK);
    }






}
