package com.example.apigateway.config.security;

import com.example.apigateway.config.filters.ExceptionHandlerFilter;
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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    @Value("${csrf.xsrf_cookie_name}")
    public String xsrfCookieName;
    @Value("${csrf.xsrf_header_name}")
    public String xsrfHeaderName;
    @Value("${csrf.cookie_domain}")
    public String cookieDomain;
    @Value("${security.allowed-origin}")
    public String allowedOrigin;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable()
//            .csrf().ignoringAntMatchers("/api/v1/auth/login", "/logout")
//            .ignoringRequestMatchers(new AntPathRequestMatcher("/api/v1/users", "POST"))
//            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests(requests -> requests
                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/languages/**").permitAll()
                .antMatchers("/api/v1/countries/**").permitAll()
                .antMatchers("/api/v1/levels/**").permitAll()
                .antMatchers("/api/v1/users/exists/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/tutors/**").hasAnyRole("Student")
                .antMatchers(HttpMethod.DELETE, "/api/v1/students/**").hasAnyRole("Tutor")
                .anyRequest().authenticated()
            )
            .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
     //       .csrf().csrfTokenRepository(csrfTokenRepository())
         //   .and()
            .logout(logout -> logout
                .permitAll()
                .logoutUrl("/logout")
                .addLogoutHandler(new SecurityContextLogoutHandler())
                .logoutSuccessHandler((request, response, authentication) -> {
                    for (Cookie cookie : request.getCookies()) {
                        String cookieName = cookie.getName();
                        Cookie cookieToDelete = new Cookie(cookieName, null);
                        cookieToDelete.setMaxAge(0);
                        response.addCookie(cookieToDelete);
                    }
                    response.setStatus(HttpServletResponse.SC_OK);
                })
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private CookieCsrfTokenRepository csrfTokenRepository() {
        final CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        repository.setCookieName(xsrfCookieName);
        repository.setHeaderName(xsrfHeaderName);
        repository.setCookieDomain(cookieDomain);
        return repository;
    }

}
