package com.megatravel.controller;


import com.megatravel.dtos.agent.ServiceDTO;

import com.megatravel.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/service")
@RestController
public class ServiceController {

    @Autowired
    private ServiceServiceImpl serviceService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO){
        return new ResponseEntity<ServiceDTO>(serviceService.createAccService(serviceDTO), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceDTO> getService(@PathVariable Long id){
        return new ResponseEntity<ServiceDTO>(serviceService.getAccService(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ServiceDTO>> getAllService(){
        return new ResponseEntity<>(serviceService.getAllServices(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ServiceDTO> updateService(@RequestBody ServiceDTO serviceDTO){
        return new ResponseEntity<>(serviceService.updateAccService(serviceDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ServiceDTO> deleteService(@PathVariable Long id){
        return new ResponseEntity<>(serviceService.deleteAccService(id), HttpStatus.OK);
    }


}
