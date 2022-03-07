package com.rgsura.city_hall_co2_api.domains.co2.services;

import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelsRetrievalService;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.services.deps.CO2LevelsRetriever;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CO2LevelsRetrievalServiceImpl implements CO2LevelsRetrievalService {

    private final CO2LevelsRetriever retriever;

    @Override
    public Optional<CO2Levels> getCO2Levels(String city, String district, Integer maxDays) {
        return retriever.get(city, district, maxDays);
    }
}
