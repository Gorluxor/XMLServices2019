package com.megatravel.controller;

import com.megatravel.dtos.agent.PricingDTO;
import com.megatravel.models.agent.Pricing;
import com.megatravel.service.PricingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class PricingController {

    @Autowired
    private PricingServiceImpl pricingService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PricingDTO> createPricing(@RequestBody PricingDTO pricingDTO, @PathVariable(name = "unitId") Long unitId){
        return new ResponseEntity<>( new PricingDTO(pricingService.createPriceForUnit(unitId, pricingDTO)), HttpStatus.OK);
    }


    @RequestMapping(value = "/unit/{unitId}/price/{priceId}", method = RequestMethod.DELETE)
    public ResponseEntity<PricingDTO> deletePricing( @PathVariable(name = "unitId") Long unitId, @PathVariable(name = "priceId") Long priceId ){
        return new ResponseEntity<>( new PricingDTO(pricingService.deletePriceForUnit(unitId, priceId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price",method = RequestMethod.GET)
    public ResponseEntity<List<PricingDTO>> getAllPricing(@PathVariable(name = "unitId") Long unitId ){
        List<Pricing> results = pricingService.getAllPricesForUnit(unitId);
        List<PricingDTO> values = new ArrayList<>();
        for (Pricing pricing : results ){
            values.add(new PricingDTO(pricing));
        }
        return new ResponseEntity<>(values, HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price/{priceId}",method = RequestMethod.GET)
    public ResponseEntity<PricingDTO> getOnePricing(@PathVariable(name = "unitId") Long unitId, @PathVariable(name = "priceId") Long priceId ){
        return new ResponseEntity<>( new PricingDTO(pricingService.getPricesForUnit(unitId, priceId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price",method = RequestMethod.PUT)
    public ResponseEntity<PricingDTO> updatePricing(@RequestBody PricingDTO pricingDTO, @PathVariable(name = "unitId") Long unitId){
        return new ResponseEntity<>( new PricingDTO(pricingService.updatePriceForUnit(unitId, pricingDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price/search/{startDate}/{endDate}/" , method = RequestMethod.GET)
    public double getPricingForDates(@PathVariable(name = "unitId") Long unitId, @PathVariable(name = "startDate") Date startDate, @PathVariable(name = "endDate") Date endDate){
        return  pricingService.getPriceForDatesUnitId(unitId, startDate, endDate);
    }

    /***
     * Returns minimal price for interval, "starting price is return"
     *
     * @param accId Accommodation Id
     * @param startDate Start date interval1
     * @param endDate End date interval
     * @return
     */
    @RequestMapping(value = "/accommodation/{accId}/search/{startDate}/{endDate}/" , method = RequestMethod.GET)
    public double getMinPricingForDates(@PathVariable(name = "accId") Long accId, @PathVariable(name = "startDate") Date startDate, @PathVariable(name = "endDate") Date endDate){
        return  pricingService.getPriceForDatesAccId(accId, startDate, endDate);
    }


}
