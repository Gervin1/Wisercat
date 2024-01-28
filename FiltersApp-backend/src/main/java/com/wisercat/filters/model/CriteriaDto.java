package com.wisercat.filters.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteriaDto {
    private Long id;
    private String type;
    private String condition;
    private String value;
}
