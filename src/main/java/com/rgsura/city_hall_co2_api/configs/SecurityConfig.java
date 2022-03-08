package com.rgsura.city_hall_co2_api.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @lombok.Value
    static class Credentials {
        String user;
        String pw;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth,
                                @Value("${c02.auth.basic.read}") String credsRead,
                                @Value("${c02.auth.basic.write}") String credsWrite) throws Exception {

        var authentication = auth.inMemoryAuthentication();
        var credsReadList = credsAsList(credsRead);

        credsReadList.forEach(
                x-> authentication
                        .withUser(x.getUser())
                        .password(passwordEncoder().encode(x.getPw()))
                        .roles("READ")
        );

        var credsWriteList = credsAsList(credsWrite);
        credsWriteList.forEach(
                x-> authentication
                        .withUser(x.getUser())
                        .password(passwordEncoder().encode(x.getPw()))
                        .roles("WRITE")
        );
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/mgmt/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/swagger-ui/**"
        );
    }

    private Credentials asCreds(String credString) {
        var splitCred = credString.split(":");
        return new Credentials(splitCred[0], splitCred[1]);
    }

    private List<Credentials> credsAsList(String creds) {
        var credsString = creds.split(",");

        return stream(credsString)
                .map(this::asCreds)
                .collect(Collectors.toList());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http.headers().frameOptions().disable();

    }


}
