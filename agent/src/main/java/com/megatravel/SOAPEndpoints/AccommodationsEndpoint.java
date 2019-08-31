package com.megatravel.SOAPEndpoints;

import com.megatravel.interfaces.GetAllAccommodations;
import com.megatravel.interfaces.GetAllAccommodationsResponse;
import com.megatravel.repository.AccommodationRepository;
import com.megatravel.service.AccommodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

    @Endpoint
    public class AccommodationsEndpoint {
        final String NAMESPACE = "http://interfaces.megatravel.com/";

        @Autowired
        AccommodationServiceImpl accommodationService;

        @ResponsePayload
        @PayloadRoot(namespace = NAMESPACE, localPart = "GetAllAccommodations")
        public GetAllAccommodationsResponse getAll(@RequestPayload GetAllAccommodations input) {
            GetAllAccommodationsResponse response = new GetAllAccommodationsResponse();

            System.out.println("DOSAO");

            accommodationService.getAllAccommodations();

            return response;
        }

    }

