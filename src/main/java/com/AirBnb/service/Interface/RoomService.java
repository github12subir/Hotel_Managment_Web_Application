package com.AirBnb.service.Interface;

import com.AirBnb.payload.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto addRoom(RoomDto roomDto, Long propertyId);

    RoomDto editRoomDetailsById(RoomDto roomDto, Long roomId);

    RoomDto searchRoomByRoomId(Long roomId);

    List<RoomDto> searchAllRoomsByPropertyId(Long propertyId);

    List<RoomDto> searchRoomByRoomType(String roomType);

    List<RoomDto> searchRoomByPrice(Double price);

    void removeRoomByRoomId(Long roomId);
}
