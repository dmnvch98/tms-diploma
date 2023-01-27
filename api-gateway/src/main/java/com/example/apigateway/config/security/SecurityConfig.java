package com.example.apigateway.config.security;

import com.example.apigateway.config.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Value("${csrf.xsrf_cookie_name}")
    public String XSRF_COOKIE_NAME;
    @Value("${csrf.xsrf_header_name}")
    public String XSRF_HEADER_NAME;
    @Value("${csrf.cookie_domain}")
    public String COOKIE_DOMAIN;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().ignoringAntMatchers("/nocsrf","/api/v1/auth/login")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(requests -> {
                            requests
                                    .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                    .antMatchers("/api/v1/auth/**").permitAll()
                                    .antMatchers("/api/v1/languages/**").permitAll()
                                    .antMatchers("/api/v1/countries/**").permitAll()
                                    .antMatchers("/api/v1/levels/**").permitAll()
                                    .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                                    .antMatchers(HttpMethod.DELETE, "/api/v1/tutors/**").hasAnyRole("Student")
                                    .antMatchers(HttpMethod.DELETE, "/api/v1/students/**").hasAnyRole("Tutor")
                                    .antMatchers("/api/v1/users/tutors/**").hasAnyRole("Student", "Tutor")
                                    .anyRequest().authenticated();

                        }
                )
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                .logout(LogoutConfigurer::permitAll)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private CookieCsrfTokenRepository csrfTokenRepository() {
        final CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setSecure(true);
        repository.setCookieName(XSRF_COOKIE_NAME);
        repository.setHeaderName(XSRF_HEADER_NAME);
        repository.setCookieDomain(COOKIE_DOMAIN);
        return repository;
    }

}
