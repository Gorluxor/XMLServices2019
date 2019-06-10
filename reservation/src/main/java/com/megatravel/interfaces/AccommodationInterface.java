package com.megatravel.interfaces;

import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.models.agent.AccommodationUnit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface AccommodationInterface {

    @WebMethod
    List<AccommodationDTO> getAllAccommodations();
    @WebMethod
    AccommodationDTO getAccommodation(@WebParam(name = "AccId") Long accId);
    @WebMethod
    List<AccommodationUnitDTO> getAllUnits(@WebParam(name = "AccId") Long accId);
    @WebMethod
    AccommodationUnitDTO getUnit(@WebParam(name = "AccId") Long accId, @WebParam(name = "UnitId") Long unitId);
    @WebMethod
    AccommodationDTO createAccommodation(AccommodationDTO accommodationDTO);
    @WebMethod
    AccommodationUnitDTO createUnit(@WebParam(name = "AccId") Long accId, AccommodationUnitDTO accommodationUnitDTO);
    @WebMethod
    AccommodationDTO updateAccommodation(AccommodationDTO accommodationDTO);
    @WebMethod
    AccommodationUnitDTO updateUnit(@WebParam(name = "AccId") Long accId, AccommodationUnitDTO accommodationUnitDTO);
    @WebMethod
    AccommodationDTO deleteAccommodation(@WebParam(name = "AccId") Long accId);
    @WebMethod
    AccommodationUnitDTO deleteUnit(@WebParam(name = "AccId") Long accId, @WebParam(name = "UnitId") Long unitId);
}
