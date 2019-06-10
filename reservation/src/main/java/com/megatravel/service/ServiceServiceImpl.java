package com.megatravel.service;


import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.ServiceDTO;
import com.megatravel.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.megatravel.interfaces.ServiceInterface")
@Service
public class ServiceServiceImpl implements ServiceInterface {

    public static final String ENDPOINT = "/service";

    public ServiceServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public ServiceDTO createAccService(ServiceDTO serviceDTO) {
        return null;
    }

    @Override
    public ServiceDTO updateAccService(ServiceDTO serviceDTO) {
        return null;
    }

    @Override
    public ServiceDTO deleteAccService(Long serviceId) {
        return null;
    }

    @Override
    public ServiceDTO getAccService(Long serviceId) {
        return null;
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        return null;
    }
}
