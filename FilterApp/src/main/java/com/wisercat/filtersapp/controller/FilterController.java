package com.wisercat.filtersapp.controller;

import com.wisercat.filtersapp.model.FilterDto;
import com.wisercat.filtersapp.service.FilterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filters")
public class FilterController {

    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping
    public ResponseEntity<FilterDto> createFilter(@RequestBody FilterDto filterDto) {
        FilterDto createdFilterDto = filterService.createFilter(filterDto);
        return new ResponseEntity<>(createdFilterDto, HttpStatus.CREATED);
    }

    // Additional endpoints for update, delete, list, etc.
}
