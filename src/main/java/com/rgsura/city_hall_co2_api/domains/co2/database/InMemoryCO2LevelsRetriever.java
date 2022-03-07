package com.rgsura.city_hall_co2_api.domains.co2.database;

import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.services.deps.CO2LevelsRetriever;

import java.util.Optional;

public class InMemoryCO2LevelsRetriever implements CO2LevelsRetriever {
    @Override
    public Optional<CO2Levels> get(String city, String district, Integer maxDays) {
        return Optional.empty();
    }
}
