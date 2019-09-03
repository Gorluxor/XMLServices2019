package com.megatravel.SOAPEndpoints;

import com.megatravel.interfaces.*;
import com.megatravel.models.agent.*;
import com.megatravel.repository.*;
import com.megatravel.service.AccommodationServiceImpl;
import com.megatravel.service.AccommodationUnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Endpoint
    public class AccommodationsEndpoint {
        final String NAMESPACE = "http://interfaces.megatravel.com/";

    @Autowired
    private UnitTypeRepository unitTypeRepository;

        @Autowired
        private    AccommodationServiceImpl accommodationService;

        @Autowired
        private     AccommodationUnitRepository accommodationUnitRepository;

        @Autowired
        private    ExtraServiceRepository extraServiceRepository;

        @Autowired
        private    AccommodationUnitServiceImpl accommodationUnitService;

    @Autowired
    private AccommodationRepository accommodationRepository;


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PricingRepository pricingRepository;


    @Autowired
    private LocationRepository locationRepository;


    @Autowired
    private UserRepository userRepository;

        @ResponsePayload
        @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllAccommodations")
        public GetAllAccommodationsResponse getAll(@RequestPayload GetAllAccommodations input) {
            GetAllAccommodationsResponse response = new GetAllAccommodationsResponse();


            List<AccommodationDTO> accommodationDTOArrayList = new ArrayList<>();

            System.out.println("DOSAO");


            List<Accommodation> accommodations =  accommodationService.getAllAccommodations();
            AccommodationDTO pom;

            for(Accommodation a : accommodations){
                pom = new AccommodationDTO();
                AccommodationTypeDTO pom1 = new AccommodationTypeDTO();
                pom1.setId(a.getAccommodationType().getId());
                pom1.setNameOfAccType(a.getAccommodationType().getNameOfAccType());
                pom.setAccommodationTypeDTO(pom1);
                pom.setCancelationDays(java.math.BigInteger.valueOf(a.getFreeToCancelDays()));
                pom.setDescription(a.getDescription());
                pom.setId(a.getId());
                pom.setName(a.getName());
                UserDTO pomUser = new UserDTO();
                pomUser.setEmail(a.getUser().getEmail());
                pom.setUserDTO(pomUser);
                LocationDTO pomLoc = new LocationDTO();
                pomLoc.setId(a.getLocation().getId());
                pomLoc.setCity(a.getLocation().getCity());
                pom.setLocationDTO(pomLoc);
                accommodationDTOArrayList.add(pom);
            }
            response.setAccommodationDTO(accommodationDTOArrayList);

            return response;
        }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllServices")
    public GetAllServicesResponse getAllServicesResponse(@RequestPayload GetAllServices input) {
        GetAllServicesResponse response = new GetAllServicesResponse();


        List<ExtraServiceDTO> extraServiceDTOS = new ArrayList<>();

        System.out.println("DOSAO");


        List<ExtraService> extraServices =  extraServiceRepository.findAll();
        ExtraServiceDTO pom;

        for(ExtraService a : extraServices){
            pom = new ExtraServiceDTO();
            pom.setDescription(a.getDescription());
            pom.setId(a.getId());
            pom.setNameOfService(a.getNameOfService());

            extraServiceDTOS.add(pom);
        }
        response.setExtraServiceDTO(extraServiceDTOS);

        return response;
    }


    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllUnits")
    public GetAllUnitsResponse getAllUnitsResponse(@RequestPayload GetAllUnits input) {
        GetAllUnitsResponse response = new GetAllUnitsResponse();


        List<AccommodationUnitDTO> accommodationUnitDTOS = new ArrayList<>();

        System.out.println("DOSAO");


        List<AccommodationUnit> accommodationUnits =  accommodationUnitRepository.findAll();
        AccommodationUnitDTO pom;

        for(AccommodationUnit a : accommodationUnits){
            if(a.getAccommodation().getId() != input.getAccId())
                continue;
            pom = new AccommodationUnitDTO();
            pom.setCapacity(a.getCapacity());
            pom.setId(a.getId());
            pom.setNameOfUnit(a.getNameOfUnit());
            pom.setSize(a.getSize());

            UnitTypeDTO pom1 = new UnitTypeDTO();
            pom1.setId(a.getUnitType().getId());
            pom1.setNameOfUnitType(a.getUnitType().getNameOfUnitType());
            pom.setUnitTypeDTO(pom1);


            accommodationUnitDTOS.add(pom);
        }
        response.setAccommodationUnitDTO(accommodationUnitDTOS);

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllUnitType")
    public GetAllUnitTypeResponse getAllUnitType(@RequestPayload GetAllUnitType input) {
        GetAllUnitTypeResponse response = new GetAllUnitTypeResponse();


        List<UnitTypeDTO> unitTypeDTOS = new ArrayList<>();

        System.out.println("DOSAO");


        List<UnitType> unitTypes =  unitTypeRepository.findAll();
        UnitTypeDTO pom;

        for(UnitType a : unitTypes){
            pom = new UnitTypeDTO();
            pom.setNameOfUnitType(a.getNameOfUnitType());
            pom.setId(a.getId());

            unitTypeDTOS.add(pom);
        }
        response.setUnitTypeDTO(unitTypeDTOS);

        return response;
    }

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "CreateUnit")
    public CreateUnitResponse createUnit(@RequestPayload CreateUnit input) {

        if (input.getAccommodationUnitDTO()== null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such object in database");
        }

        AccommodationUnit unit = new AccommodationUnit(input.getAccommodationUnitDTO());
        Accommodation accommodation = accommodationRepository.getOne(input.getAccId());

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
        if (input.getAccommodationUnitDTO().getExtraServiceDTO() != null){
            for (ExtraServiceDTO serviceDTO : input.getAccommodationUnitDTO().getExtraServiceDTO()){
                ExtraService extraService = extraServiceRepository.getOne(serviceDTO.getId());
                list.add(extraService);
            }
        }

        unit.setPricing(new ArrayList<>());
        if (input.getAccommodationUnitDTO().getPricingDTO() != null){
            for (PricingDTO p : input.getAccommodationUnitDTO().getPricingDTO() ){
                Pricing dbPrice = new Pricing(p);
                dbPrice.setLastChangedDate(new Date());
                pricingRepository.save(dbPrice);
                unit.getPricing().add(dbPrice);
            }
        }

        unit.setExtraService(list);
        accommodation.getAccommodationUnit().add(unit);



        CreateUnitResponse response = new CreateUnitResponse();

        response.setUspelo(true);



        return response;
    }




    }

