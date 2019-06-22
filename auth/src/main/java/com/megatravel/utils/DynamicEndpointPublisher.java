package com.megatravel.utils;



import com.megatravel.service.AuthServiceImpl;
import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
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


//	@Autowired
//	private EurekaInstanceConfig eurekaInstanceConfig;

	@PostConstruct
	public void init() {

		int port = this.getEmptyPort();
		applicationInfoManager.getInfo().getMetadata().put(SOAP_PORT,Integer.toString(port));
		publishEndpoint(port, AuthServiceImpl.ENDPOINT, AuthServiceImpl.class);
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
