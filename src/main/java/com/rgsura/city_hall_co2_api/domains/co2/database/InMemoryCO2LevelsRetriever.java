package com.rgsura.city_hall_co2_api.domains.co2.database;

import com.rgsura.city_hall_co2_api.domains.co2.database.entities.CO2LevelsEntity;
import com.rgsura.city_hall_co2_api.domains.co2.database.mappers.CO2LevelsMapper;
import com.rgsura.city_hall_co2_api.domains.co2.database.repositories.CO2LevelsRepository;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.services.deps.CO2LevelsRetriever;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InMemoryCO2LevelsRetriever implements CO2LevelsRetriever {

    private final CO2LevelsRepository repository;
    private final CO2LevelsMapper mapper;

    @Override
    public Optional<CO2Levels> get(String city, String district, Integer maxDays) {

        var result = getRepositoryResult(city, district, maxDays);
        return Optional.ofNullable(mapper.co2LevelsFromEntities(result));
    }

    private List<CO2LevelsEntity> getRepositoryResult(String city, String district, Integer maxDays) {
        if (maxDays != null) {
            var timestamp = Timestamp.from(Instant.now().minus(Period.ofDays(maxDays)));
            if (district!=null && !district.isBlank()) {
                return repository.findCO2LevelsEntitiesByCityAndDistrictAndReadingTsGreaterThanEqual(city.toUpperCase(), district, timestamp);
            }
            return repository.findCO2LevelsEntitiesByCityAndReadingTsGreaterThanEqual(city.toUpperCase(), timestamp);
        }
        if (district!=null && !district.isBlank()) {
            return repository.findCO2LevelsEntitiesByCityAndDistrict(city.toUpperCase(), district);
        }
        return repository.findCO2LevelsEntitiesByCity(city.toUpperCase());

    }
}
