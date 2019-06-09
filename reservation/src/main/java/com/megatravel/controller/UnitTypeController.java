package com.megatravel.controller;



import com.megatravel.dtos.agent.UnitTypeDTO;
import com.megatravel.service.UnitTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unitType")
public class UnitTypeController {

    @Autowired
    private UnitTypeServiceImpl unitTypeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UnitTypeDTO> createService(@RequestBody UnitTypeDTO serviceDTO){
        return new ResponseEntity<UnitTypeDTO>(unitTypeService.createService(serviceDTO), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UnitTypeDTO> getService(@PathVariable Long id){
        return new ResponseEntity<UnitTypeDTO>(unitTypeService.getService(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UnitTypeDTO>> getAllService(){
        return new ResponseEntity<>(unitTypeService.getAllServices(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UnitTypeDTO> updateService(@RequestBody UnitTypeDTO serviceDTO){
        return new ResponseEntity<>(unitTypeService.updateService(serviceDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<UnitTypeDTO> deleteService(@PathVariable Long id){
        return new ResponseEntity<>(unitTypeService.deleteService(id), HttpStatus.OK);
    }




}
