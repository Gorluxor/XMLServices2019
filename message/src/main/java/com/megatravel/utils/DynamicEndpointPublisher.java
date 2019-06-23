package com.megatravel.utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

import com.megatravel.webservice.WebMessageServiceImpl;
import com.netflix.appinfo.EurekaInstanceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.megatravel.service.MessageServiceImpl;
import com.netflix.appinfo.ApplicationInfoManager;

@SuppressWarnings("Duplicates")
@Component
public class DynamicEndpointPublisher {

    private static final String SOAP_PORT = "soap-port";

    @Autowired
    @Qualifier(value = "eurekaApplicationInfoManager")
    ApplicationInfoManager applicationInfoManager;
    @PostConstruct
    public void init() {

        int port = this.getEmptyPort();
        applicationInfoManager.getInfo().getMetadata().put(SOAP_PORT,Integer.toString(port));
        publishEndpoint(port, WebMessageServiceImpl.ENDPOINT, WebMessageServiceImpl.class);
    }

    private int getEmptyPort() {
            try {

                ServerSocket serverSocket = new ServerSocket(0);
                int port = serverSocket.getLocalPort();
                serverSocket.close();
                return port;
            } catch(IOException e) {
                e.printStackTrace();
            }
        return 0;
    }

    private void publishEndpoint(int port, String endpoint, Class<?> webService) {
        try {
            String outInfo = "Visit http://localhost:"+port+endpoint+"?wsdl  for wsdl";
            String a = "http://0.0.0.0:" + port + endpoint;
            System.out.println(outInfo);
            Endpoint e = Endpoint.publish(a, webService.newInstance());

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
