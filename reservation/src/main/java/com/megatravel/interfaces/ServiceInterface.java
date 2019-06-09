package com.megatravel.interfaces;

import com.megatravel.dtos.agent.ServiceDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface ServiceInterface {

    @WebMethod
    ServiceDTO createService(@XmlElement(name = "serviceDTO", required = true) ServiceDTO serviceDTO);
    @WebMethod
    ServiceDTO updateService(@XmlElement(name = "serviceDTO", required = true) ServiceDTO serviceDTO);
    @WebMethod
    ServiceDTO deleteService(@XmlElement(name = "serviceId", required = true) Long serviceId);
    @WebMethod
    ServiceDTO getService(@XmlElement(name = "serviceId", required = true) Long serviceId);
    @WebMethod
    List<ServiceDTO> getAllServices();


}
