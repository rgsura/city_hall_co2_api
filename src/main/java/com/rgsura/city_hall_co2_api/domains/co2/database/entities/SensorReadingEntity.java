package com.rgsura.city_hall_co2_api.domains.co2.database.entities;

import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
public class SensorReadingEntity {

    private final double co2level;
    private final Timestamp timestamp;

    public double getCo2level() {
        return co2level;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
