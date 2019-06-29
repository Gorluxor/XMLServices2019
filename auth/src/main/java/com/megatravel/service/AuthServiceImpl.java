package com.megatravel.service;

import javax.jws.WebService;

import com.megatravel.dtos.admin.LoginDTO;
import com.megatravel.interfaces.AuthServiceInterface;
import com.megatravel.models.types.Location;
import com.megatravel.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.megatravel.dtos.admin.RegistrationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.configurations.WebApplicationContextLocator;


import com.megatravel.models.admin.Role;
import com.megatravel.models.admin.User;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@SuppressWarnings("Duplicates")
@WebService(portName="AuthPort",
	serviceName="AuthServiceInterface",
	targetNamespace="http://interfaces.megatravel.com/",
	endpointInterface = "com.megatravel.interfaces.AuthServiceInterface")
@Service
public class AuthServiceImpl implements AuthServiceInterface {

	public static final String ENDPOINT = "/login";

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	RoleServiceImpl roleServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
    public AuthServiceImpl() {
        AutowiredAnnotationBeanPostProcessor bpp = new AutowiredAnnotationBeanPostProcessor();
        WebApplicationContext currentContext = WebApplicationContextLocator.getCurrentWebApplicationContext();
        bpp.setBeanFactory(currentContext.getAutowireCapableBeanFactory());
        bpp.processInjection(this);
    }



	@Override
	public String testMethod() {
		return "USPEO";
	}

	@Override
	public String login(LoginDTO loginDTO) {
		
		User user = userServiceImpl.findByEmail(loginDTO.getEmail());
		if(user == null) {
			return null;
		}else {
			if (!user.isActivatedUser()){
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User not active");
			}
		}

		try {
			String jwt = userServiceImpl.signin(loginDTO.getEmail(), loginDTO.getPassword());
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("JWT token" + mapper.writeValueAsString(jwt));
			return mapper.writeValueAsString(jwt);
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public void signup( RegistrationDTO registrationDTO){
		if (!registrationDTO.getRepeatPassword().equals(registrationDTO.getPassword())) {
			//ako nisu jednaki passwordi mora ponovo da unosi
			return;
		}

		User tempKorisnik = userServiceImpl.findByEmail(registrationDTO.getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return;
		}
		
		User user = new User();
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		user.setName(registrationDTO.getName());
		user.setLastName(registrationDTO.getLastName());
		Role role = roleServiceImpl.findByRoleName("ROLE_USER");

		user.setLastChangedDate(new Date());
		user.setRole(role);
		userServiceImpl.signup(user);

	}
	


}
