package com.megatravel.controller;

import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.service.ReservationServiceImpl;
import com.megatravel.utils.TokenUtils;
import com.megatravel.webservice.WebReservationServiceImpl;
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

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> confirmReservation(@PathVariable("id") Long id, HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        return new ResponseEntity<>(new ReservationDTO(reservationService.confirmReservation(id, email)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO, HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        return new ResponseEntity<>(new ReservationDTO(reservationService.createReservation(reservationDTO, email)), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> cancelReservation(@PathVariable("id") Long id, HttpServletRequest request){
        String email = TokenUtils.getUsername(request.getHeader("Authorization"));
        reservationService.cancelReservation(id, email);
        return new ResponseEntity<>("Canceled the reservation", HttpStatus.OK);
    }

}
