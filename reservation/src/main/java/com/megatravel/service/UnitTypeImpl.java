package com.megatravel.service;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.ServiceDTO;
import com.megatravel.dtos.agent.UnitTypeDTO;
import com.megatravel.interfaces.UnitTypeServiceInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;


@WebService(endpointInterface = "com.megatravel.interfaces.UnitTypeServiceInterface")
@Service
public class UnitTypeImpl implements UnitTypeServiceInterface {

    public static final String ENDPOINT = "/unitType";

    public UnitTypeImpl() {
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
