package com.smilestone.gateway.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.Map;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Component
public class RoleAuthGatewayFilter extends AbstractGatewayFilterFactory<RoleAuthGatewayFilter.Config> {
    @Value("${jwt.secret.key}")
    private String salt;
    private Key secretKey;

    public RoleAuthGatewayFilter() {
        super(Config.class);
    }

    public static class Config {
        // Put configuration properties here
    }

    @PostConstruct
    protected void init() {
        secretKey = hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            var request = exchange.getRequest();
            request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer ", "");

            if (!isRoleValid(jwt)) {
                throw new Error();
            }
            return chain.filter(exchange);
        });

    }

    private boolean isRoleValid(String jwt) {
        Jws<Claims> claims = Jwts
            .parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(jwt);

        List<String> roleNames = (List<String>) claims.getBody().get("roles");
        return roleNames
            .stream()
            .filter(role -> role.equals("ROLE_USER"))
            .findAny()
            .isPresent();
    }
}

