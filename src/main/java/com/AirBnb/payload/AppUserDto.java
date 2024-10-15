package com.AirBnb.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppUserDto {
    private long id;

    @Size(min=3,message="Should be more than 2 characters")
    private String name;

    @Email(message="Invalid email format")
    private  String email;

    @Size(min=4,message = "Username must be more than 3 characters")
    private String username;

    @Size(min=8,max=20,message = "Password should be more than 7 & less than 21")
    private String password;

    private String role;

    private  String message;
}
