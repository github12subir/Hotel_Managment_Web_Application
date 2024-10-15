package com.AirBnb.payload;

import com.AirBnb.entity.AppUser;
import com.AirBnb.entity.Property;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReviewDto {
    private Long id;


    private String rating;

    private String description;

    private String message;

    private Property property;

    private AppUser appUser;
}
