package com.megatravel.interfaces;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.reservations.ReservationDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SearchService {


    @WebMethod
    List<AccommodationDTO> normalSearch(@WebParam(name = "accommodationDTO") AccommodationDTO accommodationDTO);

    @WebMethod
    List<AccommodationDTO> advancedSearch(@WebParam(name = "accommodationDTO") AccommodationDTO accommodationDTO);

}
