package com.wisercat.filtersapp.service;

import com.wisercat.filtersapp.model.Filter;
import com.wisercat.filtersapp.model.FilterDto;
import com.wisercat.filtersapp.repository.FilterRepository;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    private final FilterRepository filterRepository;
    private final ModelMapper modelMapper; // Use ModelMapper for object mapping

    public FilterService(FilterRepository filterRepository, ModelMapper modelMapper) {
        this.filterRepository = filterRepository;
        this.modelMapper = modelMapper;
    }

    public FilterDto createFilter(FilterDto filterDto) {
        // Map DTO to Entity
        Filter filter = modelMapper.map(filterDto, Filter.class);
        // Save to the database
        Filter savedFilter = filterRepository.save(filter);
        // Map back the saved entity to DTO
        return modelMapper.map(savedFilter, FilterDto.class);
    }

}
