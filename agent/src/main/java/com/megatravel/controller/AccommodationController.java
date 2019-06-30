package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.models.agent.Accommodation;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.service.AccommodationServiceImpl;
import com.megatravel.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/acc")
@RestController
public class AccommodationController {

    @Autowired
    private AccommodationServiceImpl accommodationService;



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations(){
        List<Accommodation> list = accommodationService.getAllAccommodations();
        return new ResponseEntity<>(convertToAccDTO(list), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.GET)
    public ResponseEntity<AccommodationDTO> getAccommodation(@PathVariable("accId") Long accommodationId){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.getAccommodation(accommodationId)), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.createAccommodation(accommodationDTO)), HttpStatus.CREATED);
    }


    @Deprecated
    @RequestMapping(method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationDTO> updateAccommodation(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(new AccommodationDTO(accommodationService.updateAccommodation(accommodationDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAccommodation(@PathVariable("accId") Long accommodationId){
        accommodationService.deleteAccommodation(accommodationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("This is a hello from Reservation", HttpStatus.OK);
    }

    @RequestMapping(value = "/accAgent", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationDTO>> getAgentAccommodations(HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        return new ResponseEntity<>(convertToAccDTO(accommodationService.getAccommodationForAgent(email)), HttpStatus.OK);
    } // should be only one, but can still by database

    private List<AccommodationDTO> convertToAccDTO(List<Accommodation> list){
        List<AccommodationDTO> values = new ArrayList<>();
        for (Accommodation accommodation : list){
            values.add(new AccommodationDTO(accommodation));
        }
        return values;
    }
}
