package com.AirBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    //Mapping
    //one Country isw Associate with several Properties
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    Set<Property> properties= new HashSet<>();

}