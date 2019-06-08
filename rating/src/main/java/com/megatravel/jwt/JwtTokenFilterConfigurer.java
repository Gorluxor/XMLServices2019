package com.megatravel.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@SuppressWarnings("Duplicates")
@Component
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private JwtTokenUtils jwtTokenProvider;

	public JwtTokenFilterConfigurer(JwtTokenUtils jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
