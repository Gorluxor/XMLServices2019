package com.megatravel.util;


import com.megatravel.webservice.SearchServiceImpl;
import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;

@SuppressWarnings("Duplicates")
@Component
public class DynamicEndpointPublisher {

    private static final String SOAP_PORT = "soap-port";

    @SuppressWarnings("Autowired")

    @Autowired
    @Qualifier(value = "eurekaApplicationInfoManager")
    ApplicationInfoManager applicationInfoManager;

    @PostConstruct
    public void init() {
        Map<String, String> map = applicationInfoManager.getInfo().getMetadata();


        int port = this.getEmptyPort();
        map.put(SOAP_PORT, Integer.toString(port));
        publishEndpoint(port, SearchServiceImpl.ENDPOINT, SearchServiceImpl.class);
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
