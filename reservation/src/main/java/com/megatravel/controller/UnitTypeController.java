package com.megatravel.controller;

import com.megatravel.dtos.agent.UnitTypeDTO;
import com.megatravel.models.agent.UnitType;
import com.megatravel.service.UnitTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/unit")
public class UnitTypeController {

    @Autowired
    private UnitTypeImpl unitType;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UnitTypeDTO> createUnitType(@RequestBody UnitTypeDTO unitTypeDTO){
        return new ResponseEntity<>(new UnitTypeDTO(unitType.createAccUnitType(unitTypeDTO)), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UnitTypeDTO> getUnitType(@PathVariable Long id){
        return new ResponseEntity<>(new UnitTypeDTO(unitType.getAccUnitType(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UnitTypeDTO>> getAllUnitTypes(){
        List<UnitType> list = unitType.getAllUnitType();
        List<UnitTypeDTO> values = new ArrayList<>();
        for (UnitType extraService : list){
            values.add(new UnitTypeDTO(extraService));
        }
        return new ResponseEntity<>(values, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<UnitTypeDTO> updateUnitType(@RequestBody UnitTypeDTO extraServiceDTO){
        return new ResponseEntity<>(new UnitTypeDTO(unitType.updateAccUnitType(extraServiceDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<UnitTypeDTO> deleteService(@PathVariable Long id){
        return new ResponseEntity<>(new UnitTypeDTO(unitType.deleteAccUnitType(id)), HttpStatus.OK);
    }

}
