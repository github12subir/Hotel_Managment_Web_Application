package com.AirBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "no_of_bed_rooms", nullable = false)
    private Integer noOfBedRooms;

    @Column(name = "no_of_bath_rooms", nullable = false)
    private Integer noOfBathRooms;

    @Column(name = "no_of_guest", nullable = false)
    private Integer noOfGuest;

    @Column(name = "no_of_beds", nullable = false)
    private Integer noOfBeds;

    @Column(name = "nightly_price", nullable = false)
    private Double nightlyPrice;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new LinkedHashSet<>();

    @ManyToOne()
    @JoinColumn(name = "property_id")
    private Property property;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Images> imageDataSet= new LinkedHashSet<>();



}