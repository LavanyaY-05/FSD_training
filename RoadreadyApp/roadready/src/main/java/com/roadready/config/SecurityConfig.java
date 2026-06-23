package com.roadready.config;

import com.roadready.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Auth
                        .requestMatchers(HttpMethod.GET, "/api/auth/login").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/auth/user-details").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/auth/newpass").authenticated()

                        //Admin
                        .requestMatchers(HttpMethod.GET, "/api/admin/adminprofile").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/agent/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/admin/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/getCarStats").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/getAgentStats").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/admin/getUnassignedAgent").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/getAgents/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/admin/combinedStats").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/car/delete/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/car/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/car/update/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/car/uploadImage").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/coupons/getAll").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/coupons/add").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/coupons/delete/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/admin/assign/{bookingId}/{agentId}").hasAuthority("ADMIN")

                        //Customer
                        .requestMatchers(HttpMethod.GET, "/api/bookings/customer").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/bookings/customer/{id}/cancel").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/coupons/validate").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/bookings/hours").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/bookings/add/{carId}").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/bookings/confirm/{id}").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/customers/signUp").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers/customerprofile").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/customers/update").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/review/by-customer").hasAuthority("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/bookings/bookingstatus").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/review/add/{id}").hasAuthority("CUSTOMER")


                        //Car
                        .requestMatchers(HttpMethod.GET, "/api/car/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/car/getTypes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/review/avgRating/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/car/search").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/car/get/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/review/car/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/car/locations").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/car/browseCars").permitAll()

                        //Agent
                        .requestMatchers(HttpMethod.POST, "/api/agent/agentprofile").hasAuthority("AGENT")
                        .requestMatchers(HttpMethod.PUT, "/api/agent/update").hasAuthority("AGENT")
                        .requestMatchers(HttpMethod.GET, "/api/inspection/pre-rent").hasAuthority("AGENT")
                        .requestMatchers(HttpMethod.POST, "/api/inspection/add/{id}").hasAuthority("AGENT")
                        .requestMatchers(HttpMethod.GET, "/api/inspection/post-rent").hasAuthority("AGENT")

                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userService);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
