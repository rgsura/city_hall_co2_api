package com.rgsura.city_hall_co2_api.domains.co2.database.mappers;

import com.rgsura.city_hall_co2_api.domains.co2.database.entities.CO2LevelsEntity;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;

import java.util.List;

public interface CO2LevelsMapper {
    CO2Levels co2LevelsFromEntities(List<CO2LevelsEntity> entities);
}
