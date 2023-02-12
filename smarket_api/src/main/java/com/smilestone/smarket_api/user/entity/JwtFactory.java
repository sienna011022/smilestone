package com.smilestone.smarket_api.user.entity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.jsonwebtoken.Jwts.claims;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@RequiredArgsConstructor
@Component
public class JwtFactory {
    private static final String ROLES = "roles";
    private static final long ACCESS_TOKEN_VALID_TIME = 30 * 60;
    private static final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 30;
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String ACCESS_TOKEN = "access_token";
    @Value("${jwt.secret.key}")
    private String salt;
    private Key secretKey;

    @PostConstruct
    protected void init() {
        secretKey = hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, String> generateTokens(String userId,List<String> roles) {
        Claims claims = makeClaims(userId,roles);
        Map<String, String> tokens = new HashMap();
        Date now = new Date();
        tokens.put(ACCESS_TOKEN, createAccessToken(claims, now));
        tokens.put(REFRESH_TOKEN, createRefreshToken(claims, now));

        return tokens;
    }

    private String createAccessToken(Claims claims, Date now) {
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    private String createRefreshToken(Claims claims, Date now) {
        return Jwts.builder()
            .setHeaderParams(Jwts.header())
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    private Claims makeClaims(String memberId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(memberId);
        claims.put(ROLES, roles);
        return claims;
    }
}
