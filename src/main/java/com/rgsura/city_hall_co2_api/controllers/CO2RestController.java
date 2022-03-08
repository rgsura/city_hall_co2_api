package com.rgsura.city_hall_co2_api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelInsertingService;
import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelsRetrievalService;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Level;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.IllegalParamException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@AllArgsConstructor
public class CO2RestController {

    private final CO2LevelsRetrievalService retrievalService;
    private final CO2LevelInsertingService insertingService;
    private final ObjectMapper objectMapper;

    @ReadRoleRequired
    @GetMapping("/city/{city}/co2readings")
    public ResponseEntity<CO2Levels> queryCO2Readings(
            @Parameter(example = "Vienna")
            @PathVariable("city") String city,
            @Parameter(example = "Penzing")
            @RequestParam(value = "district", required = false) String district,
            @Parameter(example = "30")
            @RequestParam(value = "max-days", required = false) String maxDays,
            HttpServletRequest request

    ) {
        if(!validateUser(request.getRemoteUser(), city)){
            throw new IllegalParamException("User not authorized to view this city: "+city);
        }

        var days = validateMaxDays(maxDays);

        var result = retrievalService.getCO2Levels(city, district, days);

        return ResponseEntity.ok(result.orElse(null));
    }

    @WriteRoleRequired
    @PostMapping("/city/{city}/district/{district}/co2reading")
    public ResponseEntity<CO2Level> insertCO2Reading(
            @Parameter(example = "Vienna")
            @PathVariable("city") String city,
            @Parameter(example = "Penzing")
            @PathVariable("district") String district,
            @Valid @RequestBody CO2Level sensorReading,
            HttpServletRequest request

    ) {
        if(!validateUser(request.getRemoteUser(), city)){
            throw new IllegalParamException("User not authorized to insert reading for this city: "+city);
        }

        insertingService.insertCO2Reading(city, district, sensorReading.getCo2level(), sensorReading.getTimestamp());
        return ResponseEntity.accepted().body(sensorReading);
    }

    private Integer validateMaxDays(String maxDays) {
        try {
            if(maxDays==null || maxDays.isBlank()) {
                return null;
            }
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

    private String jsonify(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Cannot JSONify result: "+object.toString(),e);
        }
    }

    private Boolean validateUser(String user, String city) {
        var userCorrected = user.split("_")[0].toUpperCase();
        var cityCorrected = city.toUpperCase();
        return userCorrected.equals(cityCorrected);
    }
}
