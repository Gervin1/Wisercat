package com.wisercat.filters.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriteriaDto {
    private Long id;
    private CriteriaType type;
    private ComparisonType condition;
    private String comparingValue;
}