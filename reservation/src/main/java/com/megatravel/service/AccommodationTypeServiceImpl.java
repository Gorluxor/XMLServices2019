package com.megatravel.service;


import com.megatravel.dtos.agent.AccommodationTypeDTO;
import com.megatravel.interfaces.AccommodationTypeInterface;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.megatravel.interfaces.AccommodationTypeInterface")
@Service
public class AccommodationTypeServiceImpl implements AccommodationTypeInterface {

    public static final String ENDPOINT = "/accType";

    @Override
    public AccommodationTypeDTO createService(AccommodationTypeDTO accommodationTypeDTO) {
        return null;
    }

    @Override
    public AccommodationTypeDTO updateService(AccommodationTypeDTO serviceDTO) {
        return null;
    }

    @Override
    public AccommodationTypeDTO deleteService(Long accommodationTypeId) {
        return null;
    }

    @Override
    public AccommodationTypeDTO getService(Long accommodationTypeId) {
        return null;
    }

    @Override
    public List<AccommodationTypeDTO> getAllServices() {
        return null;
    }
}
