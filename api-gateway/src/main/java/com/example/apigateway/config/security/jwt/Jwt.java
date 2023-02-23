package com.example.apigateway.config.security.jwt;

import com.example.apigateway.model.User;
import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
@Getter
public class Jwt {

    @Value("${security.secret}")
    private String jwtSecret;

    @Value("${security.refresh-secret}")
    private String jwtRefreshSecret;

    public String generateAccessToken(User user) {
        LocalDateTime now = LocalDateTime.now();
        Instant accessExpirationInstant = now.plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(accessExpirationInstant);
        return Jwts.builder()
            .setSubject(user.getEmail())
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .claim("role", user.getRoles())
            .claim("userId", user.getId())
            .compact();
    }

    public String generateRefreshToken(String login) {
        LocalDateTime now = LocalDateTime.now();
        Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(refreshExpirationInstant);
        return Jwts.builder()
            .setSubject(login)
            .setExpiration(date)
            .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret)
            .compact();
    }

    private boolean validateToken(String token, String secret) {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, jwtSecret);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    private String getLoginFromToken(String token, String secret) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String getLoginFromAccessToken(String token) {
        return getLoginFromToken(token, jwtSecret);
    }

    public String getLoginFromRefreshToken(String token) {
        return getLoginFromToken(token, jwtRefreshSecret);
    }

}
