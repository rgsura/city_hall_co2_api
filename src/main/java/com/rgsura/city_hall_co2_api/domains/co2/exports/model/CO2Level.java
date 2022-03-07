package com.rgsura.city_hall_co2_api.domains.co2.exports.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.sql.Timestamp;

@Value
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CO2Level {

    @Schema
    double co2level;

    @Schema
    Timestamp timestamp;
}
