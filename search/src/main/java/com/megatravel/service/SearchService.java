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
public class SearchService {

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
        System.out.println(searchDTO);
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
        if (searchDTO.getCategory() != ""){
            searchString = Accommodation.validCategory(searchDTO.getCategory());
        }

        if (searchDTO.getAccommodationType() != null){

            System.out.println(searchDTO);
            AccommodationType accommodationType = accommodationTypeRepository.findByNameOfAccType(searchDTO.getAccommodationType());

            System.out.println("City:"+searchDTO.getCity() + "Start:" + startDate + "EndDate:" + endDate+ "People:" + searchDTO.getNumberOfPeople()+ "TypeId:" + accommodationType.getId()+ "SearchString:" + searchString + "FreeDays:" + searchDTO.getSearchFreeDays()+ "\n");
            try{
                list = accommodationRepository.advancedSearch(searchDTO.getCity(), startDate, endDate, searchDTO.getNumberOfPeople(), accommodationType.getId(), searchString, searchDTO.isFreeToCancel(), searchDTO.getSearchFreeDays() );
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            list = accommodationRepository.normalSearch(searchDTO.getCity(), startDate, endDate, searchDTO.getNumberOfPeople());
        }
        System.out.println("ListBeforeExtraServices:" + list.size());
        List<ExtraService> extraServices = findExtraServices(searchDTO.getExtraServices());

        List<Accommodation> afterFilter = new ArrayList<>();
        if (searchDTO.getExtraServices() != null && list != null){
            for (Accommodation accommodation : list){
                if (accommodation.getExtraService().containsAll(extraServices)){
                    afterFilter.add(accommodation);
                }
            }
        }

        if (searchDTO.getDistance() != 0)
            afterFilter = calculateDistanceFromCity(afterFilter, searchDTO.getDistance(), searchDTO.getCity());

        return afterFilter;

    }

    public List<AccommodationUnit> normalSearchAccommodationUnit(SearchDTO searchDTO, Long id){
        validSearch(searchDTO);
        String startDate = changeToString(searchDTO.getStartDate());
        String endDate = changeToString(searchDTO.getEndDate());

        Accommodation accommodation = accommodationRepository.getOne(id);
        Long value = accommodation.getId();
        System.out.println("Address:"+searchDTO.getCity()+ "Date:"+startDate + "EndDate:" + endDate + "AccommodationId:" + value);
        return accommodationUnitRepository.normalSearch(searchDTO.getCity(), startDate, endDate, value);
    }

    @Deprecated
    public List<AccommodationUnit> advanceSearchAccommodationUnit(SearchDTO searchDTO, Long id){
        validSearch(searchDTO);
        String startDate = changeToString(searchDTO.getStartDate());
        String endDate = changeToString(searchDTO.getEndDate());
        List<AccommodationUnit> list;

        System.out.println("Advance search");

        Accommodation accommodation = accommodationRepository.getOne(id);
        Long value = accommodation.getId();

        String searchString = "all";
        if (searchDTO.getCategory() != ""){
            searchString = Accommodation.validCategory(searchDTO.getCategory());
        }


        if (searchDTO.getAccommodationType() != null){

            UnitType accommodationType = unitTypeRepository.findByNameOfUnitType(searchDTO.getAccommodationType());
            long val = 0L;
            if (accommodationType == null){
                val = -1L;
            }else {
                val = accommodationType.getId();
            }
           try{
               list = accommodationUnitRepository.advanceSearch(searchDTO.getCity(), startDate, endDate, val, value);
           }catch (Exception e){
               e.printStackTrace();
               list = null;
           }
        }else {
            list = accommodationUnitRepository.normalSearch(searchDTO.getCity(), startDate, endDate, value);
        }

        List<ExtraService> extraServices = findExtraServices(searchDTO.getExtraServices());



        List<AccommodationUnit> afterFilter = new ArrayList<>();
        if (searchDTO.getExtraServices() != null && list != null){
            System.out.println("Advance Unit search before filter:" + list.size());
            for (AccommodationUnit accommodationUnit : list){
                if (accommodationUnit.getExtraService().containsAll(extraServices)){

                    afterFilter.add(accommodationUnit);
                }
            }
        }
//        if (searchDTO.getDistance() != 0)
//            afterFilter = calculateDistanceFromCity(afterFilter, searchDTO.getDistance(), searchDTO.getCity());

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

    private List<Accommodation> calculateDistanceFromCity(List<Accommodation> accommodations, double distance, String city) {
        List<Location> locationList = locationRepository.findByCity(city);

        List<Accommodation> retVal = new ArrayList<>();

        locationList.forEach(address -> {
            accommodations.forEach(accommodation -> {
                double x = accommodation.getLocation().getLongitude();
                double y = accommodation.getLocation().getLatitude();
                double calculatedDistance =
                        Math.sqrt(Math.pow(x - address.getLongitude(), 2) + Math.pow(y - address.getLatitude(), 2));

                System.out.println("Calculated: " + calculatedDistance * degreesToKm);
                System.out.println("Distance provided: " + distance);

                if (calculatedDistance * degreesToKm <= distance) {
                    if (!retVal.contains(accommodation)) {
                        retVal.add(accommodation);
                    }
                    return;
                }
            });
        });

        return retVal;
    }

}
