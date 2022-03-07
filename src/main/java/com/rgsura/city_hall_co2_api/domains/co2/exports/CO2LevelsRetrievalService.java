package com.rgsura.city_hall_co2_api.domains.co2.exports;

import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;

import java.util.Optional;

public interface CO2LevelsRetrievalService {

    Optional<CO2Levels> getCO2Levels(String city, String district, Integer maxDays);

}
