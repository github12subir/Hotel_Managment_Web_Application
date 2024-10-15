package com.AirBnb.controler;

import com.AirBnb.payload.AppUserDto;
import com.AirBnb.payload.JWTTokenDto;
import com.AirBnb.service.Interface.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/airbnb/auth")
public class AuthController {
    private AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    //We create Admin-Role inbuilt(go db create Admin details )-> we can not create Admin Automatically
    //Create & encrypt Admin password through a class name "A" & print the password on Console
    // & copy it and paste it into Db as an Admin password

    //Create User Account
    //http://localhost:8080/api/airbnb/auth/signUpAsUser
    @PostMapping("/signUpAsUser")
    public ResponseEntity<AppUserDto> signUpAsUser(
            @RequestBody AppUserDto appUserDto
    ){

        appUserDto.setRole("ROLE_USER");
        AppUserDto userDto = appUserService.signUp(appUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //Create Owner Account
    //http://localhost:8080/api/airbnb/auth/signUpAsOwner
    @PostMapping("/signUpAsOwner")
    public ResponseEntity<AppUserDto> signUpAsOwner(
            @RequestBody AppUserDto appUserDto
    ){

        appUserDto.setRole("ROLE_OWNER");
        AppUserDto userDto = appUserService.signUp(appUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //Create Property-Manager Account
    //http://localhost:8080/api/airbnb/auth/signUpAsPropertyManager
    @PostMapping("/signUpAsPropertyManager")
    public ResponseEntity<AppUserDto> signUpAsPropertyManager(
            @RequestBody AppUserDto appUserDto
    ){

        appUserDto.setRole("ROLE_MANAGER");
        AppUserDto userDto = appUserService.signUp(appUserDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //Login As an Any-Role
    //http://localhost:8080/api/airbnb/auth/signIn
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(
        @RequestBody AppUserDto appUserDto
    ){
        String token=appUserService.signIn(appUserDto);
        if(token!=null){
            JWTTokenDto jwtTokenDto=new JWTTokenDto();
            jwtTokenDto.setTokenType("JWT");
            jwtTokenDto.setToken(token);
            return  new ResponseEntity<>(jwtTokenDto,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid credential",HttpStatus.UNAUTHORIZED);
        }
    }
}
