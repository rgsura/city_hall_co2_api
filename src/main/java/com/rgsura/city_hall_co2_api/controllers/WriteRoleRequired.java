package com.rgsura.city_hall_co2_api.controllers;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({METHOD, TYPE})
@Secured("ROLE_WRITE")
public @interface WriteRoleRequired {
}
