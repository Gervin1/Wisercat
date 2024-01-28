package com.wisercat.filters.repository;

import com.wisercat.filters.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
