package com.megatravel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.megatravel.jwt.JwtTokenFilterConfigurer;
import com.megatravel.jwt.JwtTokenUtils;


@SuppressWarnings("deprecation")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenUtils jwtTokenProvider;

	@Autowired
	private ImplementedUserDetails userDetailsService;

	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();

		http.cors();

		//http.headers().httpStrictTransportSecurity().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		//TODO 1: ono cemu neregistrovani korisnik sme da pristupi
		http.authorizeRequests().antMatchers("/*", "/users/email/*",
				"/ping/test", "/users/login", "/users/signup", "/rooms/search", "/address", "/**/*?wsdl","*/wss/*/*","*/wss/*","/wss/*").permitAll()
				.anyRequest()
				.authenticated();


		// If a user try to access a resource without having enough permissions
		http.exceptionHandling().accessDeniedPage("/api/auth/login");

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

		//http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		//sve sto ce se ignorisati za web
//		web.ignoring().antMatchers("/webjars/**")//
//				.antMatchers("/public")//
//				.antMatchers("/main**")//
//				.antMatchers("/inline**")//
//				.antMatchers("/runtime**")//
//				.antMatchers("/polyfills**")//
//				.antMatchers("/styles**")//
//				.antMatchers("/favicon.ico")//
//				.antMatchers("/scripts**")//
//				.antMatchers("/glyphicons**")//
//				.antMatchers("/fontawesome**")//
//				.antMatchers("/vendor**")//
//				.antMatchers("/assets/**")//
//				.antMatchers("/Poppins**")//
//				.antMatchers("/h2-console");
//	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
