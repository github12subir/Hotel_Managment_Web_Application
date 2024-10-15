package com.AirBnb.repository;

import com.AirBnb.entity.City;
import com.AirBnb.entity.Property;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    //Search Query
    @Query("select p from Property p JOIN p.city c JOIN p.country co where c.name=:name or co.name=:name")
    List<Property> searchHotelByCityOrCountry(
            @Param("name") String name);



}