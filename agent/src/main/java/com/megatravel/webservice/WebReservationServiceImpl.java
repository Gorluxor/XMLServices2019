package com.megatravel.webservice;


import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.interfaces.ReservationInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;


@WebService(portName="ReservationPort",
            serviceName="ReservationServiceInterface",
            targetNamespace="http://interfaces.megatravel.com/",
            endpointInterface = "com.megatravel.interfaces.ReservationInterface")
@Service
public class WebReservationServiceImpl implements ReservationInterface {

    public static final String ENDPOINT = "/reservation";

    public WebReservationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public ReservationDTO confirmReservation(Long reservationId) {
        return null;
    }
}
