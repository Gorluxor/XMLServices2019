package com.megatravel.utils;


import com.megatravel.webservice.WebAccommodationServiceImpl;
import com.megatravel.webservice.WebAccommodationUnitServiceImpl;
import com.megatravel.webservice.WebPricingServiceImpl;
import com.megatravel.webservice.WebReservationServiceImpl;
import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.ServerSocket;
/*
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
		publishEndpoint(port, WebAccommodationServiceImpl.ENDPOINT, WebAccommodationServiceImpl.class);
		publishEndpoint(port, WebAccommodationUnitServiceImpl.ENDPOINT, WebAccommodationUnitServiceImpl.class);
		publishEndpoint(port, WebPricingServiceImpl.ENDPOINT, WebPricingServiceImpl.class);
		publishEndpoint(port, WebReservationServiceImpl.ENDPOINT, WebReservationServiceImpl.class);
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
			String outInfo = "Visit http://localhost:"+port+"/services"+endpoint+"?wsdl  for wsdl";
			String a = "http://0.0.0.0:" + port +"/services"+ endpoint;
			System.out.println(outInfo);
			Endpoint e = Endpoint.publish(a, webService.newInstance());

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
*/