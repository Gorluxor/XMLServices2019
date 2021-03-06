package com.megatravel.repository;

import com.megatravel.models.agent.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

    List<Pricing> findAllByPriceForUnit_Id(Long id);

    Pricing findFirstByPriceForUnit_IdAndId(Long unitId, Long priceId);



    Pricing findFirstByPriceForUnit_IdAndStartDateBeforeOrderByStartDateDesc(Long unitId, Date date);

}
