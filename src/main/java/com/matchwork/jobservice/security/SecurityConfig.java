package com.matchwork.jobservice.security;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource; 
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .cors(cors -> cors.configurationSource(corsConfigurationSource()))
          .csrf(csrf -> csrf.disable())
          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
          .authorizeHttpRequests(auth -> auth
             
             // 1. Restaurar “permitAll” para GET /api/jobs/**
             .requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll()
             
             // 2. (Opcional) Si quieres que SOLO quien tenga rol EMPRESA cree/edite/elimine:
             .requestMatchers(HttpMethod.POST,   "/api/jobs").hasRole("EMPRESA")
             .requestMatchers(HttpMethod.PUT,    "/api/jobs/**").hasRole("EMPRESA")
             .requestMatchers(HttpMethod.DELETE, "/api/jobs/**").hasRole("EMPRESA")
             
             // 3. Otras rutas de match y postulaciones que ya habías permitido
             .requestMatchers(HttpMethod.OPTIONS, "/api/match/**").permitAll()
             .requestMatchers("/api/match/**").permitAll()
             .requestMatchers(HttpMethod.OPTIONS, "/api/postulaciones/**").permitAll()
             .requestMatchers(HttpMethod.POST,   "/api/postulaciones").permitAll()
             .requestMatchers(HttpMethod.GET,    "/api/postulaciones/**").permitAll()
             .requestMatchers("/api/postulaciones/*/postulantes-con-perfil").permitAll()
              .requestMatchers("/api/postulaciones/trabajo/*/postulantes-con-perfil").permitAll()
              .requestMatchers("/api/postulaciones/**/postulantes-con-perfil").permitAll()

             
             // 4. Todas las demás rutas sí quedan autenticadas
             .anyRequest().authenticated()
          )
          .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
