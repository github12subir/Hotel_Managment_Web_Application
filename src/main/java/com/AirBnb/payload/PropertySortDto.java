package com.AirBnb.payload;

import lombok.Data;

import java.util.List;

@Data
public class PropertySortDto<P> {

    private List<PropertyDto> dto;

    private int pageNo;

    private int pageSize;

    //private String sortBy;

    //private String sortOrder;
}
