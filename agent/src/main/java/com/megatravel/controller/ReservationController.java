package com.megatravel.controller;

import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.service.ReservationServiceImpl;
import com.megatravel.webservice.WebReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ReservationDTO> confirmReservation(@PathVariable("id") Long id){
        return new ResponseEntity<>(new ReservationDTO(reservationService.confirmReservation(id)), HttpStatus.OK);
    }
}
