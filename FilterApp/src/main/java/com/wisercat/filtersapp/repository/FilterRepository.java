package com.wisercat.filtersapp.repository;

import com.wisercat.filtersapp.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
