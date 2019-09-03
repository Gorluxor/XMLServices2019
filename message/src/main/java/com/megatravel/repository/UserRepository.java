package com.megatravel.repository;

import com.megatravel.models.admin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findByRole_IdAndAccommodationIsNull(Long id);

}
