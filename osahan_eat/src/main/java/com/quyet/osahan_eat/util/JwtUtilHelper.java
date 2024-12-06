package com.quyet.osahan_eat.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtilHelper {

    @Value("${jwt.privateKey}")
    private String privateKey;

    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(privateKey.getBytes());
        String token = Jwts.builder().setSubject(data).signWith(key).compact();

        return token;
    }

    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(privateKey.getBytes());

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
