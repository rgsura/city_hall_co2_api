package com.rgsura.city_hall_co2_api.domains.co2.services.deps;

import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;

import java.util.Optional;

public interface CO2LevelsRetriever {

    Optional<CO2Levels> get(String city, String district, Integer maxDays);
}
