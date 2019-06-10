package com.megatravel.interfaces;

import com.megatravel.dtos.agent.ServiceDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface ServiceInterface {

    @WebMethod
    ServiceDTO createAccService(@WebParam(name = "ServiceDTO") ServiceDTO serviceDTO);
    @WebMethod
    ServiceDTO updateAccService(@WebParam(name = "ServiceDTO") ServiceDTO serviceDTO);
    @WebMethod
    ServiceDTO deleteAccService(@WebParam(name = "ServiceId") Long serviceId);
    @WebMethod
    ServiceDTO getAccService(@WebParam(name = "ServiceId") Long serviceId);
    @WebMethod
    List<ServiceDTO> getAllServices();


}
