package com.smilestone.smarket_api.user.entity;

import com.smilestone.smarket_api.user.common.exception.IllegalRefreshTokenException;
import com.smilestone.smarket_api.user.repository.TokenRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@RequiredArgsConstructor
@Component
public class JwtFactory {
    private static final String ROLES = "roles";
    private static final long ACCESS_TOKEN_VALID_TIME = 30 * 60 * 1000L;
    private static final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 30 ;
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String ACCESS_TOKEN = "access_token";

    private final TokenRepository tokenRepository;
    @Value("${jwt.secret.key}")
    private String salt;
    private Key secretKey;

    @PostConstruct
    protected void init() {
        secretKey = hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, String> generateTokens(String userId, List<String> roles) {
        Claims claims = makeClaims(userId, roles);
        Map<String, String> tokens = new HashMap();
        Date now = new Date();
        tokens.put(ACCESS_TOKEN, createAccessToken(claims, now));
        tokens.put(REFRESH_TOKEN,createRefreshToken(claims,now).toString());
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

    private UUID createRefreshToken(Claims claims, Date now) {
        String refreshToken =  Jwts.builder()
            .setHeaderParams(Jwts.header())
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();

        return saveRefreshToken(refreshToken);
    }

    private Claims makeClaims(String memberId, List<String> roles) {
        Claims claims = Jwts.claims()
            .setSubject(memberId);
        claims.put(ROLES, roles);
        return claims;
    }

    public String validAndUpdateRefreshToken(UUID refreshTokenId) {
        Token targetToken = tokenRepository.findById(refreshTokenId)
            .orElseThrow(() -> new IllegalRefreshTokenException());
        return updateToken(targetToken.token());
    }

    private String updateToken(String refreshToken) {
        Jws<Claims> claims = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(refreshToken);

        if (!isJwtValid(claims)) {
            throw new IllegalRefreshTokenException();
        }

        return createAccessToken(claims.getBody(), new Date());
    }

    private boolean isJwtValid(Jws<Claims> claims) {
        return !claims.getBody()
            .getExpiration()
            .before(new Date());
    }

    private UUID saveRefreshToken(String refreshToken) {
        Token createToken = Token.of(refreshToken);
        Token token = tokenRepository.save(createToken);
        return token.uuid();
    }
}
