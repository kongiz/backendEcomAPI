package com.backend.ecom.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String jwtSecret = "my_super_secret_jwt_key_that_is_at_least_64_characters_long_and_secure";
    private final SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    // 15 minutes access token
    private final long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7;
    // 7 days refresh token
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7;

    // Generate Access Token
    public String generateAccessToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(key)
                .compact();
    }

    // Generate Refresh Token
    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(key)
                .compact();
    }
 // Default token generator (uses access token)
    public String generateToken(String email) {
        return generateAccessToken(email);
    }


    // Extract username/email
    public String extractEmail(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractEmail(token);
        return username != null &&
               username.equals(userDetails.getUsername()) &&
               !isTokenExpired(token);
    }	

//    // Validate token
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractEmail(token);
//        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
//    }
}
