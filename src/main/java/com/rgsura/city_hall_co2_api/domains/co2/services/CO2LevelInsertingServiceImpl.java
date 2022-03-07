package com.rgsura.city_hall_co2_api.domains.co2.services;

import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelInsertingService;
import com.rgsura.city_hall_co2_api.domains.co2.services.deps.CO2LevelInserter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
@AllArgsConstructor
public class CO2LevelInsertingServiceImpl implements CO2LevelInsertingService {

    private final CO2LevelInserter inserter;
    @Override
    public void insertCO2Reading(String city, String district, Double co2level, Timestamp timestamp) {
        inserter.insertCO2Reading(city, district, co2level, timestamp);
    }
}
