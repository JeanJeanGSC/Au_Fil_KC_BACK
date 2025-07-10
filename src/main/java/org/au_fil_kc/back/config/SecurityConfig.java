package org.au_fil_kc.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Les endpoints pour s'inscrire et se connecter sont publiques
                        .requestMatchers("auth/**").permitAll()
                        // La lecture des produits est publique
                        .requestMatchers(HttpMethod.GET, "/produits/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/services/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/adresses").permitAll()
                        // TODO ajouter les permission pour les services aussi et tout autre requête qui doivent être accessible par un client
                        // Toutes les autres requêtes pour produits nécessitent une authentification (POST, DELETE, UPDATE)
                        .requestMatchers("/produits/**").authenticated()
                        // toutes les autres endpoint (URLs) qui ne sont pas explicitement couvert ont besoins d'un token !
                        .anyRequest().authenticated()
                )
                // On dit à Spring que la gestion de session sera sans état (stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                // On ajoute notre filtre JWT avant le filtre de base de Spring
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}