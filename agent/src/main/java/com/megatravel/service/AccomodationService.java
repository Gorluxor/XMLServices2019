package com.megatravel.service;

import com.megatravel.models.agent.Accommodation;
import com.megatravel.repository.AccomodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccomodationService {

    @Autowired
    private AccomodationRepository accomodationRepository;

    public Accommodation findById(Long  id){
        return accomodationRepository.findById(id).get();
    }

    public List<Accommodation> findAll(){
        return accomodationRepository.findAll();
    }

    public Accommodation save(Accommodation user){
        return accomodationRepository.save(user);
    }

    public void remove(Long id){accomodationRepository.deleteById(id);}


}
