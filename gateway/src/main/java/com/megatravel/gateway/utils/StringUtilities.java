package com.megatravel.gateway.utils;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Component;

@Component
public class StringUtilities {

	public static final String SOAP_PORT = "soap-port";
	public static final String SOAP_PREFIX = "/services";


	
	@Autowired
	private ZuulProperties properties;



	public String getSoapServiceEndpointFromUrl(String url) {
		String endpoint = url.substring(url.indexOf(StringUtilities.SOAP_PREFIX));
		return endpoint;
	}

	public String getZuulPathFromURL(String url) {
		String route = url.substring(0, url.indexOf(StringUtilities.SOAP_PREFIX));
		route = route.substring(route.lastIndexOf("/") + 1);
		return "/" + route + "/**";
	}

	public String getServiceNameFromRoute(String path) {
		Collection<ZuulRoute> routes = properties.getRoutes().values();
		for(ZuulRoute route : routes) {
			if(route.getPath().equals(path)) {
				return route.getServiceId();
			}
		}
		return null;
	}

	public String getFullURL(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();
		if (queryString == null) {
			return requestURL.toString();
		} else {
			return requestURL.append('?').append(queryString).toString();
		}
	}

	public String returnSoapAction(String url, String soap) {
		int elementIndex = soap.indexOf("Body");
		int locationBeginIndex = soap.indexOf(':', elementIndex);
		int locationEndIndex = soap.indexOf(' ', locationBeginIndex);
		String result = url + "#" + soap.substring(locationBeginIndex + 1, locationEndIndex);
		return this.fixQuotesAroundSoapAction(result);
	}
	
	private String fixQuotesAroundSoapAction(String soapAction) {
	    if(soapAction == null || soapAction.startsWith("\"") && soapAction.endsWith("\"")) {
	        return soapAction;
	    } else {
	        String fixedSoapAction = soapAction;
	        if(!soapAction.startsWith("\"")) {
	            fixedSoapAction = "\"" + soapAction;
	        }
	        if(!soapAction.endsWith("\"")) {
	            fixedSoapAction = fixedSoapAction + "\"";
	        }
	        return fixedSoapAction;
	    }
	}
	
}
