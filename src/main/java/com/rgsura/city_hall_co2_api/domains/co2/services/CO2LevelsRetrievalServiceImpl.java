package com.rgsura.city_hall_co2_api.domains.co2.services;

import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelsRetrievalService;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CO2LevelsRetrievalServiceImpl implements CO2LevelsRetrievalService {
    @Override
    public Optional<CO2Levels> getCO2Levels(String city, String district, Integer maxDays) {
        return Optional.empty();
    }
}
