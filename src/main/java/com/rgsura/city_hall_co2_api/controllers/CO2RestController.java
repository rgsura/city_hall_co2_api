package com.rgsura.city_hall_co2_api.controllers;

import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2SensorReading;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.DistrictCO2Levels;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;

@RestController
public class CO2RestController {

    @GetMapping("/city/{city}/co2reading")
    public ResponseEntity<CO2Levels> queryCO2Reading(
            @Parameter (example = "Vienna")
            @PathVariable("city") String city
    ) {
        var sampleResponse = CO2Levels.builder()
                .withCityName("Vienna")
                .withDistricts(asList(
                        DistrictCO2Levels.builder()
                                .withDistrictName("Penzing")
                                .withSensorReadings(asList(
                                        CO2SensorReading.builder()
                                                .withCo2level(20)
                                                .withTimestamp(Timestamp.from(Instant.now()))
                                                .build()
                                ))
                                .build()
                ))
                .build();
        return ResponseEntity.ok(sampleResponse);
    }
}
