package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.dtos.reservations.SearchDTO;
import com.megatravel.models.agent.Accommodation;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchServiceImpl searchService;

    @RequestMapping(value = "/accommodation/normal", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationDTO>> normalSearchAccommodation(@RequestBody SearchDTO searchDTO){
        return new ResponseEntity<>(convertToAccommodationListDTO(this.searchService.normalSearchAccommodation(searchDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/accommodation/advanced", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationDTO>> advancedSearchAccommodation(@RequestBody SearchDTO searchDTO){
        return new ResponseEntity<>(convertToAccommodationListDTO(searchService.advanceSearchAccommodation(searchDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/accommodation/{id}/normal", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationUnitDTO>> normalSearchAccommodationUnit(@RequestBody SearchDTO searchDTO, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(convertToAccommodationUnitListDTO(searchService.normalSearchAccommodationUnit(searchDTO, id)), HttpStatus.OK);
    }

    @Deprecated
    @RequestMapping(value = "/accommodation/{id}/advanced", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationUnitDTO>> advancedSearchAccommodationUnit(@RequestBody SearchDTO searchDTO, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(convertToAccommodationUnitListDTO(searchService.advanceSearchAccommodationUnit(searchDTO, id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("This is a test", HttpStatus.OK);
    }


    private List<AccommodationDTO> convertToAccommodationListDTO(List<Accommodation> list){
        List<AccommodationDTO> values = new ArrayList<>();
        if (list != null){
            for (Accommodation accommodation : list){
                values.add(new AccommodationDTO(accommodation));
            }
        }
        return values;
    }

    private List<AccommodationUnitDTO> convertToAccommodationUnitListDTO(List<AccommodationUnit> list){
        List<AccommodationUnitDTO> values = new ArrayList<>();
        if (list != null){
            for (AccommodationUnit accommodationUnit : list){
                values.add(new AccommodationUnitDTO(accommodationUnit));
            }
        }
        return values;
    }

}
