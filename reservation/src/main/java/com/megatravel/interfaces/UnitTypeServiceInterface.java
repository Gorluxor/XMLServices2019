package com.megatravel.interfaces;


import com.megatravel.dtos.agent.UnitTypeDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface UnitTypeServiceInterface {

    @WebMethod
    UnitTypeDTO createAccUnitType(@WebParam(name = "UnitTypeDTO") UnitTypeDTO unitTypeDTO);
    @WebMethod
    UnitTypeDTO updateAccUnitType(@WebParam(name = "UnitTypeDTO") UnitTypeDTO unitTypeDTO);
    @WebMethod
    UnitTypeDTO deleteAccUnitType(@WebParam(name = "UnitTypeId") Long unitTypeId);
    @WebMethod
    UnitTypeDTO getAccUnitType(@WebParam(name = "UnitTypeId") Long unitTypeId);
    @WebMethod
    List<UnitTypeDTO> getAllUnitType();


}
