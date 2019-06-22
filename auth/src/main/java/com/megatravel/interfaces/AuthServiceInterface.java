package com.megatravel.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.megatravel.dtos.admin.LoginDTO;
import com.megatravel.dtos.admin.RegistrationDTO;

@WebService()
public interface AuthServiceInterface {

	@WebMethod(operationName = "testMethod")
	String testMethod();
	
	@WebMethod(operationName = "login")
	String login(@WebParam(name = "loginDTO") LoginDTO loginDTO);
	
	@WebMethod(operationName = "signup")
	void signup(@WebParam(name = "registrationDTO") RegistrationDTO registrationDTO);
	
}
