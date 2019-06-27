package com.megatravel.repository;

import com.megatravel.models.agent.Accommodation;
import com.megatravel.models.agent.AccommodationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit, Long> {

    @Query(value = " select * from accommodation_unit a where a.id  in (select q.id from accommodation_unit q where q.location_id in \n" +
            " (select l.id from location l where l.street like CONCAT('%',?1,'%') or l.city like CONCAT('%',?1,'%'))) \n" +
            " and exists (select * from accommodation_unit au where au.id = a.id and \n" +
            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))", nativeQuery = true)
    List<AccommodationUnit> allNormalSearch(String addressOrCity, String startDate, String endDate, int totalCapacity);

    @Query(value = " select * from accommodation_unit a where a.id and a.unit_type_id = ?5 in (select q.id from accommodation_unit q where q.location_id in \n" +
            " (select l.id from location l where l.street like CONCAT('%',?1,'%') or l.city like CONCAT('%',?1,'%'))) \n" +
            " and exists (select * from accommodation_unit au where au.id = a.id and \n" +
            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))", nativeQuery = true)
    List<AccommodationUnit> allAdvanceSearch(String addressOrCity, String startDate, String endDate, int totalCapacity, long unitTypeId);


    @Query(value = " select * from accommodation_unit a where a.accommodation_id = ?4 and a.id in (select q.id from accommodation_unit q where q.location_id in \n" +
            " (select l.id from location l where l.street like CONCAT('%',?1,'%') or l.city like CONCAT('%',?1,'%'))) \n" +
            " and exists (select * from accommodation_unit au where au.id = a.id and \n" +
            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))", nativeQuery = true)
    List<AccommodationUnit> normalSearch(String addressOrCity, String startDate, String endDate, Long accId);


    @Query(value = " select * from accommodation_unit a where ((?5 = -1) or a.accommodation_id = ?5)  and a.unit_type_id = ?4 and a.id in (select q.id from accommodation_unit q where q.location_id in \n" +
            " (select l.id from location l where l.street like CONCAT('%',?1,'%') or l.city like CONCAT('%',?1,'%'))) \n" +
            " and exists (select * from accommodation_unit au where au.id = a.id and \n" +
            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))", nativeQuery = true)
    List<AccommodationUnit> advanceSearch(String addressOrCity, String startDate, String endDate, long unitTypeId, Long id);



}
