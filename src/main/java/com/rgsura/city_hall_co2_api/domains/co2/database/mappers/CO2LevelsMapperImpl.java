package com.rgsura.city_hall_co2_api.domains.co2.database.mappers;

import com.rgsura.city_hall_co2_api.domains.co2.database.entities.CO2LevelsEntity;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2SensorReading;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.DistrictCO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.IllegalParamException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CO2LevelsMapperImpl implements CO2LevelsMapper{
    @Override
    public CO2Levels co2LevelsFromEntities(List<CO2LevelsEntity> entities) {
        var cities = entities.stream()
                .map(CO2LevelsEntity::getCity)
                .distinct()
                .collect(Collectors.toList());
        if(cities.size()>1)
        {
            throw new RuntimeException("More than one city in query: "+cities.toString());
        }
        else if(cities.size()==0) {
            return null;
        }
        var city = cities.get(0);

        var districtNames = entities.stream()
                .map(CO2LevelsEntity::getDistrict)
                .distinct()
                .collect(Collectors.toList());

        var districts = districtNames.stream()
                .map(x -> mapDistrict(entities, x))
                .collect(Collectors.toList());

        return CO2Levels.builder()
                .withCityName(city)
                .withDistricts(districts)
                .build();
    }

    private DistrictCO2Levels mapDistrict(List<CO2LevelsEntity> entities, String districtName) {
        var readings = entities.stream()
                .filter(x -> x.getDistrict().equals(districtName))
                .map(this::mapSensorReadings)
                .collect(Collectors.toList());

        return DistrictCO2Levels.builder()
                .withDistrictName(districtName)
                .withSensorReadings(readings)
                .build();
    }

    private CO2SensorReading mapSensorReadings(CO2LevelsEntity entity) {
        return CO2SensorReading.builder()
                .withCo2level(entity.getCo2ReadingValue())
                .withTimestamp(entity.getReadingTs())
                .build();
    }
}
