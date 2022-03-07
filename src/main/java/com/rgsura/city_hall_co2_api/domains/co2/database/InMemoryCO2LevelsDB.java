package com.rgsura.city_hall_co2_api.domains.co2.database;

import com.rgsura.city_hall_co2_api.domains.co2.database.entities.CityEntity;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.IllegalParamException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class InMemoryCO2LevelsDB {
    private List<CityEntity> cities;

    public List<CityEntity> getCities() {
        return cities;
    }

    public void addCity(CityEntity newCity) {
        if(cities.stream().anyMatch(x -> x.getCityName().equals(newCity.getCityName()))) {
            throw new IllegalParamException("District already exists: "+newCity.getCityName());
        }
        cities.add(newCity);
    }
}
