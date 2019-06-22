package com.megatravel.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;

import com.megatravel.gateway.interfaces.XmlServiceFeignClient;
import com.megatravel.gateway.utils.StringUtilities;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SigningPostFilter extends ZuulFilter {

	@Autowired
	private StringUtilities utilites;
	
	@Autowired
	private XmlServiceFeignClient service;
	
	@Override
	public boolean shouldFilter() {
		return false;
		//return !utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.WSDL_SUFFIX)
		//		&& utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.SOAP_PREFIX);
	}

	@Override
	public int filterOrder() {
		return 2;
	}
	
	@Override
	public String filterType() {
		return "post";
	}
	
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		String body = context.getResponseBody();
		String url = utilites.getFullURL(context.getRequest());
		String microserviceName = utilites.getServiceNameFromRoute(url);
		//context.setResponseBody(service.signAndEncode(body, ?, microserviceName));
		return null;
	}

}
