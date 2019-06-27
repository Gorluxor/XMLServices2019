package com.megatravel.repository;

import com.megatravel.models.agent.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitTypeRepository extends JpaRepository<UnitType, Long> {

    UnitType findByNameOfUnitType(String name);
}
