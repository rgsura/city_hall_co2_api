package com.rgsura.city_hall_co2_api.domains.co2.database.entities;

import com.rgsura.city_hall_co2_api.domains.co2.exports.model.IllegalParamException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CityEntity {

    private final String cityName;
    private List<DistrictEntity> districts;

    public String getCityName() {
        return cityName;
    }

    public List<DistrictEntity> getDistricts() {
        return districts;
    }

    public void addNewDistrict(DistrictEntity newDistrict) {
        if(districts.stream().anyMatch(x -> x.getDistrictName().equals(newDistrict.getDistrictName()))) {
            throw new IllegalParamException("District already exists: "+newDistrict.getDistrictName());
        }
        districts.add(newDistrict);
    }
}
