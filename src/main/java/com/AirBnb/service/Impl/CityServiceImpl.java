package com.AirBnb.service.Impl;

import com.AirBnb.entity.City;
import com.AirBnb.payload.CityDto;
import com.AirBnb.repository.CityRepository;
import com.AirBnb.service.Interface.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto addCity(CityDto cityDto) {

//        Optional<City> opCity = cityRepository.findById(cityDto.getId());
//        if (opCity.isPresent()){
//            throw new RuntimeException("City is Already Exists");
//       }
        City cityEntity = mapToEntity(cityDto);
        City saveEntity = cityRepository.save(cityEntity);
        CityDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("city saved");
        return saveDto;
    }

    @Override
    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }

    City mapToEntity(CityDto dto){
        City city= new City();
       // city.setId(dto.getId());
        city.setName(dto.getName());
        return city;
    }

    CityDto mapToDto(City city){
        CityDto dto= new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        return dto;
    }
}
