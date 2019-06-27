package com.megatravel.controller;


import com.megatravel.dtos.agent.ExtraServiceDTO;

import com.megatravel.models.agent.ExtraService;
import com.megatravel.service.ExtraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/service")
@RestController
public class ExtraServiceController {

    @Autowired
    private ExtraServiceImpl extraService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ExtraServiceDTO> createService(@RequestBody ExtraServiceDTO extraServiceDTO){
        return new ResponseEntity<>(new ExtraServiceDTO(extraService.createAccService(extraServiceDTO)), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExtraServiceDTO> getService(@PathVariable Long id){
        return new ResponseEntity<>(new ExtraServiceDTO(extraService.getAccService(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ExtraServiceDTO>> getAllService(){
        List<ExtraService> list = extraService.getAllServices();
        List<ExtraServiceDTO> values = new ArrayList<>();
        for (ExtraService extraService : list){
            values.add(new ExtraServiceDTO(extraService));
        }
        return new ResponseEntity<>(values, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ExtraServiceDTO> updateService(@RequestBody ExtraServiceDTO extraServiceDTO){
        return new ResponseEntity<>(new ExtraServiceDTO(extraService.updateAccService(extraServiceDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ExtraServiceDTO> deleteService(@PathVariable Long id){
        return new ResponseEntity<>(new ExtraServiceDTO(extraService.deleteAccService(id)), HttpStatus.OK);
    }


}
