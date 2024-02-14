package com.wisercat.filters.controller;

import com.wisercat.filters.model.FilterDto;
import com.wisercat.filters.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FilterController {

    @Autowired
    private FilterService filterService;

    @GetMapping("/filters")
    public ResponseEntity<List<FilterDto>> getAllFilters() {
        List<FilterDto> filters = filterService.getAllFilters();
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @PostMapping("/filters")
    public ResponseEntity<FilterDto> createFilter(@RequestBody FilterDto filterDto) {
        FilterDto createdFilter = filterService.createFilter(filterDto);
        return new ResponseEntity<>(createdFilter, HttpStatus.CREATED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
