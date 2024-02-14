package com.wisercat.filters.model;


import lombok.Getter;

import static com.wisercat.filters.model.CriteriaType.*;

public enum ComparisonType {
    NUMBER_LESS_THAN(NUMBER),
    NUMBER_LESS_THAN_OR_EQUAL(NUMBER),
    NUMBER_EQUALS(NUMBER),
    NUMBER_GREATER_THAN(NUMBER),
    NUMBER_GREATER_THAN_OR_EQUAL(NUMBER),

    TEXT_STARTS_WITH(TEXT),
    TEXT_ENDS_WITH(TEXT),
    TEXT_CONTAINS(TEXT),
    TEXT_NOT_CONTAINS(TEXT),

    DATE_FROM(DATE),
    DATE_EQUALS(DATE),
    DATE_BEFORE(DATE);

    @Getter
    private final CriteriaType criteriaType;
    ComparisonType(CriteriaType criteriaType) {
        this.criteriaType = criteriaType;
    }
}