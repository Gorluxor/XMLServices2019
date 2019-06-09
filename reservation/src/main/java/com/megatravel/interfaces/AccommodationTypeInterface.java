package com.megatravel.interfaces;


import com.megatravel.dtos.agent.AccommodationTypeDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface AccommodationTypeInterface {


    @WebMethod
    AccommodationTypeDTO createService(@XmlElement(name = "accommodationTypeDTO", required = true) AccommodationTypeDTO accommodationTypeDTO);
    @WebMethod
    AccommodationTypeDTO updateService(@XmlElement(name = "accommodationTypeDTO", required = true) AccommodationTypeDTO serviceDTO);
    @WebMethod
    AccommodationTypeDTO deleteService(@XmlElement(name = "accommodationTypeId", required = true) Long accommodationTypeId);
    @WebMethod
    AccommodationTypeDTO getService(@XmlElement(name = "accommodationTypeId", required = true) Long accommodationTypeId);
    @WebMethod
    List<AccommodationTypeDTO> getAllServices();




}
