package com.megatravel.interfaces;

import com.megatravel.dtos.agent.AccommodationUnitDTO;
import com.megatravel.dtos.agent.PricingDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public interface PricingInterface {

    @WebMethod
    List<PricingDTO> getAllPricesForUnit(@WebParam(name = "UnitId") Long unitId);

    @WebMethod
    PricingDTO getPricesForUnit(@WebParam(name = "UnitId") Long unitId, @WebParam(name = "PriceId") Long priceId);

    @WebMethod
    PricingDTO updatePriceForUnit(@WebParam(name = "UnitId") Long unitId, PricingDTO pricingDTO);

    @WebMethod
    PricingDTO deletePriceForUnit(@WebParam(name = "UnitId") Long unitId, @WebParam(name = "PriceId") Long priceId);

    @WebMethod
    PricingDTO createPriceForUnit(@WebParam(name = "UnitId") Long unitId, PricingDTO pricingDTO);

    @WebMethod
    double getPriceForDatesUnitId(@WebParam(name = "UnitId") Long unitId, @WebParam(name = "startDate") Date startDate, @WebParam(name = "endDate") Date endDate);


}
