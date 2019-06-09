package com.megatravel.interfaces;



import com.megatravel.dtos.agent.UnitTypeDTO;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@WebService
public interface UnitTypeInterface {

    @WebMethod
    UnitTypeDTO createService(@XmlElement(name = "unitTypeDTO", required = true) UnitTypeDTO unitTypeDTO);
    @WebMethod
    UnitTypeDTO updateService(@XmlElement(name = "unitTypeDTO", required = true) UnitTypeDTO unitTypeDTO);
    @WebMethod
    UnitTypeDTO deleteService(@XmlElement(name = "unitTypeId", required = true) Long unitTypeId);
    @WebMethod
    UnitTypeDTO getService(@XmlElement(name = "unitTypeId", required = true) Long unitTypeId);
    @WebMethod
    List<UnitTypeDTO> getAllServices();


}
