package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.service.AccommodationUnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/acc")
@RestController
public class AccommodationUnitController {

    @Autowired
    private AccommodationUnitServiceImpl accommodationService;


    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationUnitDTO>> getAllUnits(@PathVariable("accId") Long accommodationId){
        List<AccommodationUnit> list = accommodationService.getAllUnits(accommodationId);
        List<AccommodationUnitDTO> values = new ArrayList<>();
        for (AccommodationUnit accommodationUnit : list){
            values.add(new AccommodationUnitDTO(accommodationUnit));
        }
        return new ResponseEntity<>(values, HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit/{id}",method = RequestMethod.GET)
    public ResponseEntity<AccommodationUnitDTO> getUnit(@PathVariable("accId") Long accommodationId, @PathVariable("id") Long id){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.getUnit(accommodationId, id)), HttpStatus.OK);
    }


    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.POST)
    public ResponseEntity<AccommodationUnitDTO> createUnit(@PathVariable("accId") Long accommodationId, @RequestBody AccommodationUnitDTO accommodationUnitDTO){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.createUnit(accommodationId,accommodationUnitDTO)), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.PUT)
    public ResponseEntity<AccommodationUnitDTO> updateUnit(@PathVariable("accId") Long accommodationId, @RequestBody AccommodationUnitDTO accommodationUnitDTO){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.updateUnit(accommodationId, accommodationUnitDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit/{unitId}", method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationUnitDTO> deleteUnit(@PathVariable("accId") Long accommodationId, @PathVariable("unitId") Long unitId){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.deleteUnit(accommodationId, unitId)), HttpStatus.OK);
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("This is a hello from Agent microservice!", HttpStatus.OK);
    }


}
