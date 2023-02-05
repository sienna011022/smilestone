package com.smilestone.smarket_api.user.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String JWT_HEADER_SPACE = " ";
    private static final int JWT_POSITION = 1;
    private final JwtFactory jwtProvider;

    public JwtAuthenticationFilter(JwtFactory jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    /**
     * token을 인터셉트하여 token이 인증된 사용자라면 ,servlet에 도달하기전에 인증을 처리할 수 있다
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtProvider.getTokenFrom(request);

        if (token != null && jwtProvider.validateToken(token)) {
            token = token.split(JWT_HEADER_SPACE)[JWT_POSITION]
                .trim();

            Authentication auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext()
                .setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
