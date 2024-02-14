package com.wisercat.filters.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {

    private Long id;
    private String name;
    private List<CriteriaDto> criteriaList;
    private Date createdAt;
}
