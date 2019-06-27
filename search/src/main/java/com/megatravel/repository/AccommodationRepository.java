package com.megatravel.repository;

import com.megatravel.models.agent.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {



    @Query(value = "  select * from accommodation qs where qs.id  in \n" +
            " ( select a.accommodation_id from accommodation_unit a where a.id and a.unit_type_id = 1 in (select q.id from accommodation_unit q where q.location_id in \n" +
            " (select l.id from location l where l.street like CONCAT('%',?1,'%') or l.city like CONCAT('%',?1,'%'))) \n" +
            " and exists (select * from accommodation_unit au where au.id = a.id and \n" +
            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))\n" +
            " group by a.accommodation_id \n" +
            " having sum(a.capacity) > ?4 \n" +
            " )", nativeQuery = true)
    List<Accommodation> normalSearch(String addressOrCity, String startDate, String endDate, int totalCapacity);


//    @Query(value = "select * from accommodation qs where qs.accommodation_type_id = ?5 and ((?7 = true and qs.free_to_cancel = ?7 and qs.free_to_cancel_days >= ?8) or (?7 = false))  and (qs.category = ?6 or \"all\" = ?6) and qs.id  in" +
//            " ( select a.accommodation_id from accommodation_unit a where a.id  in (select q.id from accommodation_unit q where q.location_id in \n" +
//            "(select l.id from location l where l.street like CONCAT('%', ?1, '%') or l.city like CONCAT('%', ?1, '%'))) \n" +
//            "and exists (select * from accommodation_unit au where au.id = a.id and \n" +
//            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
//            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
//            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
//            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
//            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
//            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))\n" +
//            " group by a.accommodation_id \n" +
//            " having sum(a.capacity) > ?4\n" +
//            " )", nativeQuery = true)
    @Query(value = " select * from accommodation qs where qs.accommodation_type_id = ?5 and ((?7 = true and qs.free_to_cancel = ?7 and qs.free_to_cancel_days >= 5) or (?7=false)) and (qs.category = ?6 or ?6 = \"all\") and qs.id  in \n" +
            " ( select a.accommodation_id from accommodation_unit a where a.id and a.unit_type_id = 1 in (select q.id from accommodation_unit q where q.location_id in \n" +
            "(select l.id from location l where l.street like CONCAT('%',?1,'%') or l.city like CONCAT('%',?1,'%'))) \n" +
            "and exists (select * from accommodation_unit au where au.id = a.id and \n" +
            " au.id not in (select rau.accommodation_unit_id from reservation_accommodation_unit rau \n" +
            " inner join reservation r on r.id = rau.reservation_id where rau.accommodation_unit_id in\n" +
            " (select accu.id from accommodation_unit accu where accu.accommodation_id = au.accommodation_id) and \n" +
            " ((( r.arrival_date>= ?2 and r.departure_date <= ?3) or\n" +
            " (r.arrival_date >= ?2 and r.arrival_date <= ?3) or \n" +
            " (r.departure_date >= ?2 and r.departure_date <= ?3)))))\n" +
            " group by a.accommodation_id \n" +
            " having sum(a.capacity) > ?4\n" +
            " );", nativeQuery = true)
    List<Accommodation> advancedSearch(String addressOrCity, String startDate, String endDate, int totalCapacity, long unitTypeId, String category, Boolean freeToCancel, int freeToCancelDays);

}
