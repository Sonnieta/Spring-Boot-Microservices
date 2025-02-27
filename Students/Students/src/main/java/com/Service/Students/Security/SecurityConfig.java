package com.Service.Students.Security;

import com.Service.Students.Client.AuthClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthClient authClient;


    public SecurityConfig(
            @Lazy JwtAuthFilter jwtAuthFilter,
            AuthClient authClient) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authClient = authClient;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/auth/**"  // Authentication endpoints should be public
                        ).permitAll()
                        .requestMatchers("/auth/validate").permitAll()
                        .requestMatchers("/students/register").permitAll() // ✅ Only ADMIN & TEACHER can register students
                        .requestMatchers("/exams/**", "/reports/**", "/classes/**", "/streams/**", "/gradings/**", "/subjects/**","/students/**").permitAll()  // ✅ Require authentication
                        .requestMatchers("/actuator/**", "/eureka/**").permitAll()  // Allow Eureka Server
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// Enforce stateless API
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class); // Add JWT filter before authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(authClient);  // Pass AuthClient to the filter
    }
}
