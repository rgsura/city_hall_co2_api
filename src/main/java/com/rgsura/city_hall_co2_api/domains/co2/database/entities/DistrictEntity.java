package com.rgsura.city_hall_co2_api.domains.co2.database.entities;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DistrictEntity {

    private final String districtName;
    private List<SensorReadingEntity> sensorReadings;

    public String getDistrictName() {
        return districtName;
    }

    public List<SensorReadingEntity> getSensorReadings() {
        return sensorReadings;
    }

    public void addNewSensorReading(SensorReadingEntity newReading) {
        sensorReadings.add(newReading);
    }

}
