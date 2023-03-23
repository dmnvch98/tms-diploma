package com.example.apigateway.controllers;

import com.example.apigateway.config.security.jwt.Jwt;
import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.JwtResponse;
import com.example.apigateway.dto.RefreshTokenDto;
import com.example.apigateway.dto.UserRefreshToken;
import com.example.apigateway.model.User;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Value("${jwt.access_token_name}")
    private String accessTokenName;
    @Value("${jwt.refresh_token_name}")
    private String refreshTokenName;
    private static final long EXPIRATION = Duration.ofHours(3).toSeconds();
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
                    cookie.setMaxAge((int) EXPIRATION);
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
    public ResponseEntity<JwtResponse> refreshTokens(HttpServletRequest request,
                                                     HttpServletResponse response) {
        log.info("Updating tokens");
        Cookie requestCookie = WebUtils.getCookie(request, "JWT_REFRESH");
        String refreshToken = null;
        if (requestCookie != null) {
             refreshToken= requestCookie.getValue();
        }
        if (jwt.validateRefreshToken(refreshToken)) {
            log.info("Refresh token is valid");
            String email = jwt.getLoginFromRefreshToken(refreshToken);
            User user = userService.findUserByEmail(email);
            if (user.getRefreshToken() != null && user.getRefreshToken().equals(refreshToken)) {
                log.info("Refresh tokens are the same");
                String newAccessToken = jwt.generateAccessToken(user);
                String newRefreshToken = jwt.generateRefreshToken(email);
                final Cookie accessTokenCookie = new Cookie(accessTokenName, newAccessToken);
                final Cookie refreshTokenCookie = new Cookie(refreshTokenName, newRefreshToken);
                Arrays.asList(refreshTokenCookie, accessTokenCookie)
                    .forEach(cookie -> {
                        cookie.setPath("/");
                        cookie.setHttpOnly(true);
                        cookie.setDomain(cookieDomain);
                        cookie.setMaxAge((int) EXPIRATION);
                    });
                UserRefreshToken userRefreshToken = UserRefreshToken.builder()
                    .userId(user.getId())
                    .token(newRefreshToken)
                    .build();
                userService.saveRefreshToken(userRefreshToken);
                response.addCookie(accessTokenCookie);
                response.addCookie(refreshTokenCookie);
                log.info("Tokens are updated");
                return ResponseEntity.ok(new JwtResponse(newAccessToken, newRefreshToken));
            } else {
                log.error("Refresh tokens are not the same");
            }
        } else {
            log.error("Refresh token is not valid");
        }
        return ResponseEntity.notFound().build();
    }
}
