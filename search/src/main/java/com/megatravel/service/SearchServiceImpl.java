package com.megatravel.service;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.megatravel.interfaces.SearchService")
@Service
public class SearchServiceImpl implements com.megatravel.interfaces.SearchService {

    public static final String ENDPOINT = "/search";


    @Override
    public List<AccommodationDTO> normalSearch(AccommodationDTO accommodationDTO) {
        return null;
    }

    @Override
    public List<AccommodationDTO> advancedSearch(AccommodationDTO accommodationDTO) {
        return null;
    }
}