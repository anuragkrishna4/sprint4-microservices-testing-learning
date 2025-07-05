package com.nisum.book.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "demo-secret-demo-secret-demo-secret-demo-1234";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXP = 1000 * 60 * 60;

    public static String generate(String user) {
        return Jwts.builder()
                .subject(user)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXP))
                .signWith(KEY, Jwts.SIG.HS256)
                .compact();
    }

    public static Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token);
    }
}
