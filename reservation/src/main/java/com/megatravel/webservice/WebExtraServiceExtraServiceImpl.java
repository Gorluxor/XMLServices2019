package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.ExtraServiceDTO;
import com.megatravel.interfaces.ExtraServiceInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;

@WebService(portName = "ExtraServicePort",
            serviceName = "ExtraServiceServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.ExtraServiceInterface")
@Service
public class WebExtraServiceExtraServiceImpl implements ExtraServiceInterface {

    public static final String ENDPOINT = "/services/extraservice";

    public WebExtraServiceExtraServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }


    @Override
    public ExtraServiceDTO createAccService(ExtraServiceDTO extraServiceDTO) {
        return null;
    }

    @Override
    public ExtraServiceDTO updateAccService(ExtraServiceDTO extraServiceDTO) {
        return null;
    }

    @Override
    public ExtraServiceDTO deleteAccService(Long serviceId) {
        return null;
    }

    @Override
    public ExtraServiceDTO getAccService(Long serviceId) {
        return null;
    }

    @Override
    public List<ExtraServiceDTO> getAllServices() {
        return null;
    }
}
