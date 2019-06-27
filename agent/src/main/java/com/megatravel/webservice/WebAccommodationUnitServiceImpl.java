package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.interfaces.AccommodationUnitInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;




@WebService(portName="AccommodationUnitPort",
            serviceName="AccommodationUnitServiceInterface",
            targetNamespace="http://interfaces.megatravel.com/",
            endpointInterface = "com.megatravel.interfaces.AccommodationUnitInterface")
@Service
public class WebAccommodationUnitServiceImpl implements AccommodationUnitInterface {


    public static final String ENDPOINT = "/unit";

    public WebAccommodationUnitServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public List<AccommodationUnitDTO> getAllUnits(Long accId) {
        return null;
    }

    @Override
    public AccommodationUnitDTO getUnit(Long accId, Long unitId) {
        return null;
    }

    @Override
    public AccommodationUnitDTO createUnit(Long accId, AccommodationUnitDTO accommodationUnitDTO) {
        return null;
    }

    @Override
    public AccommodationUnitDTO updateUnit(Long accId, AccommodationUnitDTO accommodationUnitDTO) {
        return null;
    }

    @Override
    public AccommodationUnitDTO deleteUnit(Long accId, Long unitId) {
        return null;
    }
}
