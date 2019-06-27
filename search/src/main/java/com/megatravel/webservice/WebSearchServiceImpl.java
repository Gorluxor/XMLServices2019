package com.megatravel.webservice;


import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.AccommodationDTO;
import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.reservations.SearchDTO;
import com.megatravel.interfaces.SearchInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.megatravel.interfaces.SearchInterface")
@Component
public class WebSearchServiceImpl implements SearchInterface {

    public static final String ENDPOINT = "/search";


    public WebSearchServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public List<AccommodationDTO> normalSearchAccommodation(SearchDTO searchDTO) {
        return null;
    }

    @Override
    public List<AccommodationDTO> advanceSearchAccommodation(SearchDTO searchDTO) {
        return null;
    }

    @Override
    public List<AccommodationUnitDTO> normalSearchAccommodationUnit(SearchDTO searchDTO) {
        return null;
    }

    @Override
    public List<AccommodationUnitDTO> advanceSearchAccommodationUnit(SearchDTO searchDTO) {
        return null;
    }
}
