package com.AirBnb.service.Interface;

import com.AirBnb.payload.PropertyDto;
import com.AirBnb.payload.PropertySortDto;

import java.util.List;

public interface PropertyService {

    PropertyDto addProperty(PropertyDto propertyDto,long cityId, long countryId);

    PropertyDto updateProperty(PropertyDto propertyDto,long propertyId);

    void deleteProperty(long propertyId);

    List<PropertyDto> searchPropertyByCityOrCountry(String name);

    PropertyDto searchPropertyById(long propertyId);

    PropertySortDto<PropertyDto> getAllProperties(int pageNo, int pageSize, String sortBy, String sortDir);
}
