package com.AirBnb.controler;

import com.AirBnb.payload.CityDto;
import com.AirBnb.service.Interface.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/airbnb/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    //Create City
    //http://localhost:8080/api/airbnb/city/create
    @PostMapping("/create")
    public ResponseEntity<CityDto> addCity(
            @RequestBody CityDto cityDto
    ){
        CityDto dto = cityService.addCity(cityDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/airbnb/city/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCity(
            @PathVariable long id
    ){
        cityService.deleteCityById(id);
        return new ResponseEntity<>("city deleted",HttpStatus.OK);
    }
}
