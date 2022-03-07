package com.rgsura.city_hall_co2_api.domains.co2.services.deps;

import java.sql.Timestamp;

public interface CO2LevelInserter {
    void insertCO2Reading(String city, String district, Double co2level, Timestamp timestamp);
}
