package com.megatravel.service;

import com.megatravel.dtos.agent.ServiceDTO;
import com.megatravel.dtos.agent.UnitTypeDTO;
import com.megatravel.interfaces.UnitTypeInterface;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "com.megatravel.interfaces.UnitTypeInterface")
@Service
public class UnitTypeServiceImpl implements UnitTypeInterface {

    public static final String ENDPOINT = "/unitType";

    @Override
    public UnitTypeDTO createService(UnitTypeDTO unitTypeDTO) {
        return null;
    }

    @Override
    public UnitTypeDTO updateService(UnitTypeDTO unitTypeDTO) {
        return null;
    }

    @Override
    public UnitTypeDTO deleteService(Long unitTypeId) {
        return null;
    }

    @Override
    public UnitTypeDTO getService(Long unitTypeId) {
        return null;
    }

    @Override
    public List<UnitTypeDTO> getAllServices() {
        return null;
    }
}
