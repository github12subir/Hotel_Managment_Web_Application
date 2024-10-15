package com.AirBnb.controler;

import com.AirBnb.payload.RoomDto;
import com.AirBnb.service.Interface.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airbnb/room")
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    //Add a Room in a  particular Property
    //http://localhost:8080/api/airbnb/room/create/{propertyId}
    @PostMapping("/create/{propertyId}")
    public ResponseEntity<RoomDto> addRoom(
            @RequestBody RoomDto roomDto,
            @PathVariable Long propertyId
    ){
        RoomDto dto = roomService.addRoom(roomDto, propertyId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //Edit the Room Details by roomId
    //http://localhost:8080/api/airbnb/room/EditRoomDetails/{roomId}
    @PutMapping("/EditRoomDetails/{roomId}")
    public ResponseEntity<RoomDto> editRoomDetails(
            @RequestBody RoomDto roomDto,
            @PathVariable Long roomId
    ){
        RoomDto dto = roomService.editRoomDetailsById(roomDto, roomId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Search a Room By roomId
    //http://localhost:8080/api/airbnb/room/searchRoomByRoomId/{roomId}
    @GetMapping("/searchRoomByRoomId/{roomId}")
    public ResponseEntity<RoomDto> searchRoomByRoomId(
            @PathVariable Long roomId
    ){
        RoomDto dto = roomService.searchRoomByRoomId(roomId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Search All the Room of a Particular Property by the PropertyId
    //http://localhost:8080/api/airbnb/room/searchAllRoomsByPropertyId/{propertyId}
    @GetMapping("/searchAllRoomsByPropertyId/{propertyId}")
    public ResponseEntity<List<RoomDto>> searchAllRoomsByPropertyId(
            @PathVariable Long propertyId
    ){
        List<RoomDto> dtos = roomService.searchAllRoomsByPropertyId(propertyId);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }


    //Search all the Rooms By roomType
    //http://localhost:8080/api/airbnb/room/searchAllRoomsByRoomType/{roomType}
    @GetMapping("/searchAllRoomsByRoomType/{roomType}")
    public ResponseEntity<List<RoomDto>> searchRoomByRoomType(
            @PathVariable String roomType
    ){
        List<RoomDto> dto = roomService.searchRoomByRoomType(roomType);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Search All The Rooms Base on Price
    //http://localhost:8080/api/airbnb/room/searchAllRoomsByPrice/{price}
    @GetMapping("/searchAllRoomsByPrice/{price}")
    public ResponseEntity<List<RoomDto>> searchRoomByPrice(
            @PathVariable Double price
    ){
        List<RoomDto> dto = roomService.searchRoomByPrice(price);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //Remove a room By roomId from database
    //http://localhost:8080/api/airbnb/room/removeRoomByRoomId/{roomId}
    @DeleteMapping("/removeRoomByRoomId/{roomId}")
    public ResponseEntity<String>  removeRoomByRoomId(
            @PathVariable Long roomId
    ){
        roomService.removeRoomByRoomId(roomId);
        return new ResponseEntity<>("Successfully, Your Room Removed!",HttpStatus.OK);
    }


}
