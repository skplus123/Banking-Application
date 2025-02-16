package com.Banking.Application.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class for the application.
 * <p>
 * This configuration sets up JWT-based authentication by:
 * <ul>
 *   <li>Disabling CSRF protection (since the application is stateless).</li>
 *   <li>Allowing unauthenticated access to the login endpoint.</li>
 *   <li>Requiring authentication for all other endpoints.</li>
 *   <li>Configuring the session management to be stateless.</li>
 *   <li>Adding a custom {@link JwtFilter} before the default {@link UsernamePasswordAuthenticationFilter}.</li>
 * </ul>
 * </p>
 */
@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configures the security filter chain for the application.
     * <p>
     * This method disables CSRF, permits access to the "/auth/login" endpoint,
     * requires authentication for all other endpoints, and sets the session management
     * to stateless. It also adds the custom {@link JwtFilter} before the
     * {@link UsernamePasswordAuthenticationFilter} to ensure that JWT validation occurs
     * on each request.
     * </p>
     *
     * @param http the {@link HttpSecurity} instance used to configure security settings
     * @return the configured {@link SecurityFilterChain} instance
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
