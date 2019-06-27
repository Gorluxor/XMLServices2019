package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.interfaces.AccommodationInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;


@WebService(portName = "AccommodationPort",
            serviceName = "AccommodationServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.AccommodationInterface")
@Service
@SOAPBinding(style=SOAPBinding.Style.RPC, parameterStyle=SOAPBinding.ParameterStyle.WRAPPED, use= SOAPBinding.Use.LITERAL)
public class WebAccommodationServiceImpl implements AccommodationInterface {

    public static final String ENDPOINT = "/accommodation";

    public WebAccommodationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public List<AccommodationDTO> getAllAccommodations() {
        return null;
    }

    @Override
    public AccommodationDTO getAccommodation(Long accId) {
        return null;
    }

    @Override
    public AccommodationDTO createAccommodation(AccommodationDTO accommodationDTO) {
        return null;
    }


    @Override
    public AccommodationDTO updateAccommodation(AccommodationDTO accommodationDTO) {
        return null;
    }


    @Override
    public AccommodationDTO deleteAccommodation(Long accId) {
        return null;
    }


}
