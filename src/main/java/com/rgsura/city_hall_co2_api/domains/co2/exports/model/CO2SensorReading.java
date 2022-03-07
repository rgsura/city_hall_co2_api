package com.rgsura.city_hall_co2_api.domains.co2.exports.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@Value
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
public class CO2SensorReading {

    @Schema
    double co2level;

    @Schema
    Timestamp timestamp;
}
