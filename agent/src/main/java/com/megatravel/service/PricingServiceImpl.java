package com.megatravel.service;

import com.megatravel.dtos.agent.PricingDTO;
import com.megatravel.models.agent.AccommodationUnit;
import com.megatravel.models.agent.Pricing;
import com.megatravel.repository.AccommodationUnitRepository;
import com.megatravel.repository.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PricingServiceImpl{

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private AccommodationUnitRepository accommodationUnitRepository;

    public List<Pricing> getAllPricesForUnit(Long unitId) {
        return pricingRepository.findAllByPriceForUnit_Id(unitId);
    }

    public Pricing getPricesForUnit(Long unitId, Long priceId) {
        return pricingRepository.findFirstByPriceForUnit_IdAndId(unitId, priceId);
    }

    public Pricing updatePriceForUnit(Long unitId, PricingDTO pricingDTO) throws ResponseStatusException {
        if (pricingDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No object");
        }
        Pricing pricing = pricingRepository.findFirstByPriceForUnit_IdAndId(unitId, pricingDTO.getId());
        if (pricing == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad UnitId or Price Id");
        }

        if (pricing.getStartDate() == null || pricing.getPrice() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No info");
        }

        pricing.setLastChangedDate(new Date());
        pricing.setPrice(pricingDTO.getPrice());
        pricing.setStartDate(pricingDTO.getStartDate());
        return pricing;
    }

    public void deletePriceForUnit(Long unitId, Long priceId) throws ResponseStatusException{
        Pricing pricing = pricingRepository.findFirstByPriceForUnit_IdAndId(unitId,priceId);
        if (pricing == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No object");
        }
        AccommodationUnit accommodationUnit = accommodationUnitRepository.getOne(pricing.getPriceForUnit().getId());
        accommodationUnit.getPricing().remove(pricing);
        accommodationUnitRepository.save(accommodationUnit);
        pricingRepository.delete(pricing);
    }

    public Pricing createPriceForUnit(Long unitId, PricingDTO pricingDTO) throws ResponseStatusException {

        if (pricingDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No object");
        }

        Pricing pricing = new Pricing(pricingDTO);

        if (pricing.getStartDate() == null || pricing.getPrice() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No info");
        }
        AccommodationUnit accommodationUnit = accommodationUnitRepository.getOne(unitId);

        pricing.setLastChangedDate(new Date());
        pricing.setPriceForUnit(accommodationUnit);


        pricingRepository.save(pricing);

        accommodationUnit.getPricing().add(pricing);

        accommodationUnitRepository.save(accommodationUnit);

        return pricing;
    }

    public double getPriceForDatesUnitId( Long unitId, Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        BigDecimal totalPrice = new BigDecimal(0);

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
           totalPrice = totalPrice.add(pricingRepository.findFirstByPriceForUnit_IdAndStartDateBeforeOrderByStartDateDesc(unitId,date).getPrice()) ;
        }

        return totalPrice.doubleValue();
    }

    public double getPriceForDatesAccId(Long accId, Date startDate, Date endDate){

        double minPrice = Double.MAX_VALUE;

        List<AccommodationUnit> list = accommodationUnitRepository.findAllByAccommodation_Id(accId);

        if (list == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No accommodation units for the accommodation");
        }

        for (AccommodationUnit a : list){
           double something = getPriceForDatesUnitId(a.getId(), startDate, endDate);
           if (minPrice > something){
               minPrice = something;
           }
        }

        if (minPrice == Double.MAX_VALUE){
            // didn't find better price
            minPrice = -1;
        }

        // Should be price starting at ...
        return minPrice;
    }


}
