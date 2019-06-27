package com.megatravel.interfaces;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.dtos.reservations.SearchDTO;
import io.micrometer.core.instrument.search.Search;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface SearchInterface {


    @WebMethod
    List<AccommodationDTO> normalSearchAccommodation(@WebParam(name = "searchDTO") SearchDTO searchDTO);

    @WebMethod
    List<AccommodationDTO> advanceSearchAccommodation(@WebParam(name = "searchDTO") SearchDTO searchDTO);


    @WebMethod
    List<AccommodationUnitDTO> normalSearchAccommodationUnit(@WebParam(name = "searchDTO") SearchDTO searchDTO);


    @WebMethod
    List<AccommodationUnitDTO> advanceSearchAccommodationUnit(@WebParam(name = "searchDTO") SearchDTO searchDTO);

}
