package com.megatravel.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.interfaces.Login;
import com.megatravel.interfaces.LoginResponse;
import com.megatravel.models.admin.User;
import com.megatravel.service.AuthServiceImpl;
import com.megatravel.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AuthEndpoint {
    final String NAMESPACE = "http://interfaces.megatravel.com/";

    @Autowired
    UserServiceImpl authService;

    @Autowired
    AuthServiceImpl service;

    @ResponsePayload
    @PayloadRoot(namespace = NAMESPACE, localPart = "Login")
    public LoginResponse getAll(@RequestPayload Login input) {
        LoginResponse response = new LoginResponse();

        System.out.println("DOSAO");

        System.out.println("User " + input.getLoginDTO().getEmail());

        User user = authService.findByEmail(input.getLoginDTO().getEmail());
        if (user == null) {
            return null;
        } else {
            if (!user.isActivatedUser()) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not active");
            } else {
                if (!user.getRole().getRoleName().contains("AGENT")) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "NIJE AGENT");
                }
            }


            com.megatravel.dtos.admin.LoginDTO loginDTO = new com.megatravel.dtos.admin.LoginDTO();
            loginDTO.setEmail(input.getLoginDTO().getEmail());
            loginDTO.setPassword(input.getLoginDTO().getPassword());
            String jwt = service.login(loginDTO);
            ObjectMapper mapper = new ObjectMapper();

           /* try {
                System.out.println("JWT token" + mapper.writeValueAsString(jwt));
                response.setReturn(mapper.writeValueAsString(jwt));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }*/
            response.setReturn(jwt);

            return response;

/*
        User user = authService.findByEmail(input.getLoginDTO().getEmail());
        if(user == null) {
            return null;
        }else {
            if (!user.isActivatedUser()){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User not active");
            }
        }

        try {
            String jwt = authService.signin(input.getLoginDTO().getEmail(), input.getLoginDTO().getPassword());
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("JWT token" + mapper.writeValueAsString(jwt));
            response.setReturn(mapper.writeValueAsString(jwt));
            return response;
        } catch (Exception e) {
            return null;

        }*/


        }
    }


}
