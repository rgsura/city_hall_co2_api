package com.rgsura.city_hall_co2_api.controllers;

import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelsRetrievalService;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2SensorReading;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.DistrictCO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.IllegalParamException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;

@RestController
@AllArgsConstructor
public class CO2RestController {

    private final CO2LevelsRetrievalService retrievalService;

    @GetMapping("/city/{city}/co2readings")
    public ResponseEntity<CO2Levels> queryCO2Readings(
            @Parameter(example = "Vienna")
            @PathVariable("city") String city,
            @Parameter(example = "Penzing")
            @RequestParam("disctrict") String disctrict,
            @Parameter(example = "30")
            @RequestParam("max-days") String maxDays

    ) {

        var days = validateMaxDays(maxDays);

        var result = retrievalService.getCO2Levels(city, disctrict, days);

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

    private Integer validateMaxDays(String maxDays) {
        try {
            var days = Integer.parseInt(maxDays);

            if(days<0) {
                throw new IllegalParamException("Maximum days should be positive: "+days);
            }

            return days;
        }
        catch (NumberFormatException e) {
            throw new IllegalParamException("Illegal maximum days parameter: "+maxDays);
        }
    }
}
