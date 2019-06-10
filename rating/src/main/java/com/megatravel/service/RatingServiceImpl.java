package com.megatravel.service;

import com.megatravel.configs.WebConfig;
import com.megatravel.dtos.rating.RatingDTO;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.jws.WebService;

@WebService(endpointInterface = "com.megatravel.interfaces.RatingService")
@Service
public class RatingServiceImpl implements com.megatravel.interfaces.RatingService {

    public static final String ENDPOINT = "/rating";

    public RatingServiceImpl() {
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
    public RatingDTO RemoveRating(Long ratingId) {
        return null;
    }
}
