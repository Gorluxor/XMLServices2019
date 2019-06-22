package com.megatravel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.models.admin.User;


public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
