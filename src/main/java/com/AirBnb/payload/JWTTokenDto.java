package com.AirBnb.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTTokenDto {
    private String tokenType;
    private  String token;

}
