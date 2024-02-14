package com.wisercat.filters.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "criteria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CriteriaType type;

    @Column(name = "comparison")
    @Enumerated(EnumType.STRING)
    private ComparisonType comparison;

    @Column(name = "comparingValue")
    private String comparingValue;

    @ManyToOne
    @JoinColumn(name = "filter_id", nullable = false)
    private Filter filter;
}
