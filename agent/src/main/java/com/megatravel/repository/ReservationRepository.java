package com.megatravel.repository;

import com.megatravel.models.reservations.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserId(Long id);

    @Query(value = "select r.* from reservation r join reservation_accommodation_unit rau on r.id = rau.reservation_id where rau.accommodation_unit_id in \n" +
            " (select a.id from accommodation_unit a where a.accommodation_id in (select aq.id from accommodation aq where aq.user_id = ?1))", nativeQuery = true)
    List<Reservation> allByAgent(Long id);

    @Query(value = "select r.* from reservation r join reservation_accommodation_unit rau on r.id = rau.reservation_id where r.stay_realized = false and rau.accommodation_unit_id in \n" +
            " (select a.id from accommodation_unit a where a.accommodation_id in (select aq.id from accommodation aq where aq.user_id = ?1))", nativeQuery = true)
    List<Reservation> allUnrealizedByAgent(Long id);

}
