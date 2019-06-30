package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.AccommodationTypeDTO;
import com.megatravel.interfaces.AccommodationTypeInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;

@WebService(portName = "AccommodationTypePort",
            serviceName = "AccommodationTypeServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.AccommodationTypeInterface")
@Service
public class WebAccommodationTypeServiceImpl implements AccommodationTypeInterface {

    public static final String ENDPOINT = "/services/accType";

    public WebAccommodationTypeServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public AccommodationTypeDTO createAccType(AccommodationTypeDTO accommodationTypeDTO) {
        return null;
    }

    @Override
    public AccommodationTypeDTO updateAccType(AccommodationTypeDTO serviceDTO) {
        return null;
    }

    @Override
    public AccommodationTypeDTO deleteAccType(Long accommodationTypeId) {
        return null;
    }

    @Override
    public AccommodationTypeDTO getAccType(Long accommodationTypeId) {
        return null;
    }

    @Override
    public List<AccommodationTypeDTO> getAllAccType() {
        return null;
    }
}
