package com.example.apigateway.config.filters;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(@NotNull HttpServletRequest request,
                                 @NotNull HttpServletResponse response,
                                 @NotNull FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException | ServletException e) {
            switch (e.getClass().getSimpleName()) {
                case "ExpiredJwtException" -> {
                    response.setStatus(401);
                    log.info("token expired");
                }
                case "UnsupportedJwtException" -> {
                    response.setStatus(403);
                    log.info("Unsupported jwt");
                }
                case "MalformedJwtException" -> {
                    response.setStatus(403);
                    log.info("Malformed jwt");
                }
                case "SignatureException" -> {
                    response.setStatus(403);
                    log.info("Invalid signature");
                }
                case "Exception" -> {
                    response.setStatus(403);
                    log.info("Invalid token");
                }

            }
        }
    }
}