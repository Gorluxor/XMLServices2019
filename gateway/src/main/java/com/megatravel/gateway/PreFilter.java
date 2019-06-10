package com.megatravel.gateway;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

public class PreFilter extends ZuulFilter {

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = getCurrentContext();
        return ctx.getRequest().getRequestURI().startsWith("/ws/");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = getCurrentContext();
        HttpServletRequest request = ctx.getRequest();


        Map<String, String> map = eurekaInstanceConfig.getMetadataMap();

       // Integer port = Integer.parseInt(map.get("soap-port"));
        String port = map.get("soap-port");

        String url13 = ctx.getRouteHost().getPath();
        try{
            String url2 = ctx.getRouteHost().toURI().toString();
        }catch (Exception e){
            e.printStackTrace();
        }


        //String port = request.getParameter("port");
        try {
            URL url = UriComponentsBuilder.fromUri(ctx.getRouteHost().toURI())
                    .port(new Integer(port))
                    .build().toUri().toURL();
            ctx.setRouteHost(url);
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;


    }
}
