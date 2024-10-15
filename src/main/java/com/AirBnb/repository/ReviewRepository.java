package com.AirBnb.repository;

import com.AirBnb.entity.AppUser;
import com.AirBnb.entity.Property;
import com.AirBnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.appUser=:user and r.property=:property")
    Review findUserAndProperty(
            @Param("user") AppUser appUser,
            @Param("property")Property property
            );

    @Query("select r from Review r where r.appUser=:user")
    List<Review> getReviewSByUser(
            @Param("user") AppUser appUser
    );

}