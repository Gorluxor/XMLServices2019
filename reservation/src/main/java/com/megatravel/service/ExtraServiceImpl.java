package com.megatravel.service;


import com.megatravel.dtos.agent.ExtraServiceDTO;
import com.megatravel.models.agent.ExtraService;
import com.megatravel.repository.ExtraServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Date;
import java.util.List;


@Service
public class ExtraServiceImpl {

    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    public ExtraService createAccService(ExtraServiceDTO extraServiceDTO) {
        if (extraServiceDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No extra service");
        }
        ExtraService extraService = new ExtraService(extraServiceDTO);
        extraService.setLastChangedDate(new Date());
        extraServiceRepository.save(extraService);
        return extraService;
    }


    public ExtraService updateAccService(ExtraServiceDTO extraServiceDTO) {
        if (extraServiceDTO == null || extraServiceDTO.getId() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No extra service");
        }
        ExtraService extraService = extraServiceRepository.getOne(extraServiceDTO.getId());
        extraService.setLastChangedDate(new Date());
        extraService.setDescription(extraServiceDTO.getDescription());
        extraService.setNameOfService(extraServiceDTO.getNameOfService());
        extraServiceRepository.save(extraService);
        return extraService;
    }


    public ExtraService deleteAccService(Long serviceId) {
        ExtraService extraService = extraServiceRepository.getOne(serviceId);
        extraServiceRepository.delete(extraService);
        return extraService;
    }


    public ExtraService getAccService(Long serviceId) {
        ExtraService extraService = extraServiceRepository.getOne(serviceId);
        return extraService;
    }


    public List<ExtraService> getAllServices() {
        return extraServiceRepository.findAll();
    }
}
