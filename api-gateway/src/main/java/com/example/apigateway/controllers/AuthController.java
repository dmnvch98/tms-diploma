package com.example.apigateway.controllers;

import com.example.apigateway.config.security.jwt.Jwt;
import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.JwtResponse;
import com.example.apigateway.dto.RefreshTokenDto;
import com.example.apigateway.dto.UserRefreshToken;
import com.example.apigateway.model.User;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.access_token_name}")
    private String accessTokenName;
    @Value("${jwt.refresh_token_name}")
    private String refreshTokenName;
    private static final long expiration = Duration.ofHours(3).toSeconds();

    private final UserService userService;
    private final Jwt jwt;

    @Value("${csrf.cookie_domain}")
    public String cookieDomain;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authorize(@RequestBody final CredentialsDto credentials,
                                                 HttpServletResponse response) {
        if (userService.verifyUser(credentials)) {
            User user = userService.findUserByEmail(credentials.getEmail());
            String accessToken = jwt.generateAccessToken(user);
            String refreshToken = jwt.generateRefreshToken(user.getEmail());
            final Cookie accessTokenCookie = new Cookie(accessTokenName, accessToken);
            final Cookie refreshTokenCookie = new Cookie(refreshTokenName, refreshToken);
            Arrays.asList(refreshTokenCookie, accessTokenCookie)
                .forEach(cookie -> {
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    cookie.setDomain(cookieDomain);
                    cookie.setMaxAge((int) expiration);
                });
            UserRefreshToken userRefreshToken = UserRefreshToken.builder()
                .userId(user.getId())
                .token(refreshToken)
                .build();

            userService.saveRefreshToken(userRefreshToken);
            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);
            return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refreshTokens(@RequestBody final RefreshTokenDto dto,
                                                     HttpServletResponse response) {
        if (jwt.validateRefreshToken(dto.getRefreshToken())) {
            String email = jwt.getLoginFromRefreshToken(dto.getRefreshToken());
            User user = userService.findUserByEmail(email);
            if (user.getRefreshToken() != null && user.getRefreshToken().equals(dto.getRefreshToken())) {
                String accessToken = jwt.generateAccessToken(user);
                String refreshToken = jwt.generateRefreshToken(email);
                final Cookie cookie = new Cookie(accessTokenName, accessToken);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setMaxAge((int) expiration);
                cookie.setDomain(cookieDomain);
                response.addCookie(cookie);
                UserRefreshToken userRefreshToken = UserRefreshToken.builder()
                    .userId(user.getId())
                    .token(refreshToken)
                    .build();
                userService.saveRefreshToken(userRefreshToken);
                return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
            }
        }
        return ResponseEntity.notFound().build();
    }
}
