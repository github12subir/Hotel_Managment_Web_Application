package com.AirBnb.repository;

import com.AirBnb.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    //Custom Query

    //Show the Booking Details By Booking id
    @Query("select b from Booking b where b.id=:bookingId")
    Optional<Booking> findByBookingId(@Param("bookingId") String bookingId);

    //Show all the Booking details by user
    @Query("select b from Booking b where b.appUser.id=:userId")
    List<Booking> findAllBookingsByUser(@ Param("userId") Long id);
}
