package com.megatravel.service;

import com.megatravel.dtos.agent.UnitTypeDTO;
import com.megatravel.models.agent.UnitType;
import com.megatravel.repository.UnitTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;



@Service
public class UnitTypeImpl {

    @Autowired
    private UnitTypeRepository unitTypeRepository;

    public UnitType createAccUnitType(UnitTypeDTO unitTypeDTO) {
        if (unitTypeDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UnitType null");
        }
        UnitType unitType = new UnitType(unitTypeDTO);
        unitType.setLastChangedDate(new Date());
        unitTypeRepository.save(unitType);
        return unitType;
    }


    public UnitType updateAccUnitType(UnitTypeDTO unitTypeDTO) {
        if (unitTypeDTO == null || unitTypeDTO.getId() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UnitType null or no ID");
        }
        UnitType unitType = unitTypeRepository.getOne(unitTypeDTO.getId());
        unitType.setLastChangedDate(new Date());
        unitType.setNameOfUnitType(unitTypeDTO.getNameOfUnitType());
        unitTypeRepository.save(unitType);
        return unitType;
    }


    public UnitType deleteAccUnitType(Long unitTypeId) {
        UnitType unitType = unitTypeRepository.getOne(unitTypeId);
        unitTypeRepository.delete(unitType);
        return unitType;
    }


    public UnitType getAccUnitType(Long unitTypeId) {
       return unitTypeRepository.getOne(unitTypeId);
    }


    public List<UnitType> getAllUnitType() {
        return unitTypeRepository.findAll();
    }
}
