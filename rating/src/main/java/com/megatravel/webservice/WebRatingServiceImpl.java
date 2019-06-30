package com.megatravel.webservice;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.interfaces.RatingInterface;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;
import java.util.List;

@WebService(portName = "RatingPort",
            serviceName = "RatingServiceInterface",
            targetNamespace = "http://interfaces.megatravel.com",
            endpointInterface = "com.megatravel.interfaces.RatingInterface")
@Service
public class WebRatingServiceImpl implements RatingInterface {

    public static final String ENDPOINT = "/services/rating";

    public WebRatingServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebConfig.getWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }


    @Override
    public RatingDTO addRating(RatingDTO ratingDTO) {
        return null;
    }

    @Override
    public RatingDTO removeRating(Long ratingId) {
        return null;
    }

    @Override
    public RatingDTO approveRating(Long id) {
        return null;
    }

    @Override
    public List<RatingDTO> ratingForReservation(Long reservationId) {
        return null;
    }
}
