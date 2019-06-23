package com.megatravel.repository;

import com.megatravel.models.agent.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> {
    AccommodationUnit findByAccommodation_IdAndId(Long accId, Long unitId);

}
