package com.rgsura.city_hall_co2_api.domains.co2.database.repositories;

import com.rgsura.city_hall_co2_api.domains.co2.database.entities.CO2LevelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface CO2LevelsRepository extends JpaRepository<CO2LevelsEntity, Integer> {

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCity(String city);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCityAndDistrict(String city, String district);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCityAndReadingTsGreaterThanEqual(String city, Timestamp readingTs);

    List<CO2LevelsEntity> findCO2LevelsEntitiesByCityAndDistrictAndReadingTsGreaterThanEqual(String city, String district, Timestamp readingTs);

    @Modifying
    @Query(value =
            "insert into CO2_LEVELS (city, district, CO2READING_VALUE, reading_Ts) values (:city, :district, :co2ReadingValue, :readingTs)",
            nativeQuery = true)
    void insertCO2Reading(@Param("city") String city,
                    @Param("district") String district,
                    @Param("co2ReadingValue") Double co2ReadingValue,
                    @Param("readingTs") Timestamp readingTs);

}
