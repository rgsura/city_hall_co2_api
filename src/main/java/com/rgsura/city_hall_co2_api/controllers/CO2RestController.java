package com.rgsura.city_hall_co2_api.controllers;

import com.rgsura.city_hall_co2_api.domains.co2.exports.CO2LevelsRetrievalService;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.CO2Levels;
import com.rgsura.city_hall_co2_api.domains.co2.exports.model.IllegalParamException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CO2RestController {

    private final CO2LevelsRetrievalService retrievalService;

    @GetMapping("/city/{city}/co2readings")
    public ResponseEntity<CO2Levels> queryCO2Readings(
            @Parameter(example = "Vienna")
            @PathVariable("city") String city,
            @Parameter(example = "Penzing")
            @RequestParam(value = "disctrict", required = false) String disctrict,
            @Parameter(example = "30")
            @RequestParam(value = "max-days", required = false) String maxDays

    ) {

        var days = validateMaxDays(maxDays);

        var result = retrievalService.getCO2Levels(city, disctrict, days);

        return ResponseEntity.ok(result.orElse(null));
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
}
