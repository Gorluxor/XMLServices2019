package com.megatravel.controller;

import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.repository.UserRepository;
import com.megatravel.service.AccommodationUnitServiceImpl;
import com.megatravel.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RequestMapping(value = "/acc")
@RestController
public class AccommodationUnitController {

    @Autowired
    private AccommodationUnitServiceImpl accommodationService;


    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationUnitDTO>> getAllUnits(@PathVariable("accId") Long accommodationId){
        List<AccommodationUnit> list = accommodationService.getAllUnits(accommodationId);
        return new ResponseEntity<>(convertToDTOAccUnit(list), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationUnitDTO> getUnit(@PathVariable("accId") Long accommodationId, @PathVariable("id") Long id){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.getUnit(accommodationId, id)), HttpStatus.OK);
    }


    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationUnitDTO> createUnit(@PathVariable("accId") Long accommodationId, @RequestBody AccommodationUnitDTO accommodationUnitDTO){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.createUnit(accommodationId,accommodationUnitDTO)), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{accId}/unit",method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccommodationUnitDTO> updateUnit(@PathVariable("accId") Long accommodationId, @RequestBody AccommodationUnitDTO accommodationUnitDTO){
        return new ResponseEntity<>(new AccommodationUnitDTO(accommodationService.updateUnit(accommodationId, accommodationUnitDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{accId}/unit/{unitId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUnit(@PathVariable("accId") Long accommodationId, @PathVariable("unitId") Long unitId){
        accommodationService.deleteUnit(accommodationId, unitId);
        return new ResponseEntity<>("Removed unit", HttpStatus.OK);
    }

    @RequestMapping(value = "/agent", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccommodationUnitDTO>> agentUnits(HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        List<AccommodationUnit> accommodationUnits = accommodationService.getAgentUnits(email);
        return new ResponseEntity<>(convertToDTOAccUnit(accommodationUnits), HttpStatus.OK);
    }

    @SuppressWarnings("Duplicates")
    private List<AccommodationUnitDTO> convertToDTOAccUnit(List<AccommodationUnit> list){
        List<AccommodationUnitDTO> values = new ArrayList<>();
        if (list != null){
            for (AccommodationUnit accommodationUnit : list){
                values.add(new AccommodationUnitDTO(accommodationUnit));
            }
        }
        return values;
    }

}
