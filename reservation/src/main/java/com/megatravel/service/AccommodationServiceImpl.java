package com.megatravel.service;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.interfaces.AccommodationInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationInterface {

    @Override
    public List<AccommodationDTO> getAllAccommodations() {
        return null;
    }

    @Override
    public AccommodationDTO getAccommodation(Long accId) {
        return null;
    }

    @Override
    public List<AccommodationUnitDTO> getAllUnits(Long accId) {
        return null;
    }

    @Override
    public AccommodationUnitDTO getUnit(Long accId, Long unitId) {
        return null;
    }

    @Override
    public AccommodationDTO createAccommodation(AccommodationDTO accommodationDTO) {
        return null;
    }

    @Override
    public AccommodationUnitDTO createUnit(Long accId, AccommodationUnitDTO accommodationUnitDTO) {
        return null;
    }

    @Override
    public AccommodationDTO updateAccommodation(AccommodationDTO accommodationDTO) {
        return null;
    }

    @Override
    public AccommodationUnitDTO updateUnit(Long accId, AccommodationUnitDTO accommodationUnitDTO) {
        return null;
    }

    @Override
    public AccommodationDTO deleteAccommodation(Long accId) {
        return null;
    }

    @Override
    public AccommodationUnitDTO deleteUnit(Long accId, Long unitId) {
        return null;
    }
}
