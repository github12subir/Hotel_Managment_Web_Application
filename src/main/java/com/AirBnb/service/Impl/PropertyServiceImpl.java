package com.AirBnb.service.Impl;

import com.AirBnb.entity.City;
import com.AirBnb.entity.Country;
import com.AirBnb.entity.Property;
import com.AirBnb.exception.PropertyNotFound;
import com.AirBnb.payload.PropertyDto;
import com.AirBnb.payload.PropertySortDto;
import com.AirBnb.repository.CityRepository;
import com.AirBnb.repository.CountryRepository;
import com.AirBnb.repository.PropertyRepository;
import com.AirBnb.service.Interface.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    public PropertyServiceImpl(PropertyRepository propertyRepository, CityRepository cityRepository, CountryRepository countryRepository) {
        this.propertyRepository = propertyRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public PropertyDto addProperty(PropertyDto propertyDto,long cityId, long countryId) {
        Property property = mapToEntity(propertyDto);

        //find the City is Exist or Not
        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("city Exist!"));
        property.setCity(city);//set the city into property

        //find the Country is Exist or Not
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new RuntimeException("Country Exist!"));
        property.setCountry(country);//set the Country into property

        Property saveEntity = propertyRepository.save(property);
        PropertyDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Yours Property is Successfully Added!");
        return saveDto;
    }

    @Override
    public PropertyDto updateProperty(PropertyDto propertyDto,long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property Doesn't Exist!"));
        //Update the Property
        property.setName(propertyDto.getName());
        property.setAddress(propertyDto.getAddress());
        property.setDescription(propertyDto.getDescription());
        Property saveEntity = propertyRepository.save(property);
        PropertyDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Yours Property is Successfully Updated!");
        return saveDto;
    }

    @Override
    public void deleteProperty(long propertyId) {
        propertyRepository.findById(propertyId).orElseThrow(()-> new RuntimeException("This Property is not Exit!"));
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public List<PropertyDto> searchPropertyByCityOrCountry(String name) {
        List<Property> properties = propertyRepository.searchHotelByCityOrCountry(name);
        List<PropertyDto> dtos = properties.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PropertyDto searchPropertyById(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new PropertyNotFound("In This Id Property Not Found"));
        PropertyDto propertyDto = mapToDto(property);
        return propertyDto;
    }

    @Override
    public PropertySortDto<PropertyDto> getAllProperties(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC,sortBy) : Sort.by(Sort.Direction.DESC,sortBy);
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Property> all = propertyRepository.findAll(pageable);
        List<Property> content = all.getContent();
        List<PropertyDto> dtos = content.stream().map(x -> mapToDto(x)).collect(Collectors.toList());

        //create one obj of PropertySortDto<PropertyDto> class
        //set all variable values of PropertySortDto on that obj
        //return that object properly
        PropertySortDto<PropertyDto> propertySortDto=new PropertySortDto();
        propertySortDto.setDto(dtos);
        propertySortDto.setPageNo(all.getTotalPages());
        propertySortDto.setPageSize(all.getSize());

        return propertySortDto;
    }

    Property mapToEntity(PropertyDto dto){
        Property property=new Property();
        property.setId(dto.getId());
        property.setName(dto.getName());
        property.setAddress(dto.getAddress());
        property.setDescription(dto.getDescription());
        return property;
    }

    PropertyDto mapToDto(Property entity){
        PropertyDto dto=new PropertyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setDescription(entity.getDescription());
        return dto;
    }



}
