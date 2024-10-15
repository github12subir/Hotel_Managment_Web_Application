package com.AirBnb.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDto {
    private Long id;

    @Size(min = 3,message = "Name should be more than 2 characters")
    private String name;

    private String message;
}
