package com.megatravel.repository;

import com.megatravel.models.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
