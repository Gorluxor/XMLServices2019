package com.megatravel.interfaces;


import com.megatravel.dtos.agent.AccommodationUnitDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface AccommodationUnitInterface {

    @WebMethod
    List<AccommodationUnitDTO> getAllUnits(@WebParam(name = "AccId") Long accId);
    @WebMethod
    AccommodationUnitDTO getUnit(@WebParam(name = "AccId") Long accId, @WebParam(name = "UnitId") Long unitId);
    @WebMethod
    AccommodationUnitDTO createUnit(@WebParam(name = "AccId") Long accId, AccommodationUnitDTO accommodationUnitDTO);
    @WebMethod
    AccommodationUnitDTO updateUnit(@WebParam(name = "AccId") Long accId, AccommodationUnitDTO accommodationUnitDTO);
    @WebMethod
    AccommodationUnitDTO deleteUnit(@WebParam(name = "AccId") Long accId, @WebParam(name = "UnitId") Long unitId);
}
