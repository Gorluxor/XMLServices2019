package com.megatravel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.dtos.admin.RoleDTO;
import com.megatravel.models.admin.Role;
import com.megatravel.repository.RoleRepository;

@Component
public class RoleService {
	@Autowired
	RoleRepository roleRepository;

	public List<RoleDTO> findAll(Pageable page) {
		Page<Role> roles = roleRepository.findAll(page);
		if(roles.hasContent()) {
			List<RoleDTO> retVal = new ArrayList<RoleDTO>();
			for (Role role : roles) {
				retVal.add(new RoleDTO(role));
			}
			return retVal;
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested page is empty.");
		}
	}

	public RoleDTO findOne(Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if(role.isPresent()) {
			return new RoleDTO(role.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + id + " doesn't exist.");
		}
	}
	
	public Role findOneRole(Long id) {
		Optional<Role> role = roleRepository.findById(id);
		if(role.isPresent()) {
			return role.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + id + " doesn't exist.");
		}
	}

	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	public Role update(Long id, Role role) {
		Optional<Role> foundRole = roleRepository.findById(id);
		if(foundRole.isPresent()) {
			Role tempRole = foundRole.get();
			tempRole.setRoleName(role.getRoleName());

			return roleRepository.save(tempRole);
		}
		else {

			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Requested role with id " + id + " doesn't exist.");
		}
		
	}

	public void remove(Long id) {
		roleRepository.deleteById(id);
	}
	
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

}
