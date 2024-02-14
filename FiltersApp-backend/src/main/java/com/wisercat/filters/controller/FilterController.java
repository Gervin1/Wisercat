package com.wisercat.filters.controller;

import com.wisercat.filters.model.FilterDto;
import com.wisercat.filters.repository.FilterRepository;
import com.wisercat.filters.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/filters")
public class FilterController {

    @Autowired
    private FilterRepository filterRepository;

    @PostMapping
    public ResponseEntity<FilterDto> createFilter(@RequestBody FilterDto filterDto) {
//        FilterDto createdFilterDto = filterService.createFilter(filterDto);
//        return new ResponseEntity<>(createdFilterDto, HttpStatus.CREATED);
        return null;
    }

    // Additional endpoints for update, delete, list, etc.
}
