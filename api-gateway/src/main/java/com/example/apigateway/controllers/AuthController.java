package com.example.apigateway.controllers;

import com.example.apigateway.config.security.jwt.Jwt;
import com.example.apigateway.dto.CredentialsDto;
import com.example.apigateway.dto.JwtResponse;
import com.example.apigateway.dto.RefreshTokenDto;
import com.example.apigateway.dto.RefreshTokenSave;
import com.example.apigateway.model.User;
import com.example.apigateway.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private static final String TOKEN_NAME = "JWT";
    private static final long expiration = Duration.ofHours(3).toSeconds();

    private final UserService userService;
    private final Jwt jwt;
    @PostMapping("login")
    public ResponseEntity<JwtResponse> authorize(@RequestBody final CredentialsDto credentials, HttpServletResponse response)  {
        if (userService.verifyUser(credentials)) {
            User user = userService.findUserByEmail(credentials.getEmail());
            String accessToken = jwt.generateAccessToken(user);
            String refreshToken = jwt.generateRefreshToken(user.getEmail());
            final Cookie cookie = new Cookie(TOKEN_NAME, accessToken);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            RefreshTokenSave refreshTokenSave = new RefreshTokenSave(user.getId(), refreshToken);
            userService.saveRefreshToken(refreshTokenSave);
            response.addCookie(cookie);
            return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("access")
    public ResponseEntity<JwtResponse> getAccessToken(@RequestBody final RefreshTokenDto dto) {
        if (jwt.validateRefreshToken(dto.getRefreshToken())) {
            String email = jwt.getLoginFromRefreshToken(dto.getRefreshToken());
            User user = userService.findUserByEmail(email);
            if (user.getRefreshToken() != null && user.getRefreshToken().equals(dto.getRefreshToken())) {
                String accessToken = jwt.generateAccessToken(user);
                return ResponseEntity.ok(new JwtResponse(accessToken, null));
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> refreshTokens(@RequestBody final RefreshTokenDto dto) {
        if (jwt.validateRefreshToken(dto.getRefreshToken())) {
            String username = jwt.getLoginFromRefreshToken(dto.getRefreshToken());
            User user = userService.findUserByEmail(username);
            if (user.getRefreshToken() != null && user.getRefreshToken().equals(dto.getRefreshToken())) {
                String accessToken = jwt.generateAccessToken(user);
                String refreshToken = jwt.generateRefreshToken(username);
                user.setRefreshToken(refreshToken);
                userService.update(user);
                return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
            }
        }
        return ResponseEntity.noContent().build();
    }
}
