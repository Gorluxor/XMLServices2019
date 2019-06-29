package com.megatravel.webservice;


import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.reservations.ReservationDTO;
import com.megatravel.interfaces.ReservationInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;


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

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public ReservationDTO cancelReservation(Long reservationId) {
        return null;
    }

    @Override
    public List<ReservationDTO> getListReservationsForUser(Long userId) {
        return null;
    }
}
