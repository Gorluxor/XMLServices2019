package com.megatravel.service;
import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.reservations.ReservationDTO;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


import javax.jws.WebService;

@WebService(endpointInterface = "com.megatravel.interfaces.ReservationService")
@Service
public class ReservationServiceImpl implements com.megatravel.interfaces.ReservationService {

    public static final String ENDPOINT = "/reservation";

    public ReservationServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {
        return null;
    }

    @Override
    public ReservationDTO confirmReservation(Long reservationId) {
        return null;
    }
}
