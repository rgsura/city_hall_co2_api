package com.rgsura.city_hall_co2_api.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.rgsura.city_hall_co2_api.domains.co2.database.repositories"})
public class PersistanceConfig {
}
