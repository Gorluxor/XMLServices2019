package com.megatravel.service;

import com.megatravel.dtos.agent.*;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.*;
import com.megatravel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class AccommodationServiceImpl {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;


    @Autowired
    private LocationRepository locationRepository;

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }


    public Accommodation getAccommodation(Long accId) {
        return accommodationRepository.getOne(accId);
    }


    public Accommodation createAccommodation(AccommodationDTO accommodationDTO) {

        if (accommodationDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such object in database");
        }

        Accommodation accommodation = new Accommodation(accommodationDTO);

        if (accommodation.getUser() == null || accommodation.getAccommodationType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such object in database");
        }
        accommodation.setExtraService(new ArrayList<>()); // admin doesn't add which extra services does the user have

        locationRepository.save(accommodation.getLocation());

        accommodation.setUser(userRepository.getOne(accommodation.getUser().getId())); // one of the available agents
        accommodation.setAccommodationType(accommodationTypeRepository.getOne(accommodation.getAccommodationType().getId()));

        accommodation.setAccommodationUnit(new ArrayList<>());
        accommodationRepository.save(accommodation);

        return accommodation;
    }


    public Accommodation updateAccommodation(AccommodationDTO accommodationDTO) {
        if (accommodationDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such object in database");
        }
        Accommodation accommodation = accommodationRepository.getOne(accommodationDTO.getId());

        if (accommodation.getUser() == null || accommodation.getAccommodationType() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No info");
        }

        accommodation.setUser(userRepository.getOne(accommodation.getUser().getId())); // one of the available agents
        accommodation.setAccommodationType(accommodationTypeRepository.getOne(accommodation.getAccommodationType().getId()));

        accommodation.setLastChangedDate(new Date());

        accommodationRepository.save(accommodation);
        return accommodation;
    }


    public Accommodation deleteAccommodation(Long accId) {

        Accommodation accommodation = accommodationRepository.getOne(accId);

        accommodation.setExtraService(null);
        accommodation.setAccommodationType(null);
        accommodation.setUser(null);

        if (accommodation.getAccommodationUnit() != null) {
            for (AccommodationUnit a : accommodation.getAccommodationUnit()) {
                a.setUnitType(null);
            }
        }

        accommodationRepository.delete(accommodation);
        return accommodation;
    }


}
