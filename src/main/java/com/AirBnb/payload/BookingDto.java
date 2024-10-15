package com.AirBnb.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDto {

    private String id;

    @Size(min = 3,message = "room number should be more than 2 digits")
    private String roomNumber;


    private LocalDate checkIn;

    private LocalDate checkOut;

    @Size(min = 1,message = "Total number of guest should be minimum 1 ")
    private Integer totalNoOfGuest;

    @Size(min = 3,message = "Name should be more than 2 characters")
    private String name;

    @Email(message = "invalid Email Id")
    private String email;

    @Size(min = 10,message = "PhNumbers should be exactly 10 digits ")
    private String phNumber;


    private Double totalPrice;

    private String message;
}
