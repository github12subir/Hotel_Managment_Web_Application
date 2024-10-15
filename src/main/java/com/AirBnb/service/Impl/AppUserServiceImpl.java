package com.AirBnb.service.Impl;

import com.AirBnb.entity.AppUser;
import com.AirBnb.exception.UserExist;
import com.AirBnb.payload.AppUserDto;
import com.AirBnb.repository.AppUserRepository;
import com.AirBnb.service.Interface.AppUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {
    private AppUserRepository appUserRepository;
    private JWTService jwtService;
    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }


    @Override
    public AppUserDto signUp(AppUserDto appUserDto) {

        //Check this Email Exists or not Database
       Optional<AppUser> opEmial=appUserRepository.findByEmail(appUserDto.getEmail());
        if (opEmial.isPresent()){
            //if EmailId already Exists then Throw an Exception
            throw new UserExist("This Email is already Exist!");
        }

        Optional<AppUser> opUser=appUserRepository.findByUsername(appUserDto.getUsername());
        if (opUser.isPresent()){
            throw new UserExist("This Username is already Exist!");
        }

        //EnCrypt the password & Stored it into String
        String hashpw = BCrypt.hashpw(appUserDto.getPassword(), BCrypt.gensalt(10));
        //Set the Encrypted-Password into Dto
        appUserDto.setPassword(hashpw);
        //Converted the Dto into Entity
        AppUser appUser = mapToEntity(appUserDto);
        //Saved the Password into Database Through Repository
        AppUser saveEntity = appUserRepository.save(appUser);
        //Converted the save-password into Dto
        AppUserDto saveDto = mapToDto(saveEntity);
        //set the message into Dto
        saveDto.setMessage("successfully SignUp");
        //return Dto
        return saveDto;
    }

    @Override
    public String signIn(AppUserDto appUserDto) {

        //check this UserName Exists or note
        Optional<AppUser> opUser = appUserRepository.findByUsername(appUserDto.getUsername());
        if(opUser.isPresent()){
            //if Username Exists then get the Entity
            AppUser appUser = opUser.get();
            //check two password(user given & taken from DB) is same or not
            if(BCrypt.checkpw( appUserDto.getPassword(),appUser.getPassword())){
                //if password matched->then generate a Token for that user
                String token = jwtService.generateToken(appUser);
                //return that Token
                return token;
            }
        }
        //if username not Exists & Password not matched-> Then return null
        return null;

    }


    AppUser mapToEntity(AppUserDto appUserDto){
        AppUser appUser=new AppUser();
        appUser.setId(appUserDto.getId());
        appUser.setName(appUserDto.getName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(appUserDto.getPassword());
        appUser.setRole(appUserDto.getRole());
        return appUser;
    }

    AppUserDto mapToDto(AppUser appUser){
        AppUserDto dto=new AppUserDto();
        dto.setId(appUser.getId());
        dto.setName(appUser.getName());
        dto.setEmail(appUser.getEmail());
        dto.setUsername(appUser.getUsername());
        //dto.setPassword(appUser.getPassword());
        dto.setRole(appUser.getRole());
        return dto;
    }

}
