package com.megatravel.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SuppressWarnings("unused")
@Component
public class TokenUtils {
    public static String getUsername(String jwtToken) {
        String pureJwtToken = jwtToken.substring(7, jwtToken.length());
        return decodeJwt(pureJwtToken).getSub(); // email address in our case
    }

    public static JwtToken decodeJwt(String pureJwtToken) {
        java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
        String[] parts = pureJwtToken.split("\\.");
        String headerJson = new String(decoder.decode(parts[0]));
        String payloadJson = new String(decoder.decode(parts[1]));
        JwtToken tokenObject = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            tokenObject = objectMapper.readValue(payloadJson, JwtToken.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenObject;
    }
}
