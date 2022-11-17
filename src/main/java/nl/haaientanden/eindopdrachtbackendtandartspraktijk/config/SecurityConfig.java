package nl.haaientanden.eindopdrachtbackendtandartspraktijk.config;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.UserRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.filter.JwtRequestFilter;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.JwtService;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtService jwtService;


    public SecurityConfig(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    //authorisation
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/haaientanden/users").permitAll()
                .antMatchers(HttpMethod.POST, "/haaientanden/auth").permitAll()
                .antMatchers(HttpMethod.POST,
                        "/haaientanden/patienten",
                        "/haaientanden/behandelkamers",
                        "/haaientanden/behandelingen",
                        "/haaientanden/afspraken",
                        "/haaientanden/facturen"
                        ).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/haaientanden/patienten",
                        "/haaientanden/patienten/{id}",
                        "/haaientanden/afspraken",
                        "/haaientanden/facturen",
                        "/haaientanden/facturen/{id}"
                        ).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,
                        "/haaientanden/patienten/{id}",
                        "/haaientanden/behandelkamers/{id}",
                        "/haaientanden/behandelingen/{id}",
                        "/haaientanden/afspraken/{id}",
                        "/haaientanden/afspraken/{id}/behandelkamers/{id}",
                        "/haaientanden/afspraken/{appointmentId}/patienten/{patientId}",
                        "/haaientanden/facturen/{id}"
                        ).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,
                        "/haaientanden/patienten/{id}",
                        "/haaientanden/behandelkamers/{id}",
                        "/haaientanden/behandelingen/{id}",
                        "/haaientanden/afspraken/{id}",
                        "/haaientanden/facturen/{id}"
                ).hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,
                        "/haaientanden/behandelkamers",
                        "/haaientanden/behandelkamers/{id}",
                        "/haaientanden/behandelingen/{id}",
                        "/haaientanden/afspraken/tandartsen/{surnameDentist}",
                        "/haaientanden/behandelingen-bij-afspraken/afspraken/{id}",
                        "/haaientanden/behandelingen-bij-afspraken/behandelingen/{id}"
                        ).hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.GET, "/haaientanden/behandelingen").hasAnyAuthority("ADMIN", "TANDARTS", "PATIENT")
                .antMatchers(HttpMethod.GET, "/haaientanden/afspraken/{id}").hasAnyAuthority("ADMIN", "PATIENT")
                .antMatchers(HttpMethod.POST, "/haaientanden/behandelingen-bij-afspraken/{appointmentId}/{treatmentId}").hasAnyAuthority("ADMIN", "TANDARTS")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}

