package com.rgsura.city_hall_co2_api.domains.co2.exports.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Value
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class DistrictCO2Levels {

    @Schema
    String districtName;

    @Schema
    List<CO2SensorReading> sensorReadings;
}
