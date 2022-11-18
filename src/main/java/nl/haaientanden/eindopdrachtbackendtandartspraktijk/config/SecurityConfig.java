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
    public AuthenticationManager authManager(HttpSecurity http, UserDetailsService udService, PasswordEncoder encoder) throws Exception {
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

                .antMatchers(HttpMethod.POST, "/haaientanden/roles").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/haaientanden/roles").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/haaientanden/roles/{rolename}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/haaientanden/users").permitAll()
                .antMatchers(HttpMethod.POST,"/haaientanden/auth").permitAll()

                .antMatchers(HttpMethod.POST,"/haaientanden/behandelingen").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/haaientanden/behandelingen").hasAnyAuthority("ADMIN", "TANDARTS", "PATIENT")
                .antMatchers(HttpMethod.GET,        "/haaientanden/behandelingen/{id}").hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.PUT,"/haaientanden/behandelingen/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/haaientanden/behandelingen/{id}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/haaientanden/patienten").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/haaientanden/patienten").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/haaientanden/patienten/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/haaientanden/patienten/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/haaientanden/patienten/{id}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/haaientanden/behandelkamers").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/haaientanden/behandelkamers").hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.GET,        "/haaientanden/behandelkamers/{id}").hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.PUT,"/haaientanden/behandelkamers/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/haaientanden/behandelkamers/{id}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/haaientanden/afspraken").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/haaientanden/afspraken").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/haaientanden/afspraken/{id}").hasAnyAuthority("ADMIN", "PATIENT")
                .antMatchers(HttpMethod.GET,        "/haaientanden/afspraken/tandartsen/{surnameDentist}").hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.PUT,"/haaientanden/afspraken/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/haaientanden/afspraken/{appointmentId}/patienten/{patientId}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/haaientanden/afspraken/{id}/behandelkamers/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/haaientanden/afspraken/{id}").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST,"/haaientanden/facturen").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/haaientanden/facturen").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,"/haaientanden/facturen/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT,"/haaientanden/facturen/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/haaientanden/facturen/{id}").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET,        "/haaientanden/behandelingen-bij-afspraken/afspraken/{id}").hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.GET,        "/haaientanden/behandelingen-bij-afspraken/behandelingen/{id}").hasAnyAuthority("ADMIN", "TANDARTS")
                .antMatchers(HttpMethod.POST, "/haaientanden/behandelingen-bij-afspraken/{appointmentId}/{treatmentId}").hasAnyAuthority("ADMIN", "TANDARTS")
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}

