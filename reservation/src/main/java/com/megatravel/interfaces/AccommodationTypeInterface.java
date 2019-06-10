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
    AccommodationTypeDTO createService(@WebParam(name = "AccommodationTypeDTO") AccommodationTypeDTO accommodationTypeDTO);
    @WebMethod
    AccommodationTypeDTO updateService(@WebParam(name = "AccommodationTypeDTO") AccommodationTypeDTO serviceDTO);
    @WebMethod
    AccommodationTypeDTO deleteService(@WebParam(name = "AccommodationTypeId") Long accommodationTypeId);
    @WebMethod
    AccommodationTypeDTO getService(@WebParam(name = "AccommodationTypeId") Long accommodationTypeId);
    @WebMethod
    List<AccommodationTypeDTO> getAllServices();




}
