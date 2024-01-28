package com.wisercat.filtersapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FilterDto {

    private Long id;
    private String name;

    private List<CriteriaDto> criteriaList;
    private Date createdAt;

    public FilterDto() {
    }

    public FilterDto(Long id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }
}
