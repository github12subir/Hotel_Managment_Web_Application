package com.AirBnb.controler;

import com.AirBnb.payload.PropertyDto;
import com.AirBnb.payload.PropertySortDto;
import com.AirBnb.service.Interface.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airbnb/property")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //http://localhost:8080/api/airbnb/property/create/{cityId}/{countryId}
    @PostMapping("/create/{cityId}/{countryId}")
    public ResponseEntity<PropertyDto> addProperty(
            @RequestBody PropertyDto propertyDto,
            @PathVariable long cityId,
            @PathVariable long countryId
    ){
        PropertyDto dto = propertyService.addProperty(propertyDto, cityId, countryId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //Update Property
    //http://localhost:8080/api/airbnb/property/update/{propertyId}
    @PutMapping("/update/{propertyId}")
    public ResponseEntity<PropertyDto> updateProperty(
            @RequestBody PropertyDto propertyDto,
            @PathVariable long propertyId
    ){
        PropertyDto dto = propertyService.updateProperty(propertyDto, propertyId);
        return  new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //Delete Property
    //http://localhost:8080/api/airbnb/property/delete/{propertyId}
    @DeleteMapping("/delete/{propertyId}")
    public ResponseEntity<String> deletePropertyById(
            @PathVariable long propertyId
    ){
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>("Your Property is Successfully Deleted!",HttpStatus.OK);
    }

    // Get  all Properties By Name of City or Country
    //http://localhost:8080/api/airbnb/property/getProperties/{name}
    @GetMapping("/getProperties/{name}")
    public  ResponseEntity<List<PropertyDto>> searchPropertyByName(
            @PathVariable String name
            ){

        List<PropertyDto> dtos = propertyService.searchPropertyByCityOrCountry(name);
        return  new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    // Get property By PropertyId
    //http://localhost:8080/api/airbnb/property/getProperty/{propertyId}
    @GetMapping("/getProperty/{propertyId}")
    public ResponseEntity<PropertyDto> searchProperty(
            @PathVariable long propertyId
    ){
        PropertyDto dto = propertyService.searchPropertyById(propertyId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    // Get all properties Using Pagination Concept
    //http://localhost:8080/api/airbnb/property/getAllProperties
    @GetMapping("/getAllProperties")
    public ResponseEntity<PropertySortDto<PropertyDto>> getAllProperties(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "name",required = false) String sortDir
    ){
        PropertySortDto<PropertyDto> allProperties = propertyService.getAllProperties(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allProperties,HttpStatus.OK);
    }
}
