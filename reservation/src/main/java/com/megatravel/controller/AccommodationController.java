package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.service.AccommodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/acc")
@RestController
public class AccommodationController {

    @Autowired
    private AccommodationServiceImpl accommodationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(){
        return new ResponseEntity<>(accommodationService.getAllAccommodations(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(accommodationService.getAccommodation(accommodationId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationUnitDTO>> getAllUnits(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(accommodationService.getAllUnits(accommodationId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit/{id}",method = RequestMethod.GET)
    public ResponseEntity<AccommodationUnitDTO> getUnit(@PathVariable("accId") Long accommodationId, @PathVariable("id") Long id){
        return new ResponseEntity<>(accommodationService.getUnit(accommodationId, id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(accommodationService.createAccommodation(accommodationDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.POST)
    public ResponseEntity<AccommodationUnitDTO> createUnit(@PathVariable("accId") Long accommodationId, @RequestBody AccommodationUnitDTO accommodationUnitDTO){
        return new ResponseEntity<>(accommodationService.createUnit(accommodationId,accommodationUnitDTO), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(accommodationService.updateAccommodation(accommodationDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.PUT)
    public ResponseEntity<AccommodationUnitDTO> updateUnit(@PathVariable("accId") Long accommodationId, @RequestBody AccommodationUnitDTO accommodationUnitDTO){
        return new ResponseEntity<>(accommodationService.updateUnit(accommodationId, accommodationUnitDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationDTO> deleteAccommodation(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(accommodationService.deleteAccommodation(accommodationId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit/{unitId}", method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationUnitDTO> deleteAccommodation(@PathVariable("accId") Long accommodationId, @PathVariable("unitId") Long unitId){
        return new ResponseEntity<>(accommodationService.deleteUnit(accommodationId, unitId), HttpStatus.OK);
    }





}
