package com.megatravel.controller;


import com.megatravel.dtos.agent.AccommodationTypeDTO;
import com.megatravel.models.agent.AccommodationType;
import com.megatravel.service.AccommodationTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/type")
public class AccommodationTypeController {

    @Autowired
    private AccommodationTypeServiceImpl accommodationTypeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccommodationTypeDTO> createService(@RequestBody AccommodationTypeDTO accommodationTypeDTO){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.createAccType(accommodationTypeDTO)), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationTypeDTO> getService(@PathVariable Long id){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.getAccType(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationTypeDTO>> getAllService(){
        List<AccommodationType> list = accommodationTypeService.getAllAccType();
        List<AccommodationTypeDTO> values = new ArrayList<>();

        for (AccommodationType accommodationType : list){
            values.add(new AccommodationTypeDTO(accommodationType));
        }

        return new ResponseEntity<>(values, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AccommodationTypeDTO> updateService(@RequestBody AccommodationTypeDTO accommodationTypeDTO){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.updateAccType(accommodationTypeDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationTypeDTO> deleteService(@PathVariable Long id){
        return new ResponseEntity<>(new AccommodationTypeDTO(accommodationTypeService.deleteAccType(id)), HttpStatus.OK);
    }

}
