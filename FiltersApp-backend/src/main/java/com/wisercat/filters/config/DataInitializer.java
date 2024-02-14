package com.wisercat.filters.config;

import com.wisercat.filters.model.Criteria;
import com.wisercat.filters.model.CriteriaType;
import com.wisercat.filters.model.Filter;
import com.wisercat.filters.repository.CriteriaRepository;
import com.wisercat.filters.repository.FilterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;

import static com.wisercat.filters.model.ComparisonType.*;
import static com.wisercat.filters.model.ComparisonType.TEXT_CONTAINS;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initDatabase(FilterRepository filterRepository, CriteriaRepository criteriaRepository) {
        return args -> {
            Filter filter1 = new Filter(null, "My Filter 1", new ArrayList<>(), new Date());
            Filter filter2 = new Filter(null, "My Filter 2", new ArrayList<>(), new Date());
            filterRepository.save(filter1);
            filterRepository.save(filter2);

            Criteria criteria1 = new Criteria(null, CriteriaType.NUMBER, NUMBER_GREATER_THAN, "4", filter1);
            Criteria criteria2 = new Criteria(null, CriteriaType.TEXT, TEXT_STARTS_WITH, "Meow", filter1);
            Criteria criteria3 = new Criteria(null, CriteriaType.DATE, DATE_FROM, "2024-01-01", filter1);
            criteriaRepository.save(criteria1);
            criteriaRepository.save(criteria2);
            criteriaRepository.save(criteria3);

            Criteria criteria4 = new Criteria(null, CriteriaType.TEXT, TEXT_CONTAINS, "4", filter2);
            Criteria criteria5 = new Criteria(null, CriteriaType.NUMBER, NUMBER_GREATER_THAN, "4", filter2);
            criteriaRepository.save(criteria4);
            criteriaRepository.save(criteria5);
        };
    }

}
