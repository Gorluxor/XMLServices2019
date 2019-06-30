package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.agent.PricingDTO;
import com.megatravel.interfaces.PricingInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;


@WebService(portName="PricingPort",
            serviceName="PricingServiceInterface",
            targetNamespace="http://interfaces.megatravel.com/",
            endpointInterface = "com.megatravel.interfaces.PricingInterface")
@Service
public class WebPricingServiceImpl implements PricingInterface {

    public static final String ENDPOINT = "/services/price";

    public WebPricingServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }


    @Override
    public List<PricingDTO> getAllPricesForUnit(Long unitId) {
        return null;
    }

    @Override
    public PricingDTO getPricesForUnit(Long unitId, Long priceId) {
        return null;
    }

    @Override
    public PricingDTO updatePriceForUnit(Long unitId, PricingDTO pricingDTO) {
        return null;
    }

    @Override
    public PricingDTO deletePriceForUnit(Long unitId, Long priceId) {
        return null;
    }

    @Override
    public PricingDTO createPriceForUnit(Long unitId, PricingDTO pricingDTO) {
        return null;
    }

    @Override
    public double getPriceForDatesUnitId(Long unitId, Date startDate, Date endDate) {
        return 0;
    }
}
