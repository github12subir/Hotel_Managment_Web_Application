package com.AirBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address", nullable = false, length = 1000)
    private String address;

    @Column(name = "description", nullable = false, length = 2000)
    private String description;


//    @Column(name = "no_of_bathrooms", nullable = false)
//    private Integer noOfBathroom;
//
//    @Column(name = "no_of_bed_rooms", nullable = false)
//    private Integer noOfBedRoom;
//
//    @Column(name = "no_of_beds", nullable = false)
//    private Integer noOfBeds;
//
//    @Column(name = "no_of_guests", nullable = false)
//    private Integer noOfGuests;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new LinkedHashSet<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new LinkedHashSet<>();


}