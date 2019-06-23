package com.megatravel.repository;

import com.megatravel.models.agent.Accommodation;
import com.megatravel.models.agent.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {


    AccommodationUnit findFirstByAccommodation_Id(Long id);

}
