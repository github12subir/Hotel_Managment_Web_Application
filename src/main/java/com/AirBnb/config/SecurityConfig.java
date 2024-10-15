package com.AirBnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    //create object of JWTFilter class through Dependency-Injection
    private JWTFilter jwtFilter;
    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        //h(cd)2
        http.csrf().disable().cors().disable();



        //Add Authorization Filter before jwtFilter to Alter process
       http.addFilterBefore(jwtFilter, AuthorizationFilter.class);

       /*http.authorizeHttpRequests()
               .requestMatchers("/api/airbnb/appuser/signIn").permitAll()
        .requestMatchers("/api/airbnb/review/createReview/{propertyId}").hasRole("USER")
               .requestMatchers("/api/airbnb/appuser/signUpAsPropertyManager").hasRole("ADMIN")
        .anyRequest().authenticated();*/
       //Short form to apply all url open(haap)
        //http.authorizeHttpRequests().anyRequest().permitAll();

        http.authorizeHttpRequests()
                .requestMatchers("/api/airbnb/auth/signUpAsUser","/api/airbnb/auth/signUpAsOwner","/api/airbnb/auth/signIn","/api/airbnb/property/getProperty/{name}")
                .permitAll()
                .requestMatchers("/api/airbnb/property/*").hasAnyRole("OWNER","MANAGER","ADMIN")
               .requestMatchers("/api/airbnb/auth/signUpAsPropertyManager").hasRole("ADMIN")
                .requestMatchers("/api/airbnb/review/**").hasRole("USER")
                .requestMatchers("/api/airbnb/country/*").hasRole("ADMIN")
                .requestMatchers("/api/airbnb/city").hasRole("ADMIN")
                .requestMatchers("/api/airbnb/room/*").hasAnyRole("OWNER")
                .requestMatchers("/api/airbnb/booking/*").hasRole("USER")
               .anyRequest().authenticated();

//        http.authorizeHttpRequests().
//                requestMatchers("/api/airbnb/auth/signIn").permitAll()
//        .requestMatchers("http://localhost:8080/api/airbnb/review/getReviews")
//                .hasRole("USER")
//                .anyRequest()
//                .authenticated();
        return http.build();
    }
}
