package com.megatravel.service;

import com.megatravel.dtos.reservations.SearchDTO;
import com.megatravel.models.agent.*;
import com.megatravel.models.types.Location;
import com.megatravel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")
@Service
public class SearchServiceImpl {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepository;

    @Autowired
    private UnitTypeRepository unitTypeRepository;

    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    @Autowired
    private LocationRepository locationRepository;


    public List<Accommodation> normalSearchAccommodation(SearchDTO searchDTO) throws ResponseStatusException{
        validSearch(searchDTO);
        String startDate = changeToString(searchDTO.getStartDate());
        String endDate = changeToString(searchDTO.getEndDate());
        return accommodationRepository.normalSearch(searchDTO.getCity(),startDate, endDate, searchDTO.getNumberOfPeople());

    }


    /***
     * searchDTO params that should be look out for
     * List<String> extraService (rather then ID) same goes for type (unit or accommodation) - accommodationType (same for both)
     *
     * @param searchDTO see for more details {@link com.megatravel.dtos.reservations.SearchDTO}
     * @return Returns the advance search of Accommodations
     */
    public List<Accommodation> advanceSearchAccommodation(SearchDTO searchDTO){
        validSearch(searchDTO);
        String startDate = changeToString(searchDTO.getStartDate());
        String endDate = changeToString(searchDTO.getEndDate());
        List<Accommodation> list = null;

        String searchString = "all";
        if (!searchDTO.getCategory().equals("")){
            searchString = Accommodation.validCategory(searchDTO.getCategory());
        }

        if (searchDTO.getAccommodationType() != null){

            AccommodationType accommodationType = accommodationTypeRepository.findByNameOfAccType(searchDTO.getAccommodationType());

            list = accommodationRepository.advancedSearch(searchDTO.getCity(), startDate, endDate, searchDTO.getNumberOfPeople(), accommodationType.getId(), searchString);
        }else {
            list = accommodationRepository.normalSearch(searchDTO.getCity(), startDate, endDate, searchDTO.getNumberOfPeople());
        }

        List<ExtraService> extraServices = findExtraServices(searchDTO.getExtraServices());

        List<Accommodation> afterFilter = new ArrayList<>();
        if (searchDTO.getExtraServices() != null && list != null){
            for (Accommodation accommodation : list){
                if (accommodation.getExtraService().containsAll(extraServices)){
                    afterFilter.add(accommodation);
                }
            }
        }



        return afterFilter;

    }

    public List<AccommodationUnit> normalSearchAccommodationUnit(SearchDTO searchDTO){
        validSearch(searchDTO);
        String startDate = changeToString(searchDTO.getStartDate());
        String endDate = changeToString(searchDTO.getEndDate());
        return accommodationUnitRepository.normalSearch(searchDTO.getCity(),startDate, endDate, searchDTO.getNumberOfPeople());
    }

    public List<AccommodationUnit> advanceSearchAccommodationUnit(SearchDTO searchDTO){
        validSearch(searchDTO);
        String startDate = changeToString(searchDTO.getStartDate());
        String endDate = changeToString(searchDTO.getEndDate());
        List<AccommodationUnit> list;

        String searchString = "all";
        if (!searchDTO.getCategory().equals("")){
            searchString = Accommodation.validCategory(searchDTO.getCategory());
        }


        if (searchDTO.getAccommodationType() != null){

            UnitType accommodationType = unitTypeRepository.findByNameOfUnitType(searchDTO.getAccommodationType());

            list = accommodationUnitRepository.advanceSearch(searchDTO.getCity(), startDate, endDate, searchDTO.getNumberOfPeople(), accommodationType.getId());
        }else {
            list = accommodationUnitRepository.normalSearch(searchDTO.getCity(), startDate, endDate, searchDTO.getNumberOfPeople());
        }

        List<ExtraService> extraServices = findExtraServices(searchDTO.getExtraServices());

        List<AccommodationUnit> afterFilter = new ArrayList<>();
        if (searchDTO.getExtraServices() != null && list != null){
            for (AccommodationUnit accommodationUnit : list){
                if (accommodationUnit.getExtraService().containsAll(extraServices)){

                    afterFilter.add(accommodationUnit);
                }
            }
        }
        if (searchDTO.getDistance() != 0)
            afterFilter = calculateDistanceFromCity(afterFilter, searchDTO.getDistance(), searchDTO.getCity());

        return afterFilter;
    }


    private void validSearch(SearchDTO searchDTO) throws ResponseStatusException{
        if (searchDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad search");
        }
        if (searchDTO.getCity() == null || searchDTO.getStartDate() == null || searchDTO.getNumberOfPeople() == 0 || searchDTO.getEndDate() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad basic search");
        }

        if (searchDTO.getStartDate().after(searchDTO.getEndDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Departure date is before arrival!");
        }
    }

    private String changeToString(Date date){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    private List<ExtraService> findExtraServices(List<String> list) throws ResponseStatusException{

        List<ExtraService> extraServices = new ArrayList<>();
        if (list != null){
            for (String string : list){
                ExtraService es = extraServiceRepository.findByNameOfService(string);
                if (es == null){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such extra service");
                }
                extraServices.add(es);
            }


        }
        return extraServices;
    }

    private final double degreesToKm = 111.32;

    private List<AccommodationUnit> calculateDistanceFromCity(List<AccommodationUnit> units, double distance, String city) {
        List<Location> locationList = locationRepository.findByCity(city);

        List<AccommodationUnit> retVal = new ArrayList<>();

        locationList.forEach(address -> {
            units.forEach(unit -> {
                double x = unit.getAccommodation().getLocation().getLongitude();
                double y = unit.getAccommodation().getLocation().getLatitude();
                double calculatedDistance =
                        Math.sqrt(Math.pow(x - address.getLongitude(), 2) + Math.pow(y - address.getLatitude(), 2));

                System.out.println("Calculated: " + calculatedDistance * degreesToKm);
                System.out.println("Distance provided: " + distance);

                if (calculatedDistance * degreesToKm <= distance) {
                    if (!retVal.contains(unit)) {
                        retVal.add(unit);
                    }
                    return;
                }
            });
        });

        return retVal;
    }

}
