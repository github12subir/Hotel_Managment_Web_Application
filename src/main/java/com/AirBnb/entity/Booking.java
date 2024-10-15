package com.AirBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @Column(name = "total_no_of_guest", nullable = false)
    private Integer totalNoOfGuest;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "ph_number", nullable = false)
    private String phNumber;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;



    @ManyToOne()
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne()
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;



}