package com.megatravel.interfaces;


import com.megatravel.dtos.agent.AccommodationTypeDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface AccommodationTypeInterface {


    @WebMethod
    AccommodationTypeDTO createAccType(@WebParam(name = "AccommodationTypeDTO") AccommodationTypeDTO accommodationTypeDTO);
    @WebMethod
    AccommodationTypeDTO updateAccType(@WebParam(name = "AccommodationTypeDTO") AccommodationTypeDTO serviceDTO);
    @WebMethod
    AccommodationTypeDTO deleteAccType(@WebParam(name = "AccommodationTypeId") Long accommodationTypeId);
    @WebMethod
    AccommodationTypeDTO getAccType(@WebParam(name = "AccommodationTypeId") Long accommodationTypeId);
    @WebMethod
    List<AccommodationTypeDTO> getAllAccType();




}
