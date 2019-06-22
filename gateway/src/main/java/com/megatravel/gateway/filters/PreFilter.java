package com.megatravel.gateway.filters;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.megatravel.gateway.utils.ServiceResponse;
import com.megatravel.gateway.utils.SoapRequestCaller;
import com.megatravel.gateway.utils.StringUtilities;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private SoapRequestCaller caller;
	
	@Autowired
	private StringUtilities utilites;

	@Override
	public boolean shouldFilter() {
		return utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.SOAP_PREFIX);
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String requestURL = utilites.getFullURL(request);
		String path = utilites.getZuulPathFromURL(requestURL);
		String microserviceName = utilites.getServiceNameFromRoute(path);
		List<ServiceInstance> microserviceInstances = discoveryClient.getInstances(microserviceName);
		int numberOfInstances = microserviceInstances.size();
		if(numberOfInstances > 0) {
			Random randomizer = new Random();
			int instanceIndex = randomizer.nextInt(numberOfInstances);
			ServiceInstance microserviceInstance = microserviceInstances.get(instanceIndex);
			String soapServicePort = microserviceInstance.getMetadata().get(StringUtilities.SOAP_PORT);
			String soapServiceEndpoint = utilites.getSoapServiceEndpointFromUrl(requestURL);
			String serviceFullAddress = "http://localhost:" + soapServicePort + soapServiceEndpoint;
			context.setSendZuulResponse(false);
			ServiceResponse response = caller.sendRequestTo(serviceFullAddress, microserviceName, request);
			context.setResponseStatusCode(response.getStatus());
			context.setResponseBody(response.getBody());
			context.addZuulResponseHeader("Content-Type", "text/xml; charset=utf-8");
		}
		return null;
	}

}
