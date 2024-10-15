package com.AirBnb.service.Impl;

import com.AirBnb.entity.Property;
import com.AirBnb.entity.Room;
import com.AirBnb.exception.PropertyNotFound;
import com.AirBnb.exception.RoomNotExist;
import com.AirBnb.payload.RoomDto;
import com.AirBnb.repository.PropertyRepository;
import com.AirBnb.repository.RoomRepository;
import com.AirBnb.service.Interface.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;
    public RoomServiceImpl(RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }

    //Add a Room in a particular Property
    @Override
    public RoomDto addRoom(RoomDto roomDto, Long propertyId) {
        Room room = mapToEntity(roomDto);
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFound("Property Not Exist!"));
        room.setProperty(property);
        Room saveEntity = roomRepository.save(room);
        RoomDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Room Successfully added");
        return saveDto;
    }

    //Edit a room
    @Override
    public RoomDto editRoomDetailsById(RoomDto roomDto, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotExist("Room Not Exist!"));

        room.setRoomNumber(roomDto.getRoomNumber());
        room.setRoomType(roomDto.getRoomType());
        room.setNoOfBedRooms(roomDto.getNoOfBedRooms());
        room.setNoOfBeds(roomDto.getNoOfBeds());
        room.setNoOfBathRooms(roomDto.getNoOfBathRooms());
        room.setNoOfGuest(roomDto.getNoOfGuest());
        room.setNightlyPrice(roomDto.getNightlyPrice());
        Room saveEntity = roomRepository.save(room);
        RoomDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Successfully, Your room is Edited!");
        return saveDto;
    }

    //Search a Room By roomId
    @Override
    public RoomDto searchRoomByRoomId(Long roomId) {
        Room roomEntity = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotExist("Room Not Exist!"));
        RoomDto roomDto = mapToDto(roomEntity);
        return roomDto;
    }

    //Search All the Rooms By propertyId
    @Override
    public List<RoomDto> searchAllRoomsByPropertyId(Long propertyId) {

        List<Room> roomsEntity = roomRepository.findAllRoomsByPropertyId(propertyId);
        List<RoomDto> roomsDto = roomsEntity.stream().map(room -> mapToDto(room)).collect(Collectors.toList());

        return roomsDto;
    }

    //Search All the Rooms By roomType
    @Override
    public List<RoomDto> searchRoomByRoomType(String roomType) {

        List<Room> roomsEntity = roomRepository.findAllRoomsByRoomType(roomType);
        List<RoomDto> roomsDto = roomsEntity.stream().map(room -> mapToDto(room)).collect(Collectors.toList());

        return roomsDto;

    }

    @Override
    public List<RoomDto> searchRoomByPrice(Double price) {

        //Get All rooms from database
        List<Room> allRooms = roomRepository.findAll();
        //Filter all Rooms Those Room's price is Less than equal to the price which supply through Json
        List<Room> roomsEntity = allRooms.stream().filter(room -> room.getNightlyPrice() <= price).collect(Collectors.toList());
       //map the list of Entity of Rooms into Dto
        List<RoomDto> roomsDto = roomsEntity.stream().map(room -> mapToDto(room)).collect(Collectors.toList());
        return roomsDto;

    }

    //Remove a Room By the roomId from database
    @Override
    public void removeRoomByRoomId(Long roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomNotExist("Room Not Exist!"));
        roomRepository.delete(room);
    }

    Room mapToEntity(RoomDto dto){
        Room entity=new Room();
        entity.setId(dto.getId());
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setRoomType(dto.getRoomType());
        entity.setNoOfBedRooms(dto.getNoOfBedRooms());
        entity.setNoOfBeds(dto.getNoOfBeds());
        entity.setNoOfBathRooms(dto.getNoOfBathRooms());
        entity.setNoOfGuest(dto.getNoOfGuest());
        entity.setNightlyPrice(dto.getNightlyPrice());

        return entity;
    }

    RoomDto mapToDto(Room entity){
        RoomDto dto=new RoomDto();
        //dto.setId(entity.getId());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setRoomType(entity.getRoomType());
        dto.setNoOfBedRooms(entity.getNoOfBedRooms());
        dto.setNoOfBeds(entity.getNoOfBeds());
        dto.setNoOfBathRooms(entity.getNoOfBathRooms());
        dto.setNoOfGuest(entity.getNoOfGuest());
        dto.setNightlyPrice(entity.getNightlyPrice());

        return dto;
    }
}
