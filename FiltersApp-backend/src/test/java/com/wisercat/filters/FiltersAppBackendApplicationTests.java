package com.wisercat.filters.service;

import com.wisercat.filters.model.*;
import com.wisercat.filters.repository.FilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilterServiceTest {
    @InjectMocks
    private FilterService filterService;

    @Mock
    private FilterRepository filterRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testCreateFilter_success() {
        Filter filter1 = new Filter(1L, "Test Filter", new ArrayList<>(), new Date());
        when(filterRepository.save(any(Filter.class))).thenReturn(filter1);

        FilterDto filterDto = new FilterDto(null, "Test Filter", null, new Date());
        FilterDto createdFilterDto = filterService.createFilter(filterDto);

        assertNotNull(createdFilterDto);
        assertEquals("Test Filter", createdFilterDto.getName());
    }

    @Test
    public void testGetAllFilters_success() {
        Filter filter1 = new Filter(1L, "Test Filter 1", new ArrayList<>(), new Date());
        Filter filter2 = new Filter(2L, "Test Filter 2", new ArrayList<>(), new Date());
        List<Filter> mockFilterList = List.of(filter1, filter2);
        when(filterRepository.findAll()).thenReturn(mockFilterList);

        List<FilterDto> foundFilters = filterService.getAllFilters();

        assertFalse(foundFilters.isEmpty());
        assertEquals(2, foundFilters.size());
        assertEquals("Test Filter 1", foundFilters.get(0).getName());
        assertEquals("Test Filter 2", foundFilters.get(1).getName());
    }

    @Test
    public void testCreateFilter_withInvalidComparison_failure() {
        CriteriaDto criteriaDto = new CriteriaDto(null, CriteriaType.NUMBER, ComparisonType.TEXT_CONTAINS, "100");
        List<CriteriaDto> criteriaDtos = new ArrayList<>();
        criteriaDtos.add(criteriaDto);
        FilterDto filterDto = new FilterDto(null, "Invalid Filter", criteriaDtos, new Date());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> filterService.createFilter(filterDto));

        String expectedMessage = "Invalid comparison 'TEXT_CONTAINS' for criteria 'NUMBER'.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
