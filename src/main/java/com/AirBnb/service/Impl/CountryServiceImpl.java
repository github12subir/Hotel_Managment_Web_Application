package com.AirBnb.service.Impl;

import com.AirBnb.entity.Country;
import com.AirBnb.payload.CountryDto;
import com.AirBnb.repository.CountryRepository;
import com.AirBnb.service.Interface.CountryService;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {

        Country country = mapToEntity(countryDto);
        Country saveEntity = countryRepository.save(country);
        CountryDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Country saved");
        return saveDto;

    }

    @Override
    public void deleteCountryById(long id) {
        countryRepository.deleteById(id);
    }

    Country mapToEntity(CountryDto dto){
        Country country= new Country();
        country.setId(dto.getId());
        country.setName(dto.getName());
        return country;
    }

    CountryDto mapToDto(Country country){
        CountryDto dto= new CountryDto();
        dto.setId(country.getId());
        dto.setName(country.getName());
        return dto;
    }
}
