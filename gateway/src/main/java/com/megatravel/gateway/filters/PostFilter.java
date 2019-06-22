package com.megatravel.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;

import com.megatravel.gateway.utils.StringUtilities;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PostFilter extends ZuulFilter {
	
	@Autowired
	private StringUtilities utilites;
	
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return utilites.getFullURL(RequestContext.getCurrentContext().getRequest()).contains(StringUtilities.WSDL_SUFFIX);
	}

	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		String body = context.getResponseBody();
		String url = utilites.getFullURL(context.getRequest());
		String location = utilites.createLocationFromURL(url);
		context.setResponseBody(utilites.replaceWebServicePort(body, location));
		return null;
	}

}
