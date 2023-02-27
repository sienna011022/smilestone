package com.smilestone.gateway.filters;

import com.smilestone.gateway.exception.ExpiredTokenException;
import com.smilestone.gateway.exception.NotFoundTokenException;
import com.smilestone.gateway.exception.UnAuthorizedException;
import com.smilestone.gateway.util.JwtParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {


    @Value("${jwt.secret.key}")
    private String salt;
    private Key secretKey;

    public static class Config {

    }

    @PostConstruct
    protected void init() {
        secretKey = hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new NotFoundTokenException();
            }
            String jwt = JwtParser.parseJwt(request);
            if (!isJwtValid(jwt)) {
                throw new ExpiredTokenException();
            }
            return chain.filter(exchange);
        });
    }

    private boolean isJwtValid(String jwt) {
        Jws<Claims> claims = Jwts
            .parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(jwt);

        return !claims.getBody()
            .getExpiration()
            .before(new Date());
    }
}
