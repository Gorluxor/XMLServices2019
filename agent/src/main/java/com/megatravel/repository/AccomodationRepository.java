package com.megatravel.repository;

import com.megatravel.models.agent.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccomodationRepository extends JpaRepository<Accommodation,Long> {
}
