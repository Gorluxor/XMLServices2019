package com.megatravel.controller;

import com.megatravel.dtos.agent.PricingDTO;
import com.megatravel.dtos.reservations.SearchDTO;
import com.megatravel.models.agent.Pricing;
import com.megatravel.service.PricingServiceImpl;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "")
public class PricingController {

    @Autowired
    private PricingServiceImpl pricingService;

    @RequestMapping(value = "/unit/{unitId}/price",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PricingDTO> createPricing(@RequestBody PricingDTO pricingDTO, @PathVariable(name = "unitId") Long unitId){
        return new ResponseEntity<>( new PricingDTO(pricingService.createPriceForUnit(unitId, pricingDTO)), HttpStatus.OK);
    }


    @RequestMapping(value = "/unit/{unitId}/price/{priceId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePricing( @PathVariable(name = "unitId") Long unitId, @PathVariable(name = "priceId") Long priceId ){
        pricingService.deletePriceForUnit(unitId, priceId);
        return new ResponseEntity<>( "Deleted pricing" , HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<PricingDTO>> getAllPricing(@PathVariable(name = "unitId") Long unitId ){
        List<Pricing> results = pricingService.getAllPricesForUnit(unitId);
        List<PricingDTO> values = new ArrayList<>();
        for (Pricing pricing : results ){
            values.add(new PricingDTO(pricing));
        }
        return new ResponseEntity<>(values, HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price/{priceId}",method = RequestMethod.GET,  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PricingDTO> getOnePricing(@PathVariable(name = "unitId") Long unitId, @PathVariable(name = "priceId") Long priceId ){
        return new ResponseEntity<>( new PricingDTO(pricingService.getPricesForUnit(unitId, priceId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price", method = RequestMethod.PUT,  consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PricingDTO> updatePricing(@RequestBody PricingDTO pricingDTO, @PathVariable(name = "unitId") Long unitId){
        return new ResponseEntity<>( new PricingDTO(pricingService.updatePriceForUnit(unitId, pricingDTO)), HttpStatus.OK);
    }

    @RequestMapping(value = "/unit/{unitId}/price/search" , method = RequestMethod.GET)
    public double getPricingForDates(@PathVariable(name = "unitId") Long unitId, @RequestBody SearchDTO searchDTO) {
        checkIfDatesArePresent(searchDTO);
        return  pricingService.getPriceForDatesUnitId(unitId, searchDTO.getStartDate(), searchDTO.getEndDate());
    }


    @RequestMapping(value = "/accommodation/{accId}/search" , method = RequestMethod.GET)
    public double getMinPricingForDates(@PathVariable(name = "accId") Long accId, @RequestBody SearchDTO searchDTO){
        checkIfDatesArePresent(searchDTO);
        return  pricingService.getPriceForDatesAccId(accId, searchDTO.getStartDate(), searchDTO.getEndDate());
    }

    private void checkIfDatesArePresent(SearchDTO searchDTO) throws ResponseStatusException{
        if (searchDTO == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No data");
        }else if (searchDTO.getStartDate() == null || searchDTO.getEndDate() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No dates");
        }
    }


}
