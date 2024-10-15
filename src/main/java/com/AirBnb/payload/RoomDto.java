package com.AirBnb.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomDto {

    private Long id;

    private String roomType;

    @Size(min = 3,message = "Room Number should be more than 2 digits")
    private String roomNumber;

    @Size(min = 1,message = "Number of Bed should be at least one ")
    private Integer noOfBedRooms;

    @Size(min = 1,message = "Number of BathRoom should be at least one")
    private Integer noOfBathRooms;

    @Size(min = 1,message = "Number of Guest should be at least one")
    private Integer noOfGuest;

    @Size(min = 1,message = "Number of Bed should be at least one")
    private Integer noOfBeds;

    private Double nightlyPrice;

    private String message;
}
