package com.megatravel.service;


import com.megatravel.dtos.agent.AccommodationTypeDTO;
import com.megatravel.models.agent.AccommodationType;
import com.megatravel.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;


@Service
public class AccommodationTypeServiceImpl {

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    public AccommodationType createAccType(AccommodationTypeDTO typeDTO) throws ResponseStatusException {
        if (typeDTO == null){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccTypeDTO");
        }
        AccommodationType accommodationType = new AccommodationType(typeDTO);
        accommodationType.setLastChangedDate(new Date());
        accommodationTypeRepository.save(accommodationType);
        return accommodationType;
    }

    public AccommodationType updateAccType(AccommodationTypeDTO typeDTO) throws ResponseStatusException  {
        if (typeDTO == null || typeDTO.getId() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No AccTypeDTO");
        }
        AccommodationType accommodationType = accommodationTypeRepository.getOne(typeDTO.getId());
        accommodationType.setLastChangedDate(new Date());
        accommodationType.setNameOfAccType(typeDTO.getNameOfAccType());
        accommodationTypeRepository.save(accommodationType);
        return accommodationType;
    }

    public AccommodationType deleteAccType(Long accommodationTypeId) {
        AccommodationType accommodationType = accommodationTypeRepository.getOne(accommodationTypeId);
        accommodationTypeRepository.delete(accommodationType);
        return accommodationType;
    }

    public AccommodationType getAccType(Long accommodationTypeId) {
        return accommodationTypeRepository.getOne(accommodationTypeId);
    }

    public List<AccommodationType> getAllAccType() {
        return accommodationTypeRepository.findAll();
    }
}
