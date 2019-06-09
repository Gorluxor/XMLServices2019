package com.megatravel.service;


import com.megatravel.dtos.agent.ServiceDTO;
import com.megatravel.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.megatravel.interfaces.ServiceInterface")
@Service
public class ServiceServiceImpl implements ServiceInterface {

    @Override
    public ServiceDTO createService(ServiceDTO serviceDTO) {
        return null;
    }

    @Override
    public ServiceDTO updateService(ServiceDTO serviceDTO) {
        return null;
    }

    @Override
    public ServiceDTO deleteService(Long serviceId) {
        return null;
    }

    @Override
    public ServiceDTO getService(Long serviceId) {
        return null;
    }

    @Override
    public List<ServiceDTO> getAllServices() {
        return null;
    }
}
