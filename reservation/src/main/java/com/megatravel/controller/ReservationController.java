package com.megatravel.controller;

import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.models.admin.User;
import com.megatravel.service.ReservationServiceImpl;
import com.megatravel.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/res")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO){
        return new ResponseEntity<>(new ReservationDTO(reservationService.createReservation(reservationDTO)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("id") Long id, HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        return new ResponseEntity<>(new ReservationDTO(reservationService.cancelReservation(id, email)), HttpStatus.OK);
    }



}
