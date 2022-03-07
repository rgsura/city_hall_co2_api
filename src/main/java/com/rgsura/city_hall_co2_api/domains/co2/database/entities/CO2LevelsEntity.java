package com.rgsura.city_hall_co2_api.domains.co2.database.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "CO2_LEVELS")
@EqualsAndHashCode
@ToString
public class CO2LevelsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Getter
    @Setter
    private Integer id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String city;

    @Column(nullable = false)
    @Getter
    @Setter
    private String district;

    @Column
    @Getter
    @Setter
    private Double co2ReadingValue;

    @Column
    @Getter
    @Setter
    private Timestamp readingTs;
}
