package org.au_fil_kc.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.web.cors.CorsConfiguration; // Import manquant
import org.springframework.web.cors.CorsConfigurationSource; // Import manquant
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // Import manquant

import java.util.Arrays; // Import manquant

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF
                // Ajoutez la configuration CORS ici
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Active CORS avec la source de configuration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Autorise les requêtes OPTIONS (pre-flight)
                        .anyRequest().authenticated() // Toutes les autres requêtes doivent être authentifiées
                )
                .httpBasic(withDefaults()) // Active l'authentification Basic HTTP
                .formLogin(form -> form.disable()); // Désactive formLogin

        return http.build();
    }

    // Bean pour configurer la politique CORS
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Autoriser l'origine de votre frontend
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        // Autoriser toutes les méthodes HTTP (GET, POST, PUT, DELETE, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Autoriser tous les en-têtes (headers)
        configuration.setAllowedHeaders(Arrays.asList("*"));
        // Autoriser l'envoi de credentials (cookies, en-têtes d'autorisation)
        configuration.setAllowCredentials(true);
        // Définir le temps de vie maximum des résultats du pre-flight (en secondes)
        configuration.setMaxAge(3600L); // 1 heure

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer cette configuration à toutes les routes
        return source;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}