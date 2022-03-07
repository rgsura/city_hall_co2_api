package com.rgsura.city_hall_co2_api.domains.co2.exports.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalParamException extends RuntimeException{
    public IllegalParamException(String message) {
        super(message);
    }
}
