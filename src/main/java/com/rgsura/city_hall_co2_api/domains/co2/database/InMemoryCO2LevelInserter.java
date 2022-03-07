package com.rgsura.city_hall_co2_api.domains.co2.database;

import com.rgsura.city_hall_co2_api.domains.co2.database.repositories.CO2LevelsRepository;
import com.rgsura.city_hall_co2_api.domains.co2.services.deps.CO2LevelInserter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@AllArgsConstructor
public class InMemoryCO2LevelInserter implements CO2LevelInserter {

    private final CO2LevelsRepository repository;

    @Override
    public void insertCO2Reading(String city, String district, Double co2level, Timestamp timestamp) {
        repository.insertCO2Reading(city.toUpperCase(), district, co2level, timestamp);
    }
}
