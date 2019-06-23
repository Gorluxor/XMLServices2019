package com.megatravel.webservice;


import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.UnitTypeDTO;
import com.megatravel.interfaces.UnitTypeInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;

@WebService(portName = "UnitTypePort",
            serviceName = "UnitTypeServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.UnitTypeInterface")
@Service
public class WebUnitTypeServiceImpl implements UnitTypeInterface {

    public static final String ENDPOINT = "/unitType";

    public WebUnitTypeServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }


    @Override
    public UnitTypeDTO createAccUnitType(UnitTypeDTO unitTypeDTO) {
        return null;
    }

    @Override
    public UnitTypeDTO updateAccUnitType(UnitTypeDTO unitTypeDTO) {
        return null;
    }

    @Override
    public UnitTypeDTO deleteAccUnitType(Long unitTypeId) {
        return null;
    }

    @Override
    public UnitTypeDTO getAccUnitType(Long unitTypeId) {
        return null;
    }

    @Override
    public List<UnitTypeDTO> getAllUnitType() {
        return null;
    }
}
