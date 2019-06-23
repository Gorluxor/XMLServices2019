package com.megatravel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.dtos.admin.RoleDTO;
import com.megatravel.models.admin.Role;
import com.megatravel.service.RoleServiceImpl;

@SuppressWarnings("Duplicates")
@RestController
@CrossOrigin
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<RoleDTO>> getAllRoles(Pageable pageable) {
		List<com.megatravel.dtos.admin.RoleDTO> found = roleServiceImpl.findAll(pageable);
		HttpHeaders headers = new HttpHeaders();
		long rolesTotal = found.size();
		headers.add("X-Total-Count", String.valueOf(rolesTotal));
		
		return new ResponseEntity<List<RoleDTO>>(found, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<RoleDTO> getRole(@PathVariable("id") Long id) {
		return new ResponseEntity<RoleDTO>(roleServiceImpl.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
		return new ResponseEntity<RoleDTO>(new RoleDTO(roleServiceImpl.save(new Role(roleDTO))), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO){
		return new ResponseEntity<RoleDTO>(new RoleDTO(roleServiceImpl.update(id, new Role(roleDTO))), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteRole(@PathVariable("id") Long id) {
		roleServiceImpl.remove(id);
		return ResponseEntity.ok().build();
	}
	

}
