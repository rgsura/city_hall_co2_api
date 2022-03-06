package com.rgsura.city_hall_co2_api.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CO2RestController {

    @GetMapping("/city/{city}/co2reading")
    public ResponseEntity<String> queryCO2Reading(
            @Parameter (example = "Vienna")
            @PathVariable("city") String city
    ) {
        return ResponseEntity.ok("All good");
    }
}
