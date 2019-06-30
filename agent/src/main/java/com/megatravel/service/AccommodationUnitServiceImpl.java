package com.megatravel.service;

import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.agent.ExtraServiceDTO;
import com.megatravel.dtos.agent.PricingDTO;
import com.megatravel.models.admin.User;
import com.megatravel.models.agent.*;
import com.megatravel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccommodationUnitServiceImpl {


    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private UnitTypeRepository unitTypeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;

    @Autowired
    private UserRepository userRepository;

    public AccommodationUnit createUnit(Long accId, AccommodationUnitDTO accommodationUnitDTO) throws ResponseStatusException {

        if (accommodationUnitDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such object in database");
        }

        AccommodationUnit unit = new AccommodationUnit(accommodationUnitDTO);
        Accommodation accommodation = accommodationRepository.getOne(accId);

        if (accommodation.getAccommodationUnit() == null){
            accommodation.setAccommodationUnit(new ArrayList<>());
        }

        if (unit.getUnitType() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No type");
        }


        UnitType unitType = unitTypeRepository.getOne(unit.getUnitType().getId());

        unit.setUnitType(unitType);

        unit.setAccommodation(accommodation);
        unit.setLastChangedDate(new Date());
        locationRepository.save(unit.getLocation());

        accommodationUnitRepository.save(unit);

        for (Image image : unit.getImages()){
            image.setBelongsToAccommodationUnit(unit); // rest set in new AccommodationUnit
            imageRepository.save(image);
        }

        List<ExtraService> list = new ArrayList<>();
        if (accommodationUnitDTO.getExtraServiceDTO() != null){
            for (ExtraServiceDTO serviceDTO : accommodationUnitDTO.getExtraServiceDTO()){
                ExtraService extraService = extraServiceRepository.getOne(serviceDTO.getId());
                list.add(extraService);
            }
        }

        unit.setPricing(new ArrayList<>());
        if (accommodationUnitDTO.getPricingDTO() != null){
            for (PricingDTO p : accommodationUnitDTO.getPricingDTO() ){
                Pricing dbPrice = new Pricing(p);
                dbPrice.setLastChangedDate(new Date());
                pricingRepository.save(dbPrice);
                unit.getPricing().add(dbPrice);
            }
        }

        unit.setExtraService(list);
        accommodation.getAccommodationUnit().add(unit);

        return unit;
    }

    public AccommodationUnit updateUnit(Long accId, AccommodationUnitDTO accommodationUnitDTO) {

        Date thisTime = new Date();

        if (accommodationUnitDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No payload");
        }

        Accommodation accommodation = accommodationRepository.getOne(accId);
        AccommodationUnit unit = accommodationUnitRepository.getOne(accommodationUnitDTO.getId());

        if (unit.getAccommodation().getId() != accId){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Accommodation Unit can't change accommodation");
        }

        unit.setCapacity(accommodationUnitDTO.getCapacity());
        unit.setSize(accommodationUnitDTO.getSize());
        unit.setNameOfUnit(accommodationUnitDTO.getNameOfUnit());
        unit.setCancelationDays(accommodationUnitDTO.getCancelationDays());
        unit.setLastChangedDate(thisTime);
        unit.setLastChangedDate(new Date());

        if (accommodationUnitDTO.getUnitTypeDTO() != null){
            unit.setUnitType(unitTypeRepository.getOne(accommodationUnitDTO.getUnitTypeDTO().getId()));
        }

        if (accommodationUnitDTO.getLocationDTO() != null){
            unit.setLocation(locationRepository.getOne(accommodationUnitDTO.getLocationDTO().getId()));
        }
        accommodationUnitRepository.save(unit);
        return unit;
    }

    public void deleteUnit(Long accId, Long unitId) {

        Accommodation accommodation = accommodationRepository.getOne(accId);
        AccommodationUnit accommodationUnit = accommodationUnitRepository.getOne(unitId);

        if (accommodation.getAccommodationUnit().contains(accommodationUnit)) {
            accommodation.getAccommodationUnit().remove(accommodationUnit);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Accommodation Unit not part of Acc");
        }

        accommodationRepository.save(accommodation);

        accommodationUnitRepository.delete(accommodationUnit);
    }


    public List<AccommodationUnit> getAllUnits(Long accId){
        Accommodation accommodation = accommodationRepository.getOne(accId);
        return accommodation.getAccommodationUnit();
    }


    public AccommodationUnit getUnit(Long accId, Long unitId) throws ResponseStatusException {
        return accommodationUnitRepository.findByAccommodation_IdAndId(accId, unitId);
    }

    public List<AccommodationUnit> getAgentUnits(String email){
        User user = userRepository.findByEmail(email);

        if (user == null || user.getRole() == null || !user.getRole().getRoleName().contains("AGENT")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No agent");
        }
        return accommodationUnitRepository.findAllByAccommodation_User_Id(user.getId());
    }


}
