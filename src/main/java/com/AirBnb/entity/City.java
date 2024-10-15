package com.AirBnb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    //Mapping

    //One City is Associate with Several properties
    @OneToMany(mappedBy = "city",cascade = CascadeType.ALL)
    Set<Property> properties= new HashSet<>();

}