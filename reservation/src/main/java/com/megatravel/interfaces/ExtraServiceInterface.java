package com.megatravel.interfaces;

import com.megatravel.dtos.agent.ExtraServiceDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ExtraServiceInterface {

    @WebMethod
    ExtraServiceDTO createAccService(@WebParam(name = "ExtraServiceDTO") ExtraServiceDTO extraServiceDTO);
    @WebMethod
    ExtraServiceDTO updateAccService(@WebParam(name = "ExtraServiceDTO") ExtraServiceDTO extraServiceDTO);
    @WebMethod
    ExtraServiceDTO deleteAccService(@WebParam(name = "ServiceId") Long serviceId);
    @WebMethod
    ExtraServiceDTO getAccService(@WebParam(name = "ServiceId") Long serviceId);
    @WebMethod
    List<ExtraServiceDTO> getAllServices();


}
