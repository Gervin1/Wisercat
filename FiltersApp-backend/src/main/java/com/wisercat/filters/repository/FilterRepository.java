package com.wisercat.filters.repository;

import com.wisercat.filters.model.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
