package com.megatravel.controller;


import com.megatravel.dtos.agent.AccommodationTypeDTO;
import com.megatravel.service.AccommodationTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class AccommodationTypeController {

    @Autowired
    private AccommodationTypeServiceImpl accommodationTypeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccommodationTypeDTO> createService(@RequestBody AccommodationTypeDTO accommodationTypeDTO){
        return new ResponseEntity<AccommodationTypeDTO>(accommodationTypeService.createService(accommodationTypeDTO), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationTypeDTO> getService(@PathVariable Long id){
        return new ResponseEntity<AccommodationTypeDTO>(accommodationTypeService.getService(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationTypeDTO>> getAllService(){
        return new ResponseEntity<>(accommodationTypeService.getAllServices(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<AccommodationTypeDTO> updateService(@RequestBody AccommodationTypeDTO accommodationTypeDTO){
        return new ResponseEntity<>(accommodationTypeService.updateService(accommodationTypeDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<AccommodationTypeDTO> deleteService(@PathVariable Long id){
        return new ResponseEntity<>(accommodationTypeService.deleteService(id), HttpStatus.OK);
    }

}
