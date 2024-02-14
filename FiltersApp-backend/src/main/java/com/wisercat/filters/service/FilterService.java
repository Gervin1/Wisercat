package com.wisercat.filters.service;

import com.wisercat.filters.model.Criteria;
import com.wisercat.filters.model.CriteriaDto;
import com.wisercat.filters.model.Filter;
import com.wisercat.filters.model.FilterDto;
import com.wisercat.filters.repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilterService {

    @Autowired
    private FilterRepository filterRepository;

    public FilterDto createFilter(FilterDto filterDto) throws IllegalArgumentException {
        Filter filter = convertToEntity(filterDto);
        for (Criteria criteria : filter.getCriteriaList()) {
            if (!criteria.getType().isValidComparison(criteria.getComparison())) {
                String errorMessage = String.format("Invalid comparison '%s' for criteria '%s'.",
                        criteria.getComparison(),
                        criteria.getType());
                throw new IllegalArgumentException(errorMessage);
            }
        }
        filter.setCreatedAt(new Date());
        Filter createdFilter = filterRepository.save(filter);
        return convertToDto(createdFilter);
    }

    public List<FilterDto> getAllFilters() {
        List<Filter> filters = filterRepository.findAll();
        return filters.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Filter convertToEntity(FilterDto filterDto) {
        Filter filter = new Filter();
        filter.setId(filterDto.getId());
        filter.setName(filterDto.getName());
        filter.setCreatedAt(filterDto.getCreatedAt());
        if (filterDto.getCriteriaList() != null) {
            List<Criteria> criteriaList = filterDto.getCriteriaList().stream().map(dto -> {
                Criteria criteria = convertToEntity(dto);
                criteria.setFilter(filter);
                return criteria;
            }).collect(Collectors.toList());
            filter.setCriteriaList(criteriaList);
        }
        return filter;
    }

    private Criteria convertToEntity(CriteriaDto dto) {
        Criteria criteria = new Criteria();
        criteria.setId(dto.getId());
        criteria.setType(dto.getType());
        criteria.setComparison(dto.getCondition());
        criteria.setComparingValue(dto.getComparingValue());
        return criteria;
    }

    private FilterDto convertToDto(Filter filter) {
        FilterDto dto = new FilterDto();
        dto.setId(filter.getId());
        dto.setName(filter.getName());
        dto.setCreatedAt(filter.getCreatedAt());
        dto.setCriteriaList(filter.getCriteriaList().stream().map(this::convertToDto).collect(Collectors.toList()));
        return dto;
    }

    private CriteriaDto convertToDto(Criteria criteria) {
        CriteriaDto dto = new CriteriaDto();
        dto.setId(criteria.getId());
        dto.setType(criteria.getType());
        dto.setCondition(criteria.getComparison());
        dto.setComparingValue(criteria.getComparingValue());
        return dto;
    }
}
