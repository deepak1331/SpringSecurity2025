package com.learn.SpringSecurity2025.service.impl;

import com.learn.SpringSecurity2025.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@Log4j2
public class JwtServiceImpl implements JwtService {


    private String key = "";

    public JwtServiceImpl() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HMACSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        //  return Jwts.builder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        long currentTime = System.currentTimeMillis();

        return Jwts.builder().claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(currentTime))
                .expiration(new Date(currentTime + (2 * 60 * 60 * 1000))) // 2 hours from issue time
                .and()
                //.signWith(geStatictKey())
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    /*@Override
    public String generateToken(String username) {
        //Hard coded JWT value
         return "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJMZWFybmluZ1NwcmluZ1NlY3VyaXR5I" +
                 "iwiVXNlcm5hbWUiOiJEZWVwYWsgWWFkYXYiLCJleHAiOjE3NTQ5OTgyNTIsImlhdCI6MTc1NDkxMTg1Mn0.e9f" +
                 "xuqWe2-FD147N0Tv_2cpH0CxeatGzSol7rztKmK0";
    }

    //This is a way to create a static key (non-recommeded)
    private Key geStatictKey() {
        String key = "abcd012345_xyz";
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }*/
}
