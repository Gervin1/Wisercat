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

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "criteria_condition", nullable = false)
    private String criteria_condition;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Filter filter;
}
