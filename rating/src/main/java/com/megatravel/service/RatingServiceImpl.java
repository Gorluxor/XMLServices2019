package com.megatravel.service;

import com.megatravel.dtos.rating.RatingDTO;
import com.megatravel.dtos.reservations.ReservationDTO;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@WebService(endpointInterface = "com.megatravel.interfaces.RatingService")
@Service
public class RatingServiceImpl implements com.megatravel.interfaces.RatingService {

    public static final String ENDPOINT = "/rating";


    @Override
    public RatingDTO addRating(RatingDTO ratingDTO) {
        return null;
    }

    @Override
    public RatingDTO RemoveRating(Long ratingId) {
        return null;
    }
}