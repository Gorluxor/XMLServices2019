package com.megatravel.controller;


import com.megatravel.dtos.agent.AccommodationTypeDTO;
import com.megatravel.models.agent.AccommodationType;
import com.megatravel.service.AccommodationTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/type")
public class AccommodationTypeController {

    @Autowired
    private AccommodationTypeServiceImpl accommodationTypeService;

    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationTypeDTO> createAccommodationType(@RequestBody AccommodationTypeDTO accommodationTypeDTO){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.createAccType(accommodationTypeDTO)), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationTypeDTO> getAccommodationType(@PathVariable Long id){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.getAccType(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationTypeDTO>> getAllAccommodationTypes(){
        List<AccommodationType> list = accommodationTypeService.getAllAccType();
        List<AccommodationTypeDTO> values = new ArrayList<>();

        for (AccommodationType accommodationType : list){
            values.add(new AccommodationTypeDTO(accommodationType));
        }

        return new ResponseEntity<>(values, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT,  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationTypeDTO> updateAccommodationType(@RequestBody AccommodationTypeDTO accommodationTypeDTO){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.updateAccType(accommodationTypeDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationTypeDTO> deleteAccommodationType(@PathVariable Long id){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.deleteAccType(id)), HttpStatus.OK);
    }

}
