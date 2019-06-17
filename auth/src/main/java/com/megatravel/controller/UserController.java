package com.megatravel.controller;

import java.util.List;

import com.megatravel.dtos.admin.LoginDTO;
import com.megatravel.dtos.admin.RegistrationDTO;
import com.megatravel.dtos.admin.UserDTO;

import com.megatravel.models.admin.Role;
import com.megatravel.models.admin.User;
import com.megatravel.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;



@RestController
@RequestMapping(value = "/api")
public class UserController {
	
	@Autowired
    UserServiceImpl userServiceImpl;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<>(userServiceImpl.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		return new ResponseEntity<>(userServiceImpl.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "user/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<>(new UserDTO(userServiceImpl.findByEmail(email)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

		User user = userServiceImpl.findByEmail(loginDTO.getEmail());
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			String jwt = userServiceImpl.signin(loginDTO.getEmail(), loginDTO.getPassword());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			//e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ResponseEntity<Void> signup(@RequestBody RegistrationDTO registrationDTO) {

		if (!registrationDTO.getRepeatPassword().equals(registrationDTO.getPassword())) {
			//not the same passwords
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		
		User tempKorisnik = userServiceImpl.findByEmail(registrationDTO.getEmail());
		if(tempKorisnik != null) {
			//unique mail for the user
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		user.setEmail(registrationDTO.getEmail());
		user.setPassword(registrationDTO.getPassword());
		user.setName(registrationDTO.getName());
		user.setLastName(registrationDTO.getLastName());
		Role role = new Role();
		role.setId(1L);
		user.setRole(role);
		userServiceImpl.signup(user);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/*@PreAuthorize("hasAnyRole('ROLE_Registrovani_korisnik')")
	@RequestMapping(value = "api", method = RequestMethod.GET)
	public ResponseEntity<Void> pomocna( HttpServletRequest req) {
		
		String token = jwtTokenUtils.resolveToken(req);
		Korisnik korisnik = null;
		
		if(token != null) {
			String email = jwtTokenUtils.getUsername(token);
			korisnik = korisnikService.findByEmail(email);
		}

		if(korisnik == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}*/
	
}

