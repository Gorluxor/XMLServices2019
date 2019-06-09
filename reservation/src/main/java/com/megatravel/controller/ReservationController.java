package com.megatravel.controller;

import com.megatravel.dtos.messages.MessageDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rsrv")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO){
        return new ResponseEntity<>(this.reservationService.createReservation(reservationDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.reservationService.cancelReservation(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> confirmReservation(@PathVariable("id") Long id){
        return new ResponseEntity<>(this.reservationService.confirmReservation(id), HttpStatus.OK);
    }

}