package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchServiceImpl searchService;

    @RequestMapping(value = "/normal", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationDTO>> normalSearch(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(this.searchService.normalSearch(accommodationDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/normal", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationDTO>> advancedSearch(@RequestBody AccommodationDTO accommodationDTO){
        return new ResponseEntity<>(this.searchService.normalSearch(accommodationDTO), HttpStatus.OK);
    }

}