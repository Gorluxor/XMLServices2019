package com.megatravel.repository;

import com.megatravel.models.rating.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {

//    Page<Rating> findAllByReservation_IdAndAdminApprovedIsTrue(Long accommodationId, Pageable pageable);
//
//    List<Rating> findAllByReservation_IdAndAdminApprovedIsTrue(Long accommodationId);

    @Query(value = "select r.* from rating r \n" +
            "\t inner join reservation rr\n" +
            "\t on r.reservation_id = rr.id \n" +
            "\t inner join accommodation_unit au \n" +
            "\t on au.id = (select MIN(rau.accommodation_unit_id) from reservation_accommodation_unit rau)\n" +
            "\t where au.accommodation_id = ?1 and r.admin_approved = true;", nativeQuery = true)
    Page<Rating> forAccommodation(Long accommodationId, Pageable pageable);

    @Query(value = "select r.* from rating r \n" +
            "\t inner join reservation rr\n" +
            "\t on r.reservation_id = rr.id \n" +
            "\t inner join accommodation_unit au \n" +
            "\t on au.id = (select MIN(rau.accommodation_unit_id) from reservation_accommodation_unit rau)\n" +
            "\t where au.accommodation_id = ?1 and r.admin_approved = true;", nativeQuery = true)
    List<Rating> forAccommodation(Long accommodationId);

    @Query(value = " select IFNULL(AVG(r.rating_value),0) from rating r \n" +
            "\t inner join reservation rr\n" +
            "\t on r.reservation_id = rr.id \n" +
            "\t inner join accommodation_unit au \n" +
            "\t on au.id = (select MIN(rau.accommodation_unit_id) from reservation_accommodation_unit rau)\n" +
            "\t where au.accommodation_id = ?1 and r.admin_approved = true;", nativeQuery = true)
    Double forAccommodationAverage(Long accommodationId);


    List<Rating> findAllByAdminApprovedIsFalse();

//    @Query("select r from Rating r where r.reservation.accommodation in (select res.id from Reservation res where )")
//    Page<Rating> forAccommodationUnit(Long accommodationUnitId, Pageable pageable);




//    @Query("Select AVG(r.ratingValue) from Rating r where r.adminApproved = TRUE and r.reservation.id = ?1")
//    Double average(Long reservationId);

}
