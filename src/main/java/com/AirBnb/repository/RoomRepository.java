package com.AirBnb.repository;

import com.AirBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.property.id=:propertyId")
    List<Room>  findAllRoomsByPropertyId(@Param("propertyId") Long id);

    @Query("select r from Room r where r.roomType=:roomType")
    List<Room>  findAllRoomsByRoomType(@Param("roomType") String roomType);

}