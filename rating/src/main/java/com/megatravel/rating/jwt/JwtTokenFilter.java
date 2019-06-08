package com.megatravel.rating.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SuppressWarnings("Duplicates")
@Component
public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenUtils jwtTokenProvider;

	public JwtTokenFilter(JwtTokenUtils jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	//Interceptor that adds the token if its missing
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
		if (token != null && jwtTokenProvider.validateToken(token)) {
			Authentication auth = token != null ? jwtTokenProvider.getAuthentication(token) : null;
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(req, res);
	}

}
