package com.smilestone.smarket_api.user.security;

import com.smilestone.smarket_api.user.entity.Authority;
import com.smilestone.smarket_api.user.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

import static io.jsonwebtoken.Jwts.claims;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@RequiredArgsConstructor
@Component
public class JwtFactory {
    private static final String ROLES = "roles";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TYPE_OF_AUTHORIZATION = "BEARER ";
    private final long EXPIRATION_TIME = 1000L * 60 * 60;

    @Value("${jwt.secret.key}")
    private String salt;
    private Key secretKey;

    private final CustomUserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String user, List<Authority> roles) {
        Claims claims = claims().setSubject(user);
        claims.put(ROLES, roles);
        Date now = new Date();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + EXPIRATION_TIME))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getAccount(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String getTokenFrom(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public boolean validateToken(String token) {
        try {
            if (!token.substring(0,TYPE_OF_AUTHORIZATION.length()).equalsIgnoreCase(TYPE_OF_AUTHORIZATION)) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
