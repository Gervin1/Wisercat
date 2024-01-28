package com.wisercat.filters.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String condition;
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Filter filter;
}
