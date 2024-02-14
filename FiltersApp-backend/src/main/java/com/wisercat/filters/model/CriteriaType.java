package com.wisercat.filters.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CriteriaType {
    NUMBER, TEXT, DATE;

    public boolean isValidComparison(ComparisonType comparisonType) {
        return getValidComparisonTypes().contains(comparisonType);
    }

    private List<ComparisonType> getValidComparisonTypes() {
        return Arrays.stream(ComparisonType.values())
                .filter(ct -> ct.getCriteriaType() == this)
                .collect(Collectors.toList());
    }

}
