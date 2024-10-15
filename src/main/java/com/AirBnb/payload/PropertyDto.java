package com.AirBnb.payload;

import com.AirBnb.entity.City;
import com.AirBnb.entity.Country;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PropertyDto {
    private Long id;

    @Size(min = 2,message = "Name should be more than 1 characters")
    private String name;

    private String address;

    private String description;

    private String message;

}
