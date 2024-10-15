package com.AirBnb.service.Interface;

import com.AirBnb.payload.AppUserDto;

public interface AppUserService {

    AppUserDto signUp(AppUserDto appUserDto);

    String signIn(AppUserDto appUserDto);
}
