package com.rgsura.city_hall_co2_api.domains.co2.database.repositories;

import com.rgsura.city_hall_co2_api.domains.co2.database.entities.CO2LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface CO2LevelsRepository extends JpaRepository<CO2LevelsEntity, Integer> {

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCity(String city);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCityAndDistrict(String city, String district);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCityAndReadingTsGreaterThanEqual(String city, Timestamp readingTs);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCityAndDistrictAndReadingTsGreaterThanEqual(String city, String district, Timestamp readingTs);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByReadingTsGreaterThanEqual(Timestamp readingTs);
}
