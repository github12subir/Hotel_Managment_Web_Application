package com.AirBnb.controler;

import com.AirBnb.payload.CountryDto;
import com.AirBnb.service.Interface.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airbnb/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("create")
    public ResponseEntity<CountryDto> addCountry(
            @RequestBody CountryDto countryDto
    ){
        CountryDto dto = countryService.addCountry(countryDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteCountry(
            @PathVariable long id
    ){
        countryService.deleteCountryById(id);
        return new ResponseEntity<>(" country deleted",HttpStatus.OK);
    }
}
