package com.AirBnb.service.Interface;

import com.AirBnb.payload.CountryDto;

public interface CountryService {
    CountryDto addCountry(CountryDto countryDto);

    void deleteCountryById(long id);
}
