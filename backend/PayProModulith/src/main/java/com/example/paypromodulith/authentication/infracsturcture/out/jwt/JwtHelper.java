package com.example.paypromodulith.authentication.infracsturcture.out.jwt;

import com.example.paypromodulith.authentication.domain.model.TokenDetails;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtHelper {

    private static final String SECRET_KEY = "19da4404dc6c4461799ddabfbf95c1a5eadd06b13b06fc0c3d37aa4f7dcdac4bcfd4e25eba7444717b0b0db64760e6311045dea1f572e4da4e4f29dc2242235c06b90253f72011a0c6d70f241104b50c691b0c085a622cb03fc38c26d711f050f72b73a32e86e9b91975a10ae1f5229ff5174208da44c44f4c957d7baaf9ad6b066ea2a162e622e592ba72efc431e3e4ebaad8b670697101a7cb892694b0cae723f3f6f9dd75dbd6a5215c8170a7a84a73bebc0effcfc27d33b721bcc31afcb1d504292cab02b87d8afff0aa6852d463f3772113ef076f97faa30c53da81235e48c2d78fb815c5f7394e10b71f91ab6bb6c0cc6d816084918c8633c55e4bdc7c";

    private static final int MINUTES = 1440;

    private static Key key() throws NoSuchAlgorithmException {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateToken(TokenDetails tokenDetails) throws NoSuchAlgorithmException {
        var now = Instant.now();
        var key = key();
        System.out.println(key());
        return Jwts.builder()
                .id(tokenDetails.getId().toString())
                .subject(tokenDetails.getFirstName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(key())
                .compact();
    }

    public static String extractId(String token) {
        return getTokenBody(token).getId();
    }

    public static String extractUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    private static Claims getTokenBody(String token) {
        try {
            return Jwts
                    .parser()

                    .setSigningKey(key())

                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new AccessDeniedException("Access denied: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isTokenExpired(String token) {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }

    public static Boolean validateToken(String token, UserDetails user) {
        final String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }



}
