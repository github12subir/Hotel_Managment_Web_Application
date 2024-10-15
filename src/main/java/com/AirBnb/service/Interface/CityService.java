package com.AirBnb.service.Interface;

import com.AirBnb.payload.CityDto;

public interface CityService {
    CityDto addCity(CityDto cityDto);

    void deleteCityById(long id);
}
