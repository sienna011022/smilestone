package com.smilestone.gateway.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

public class JwtParser {
    private static final String TYPE_OF_TOKEN = "Bearer ";
    private static final String BLANK_SPACE = "";
    private static final int INDEX_OF_AUTHORIZATION = 0;

    public static String parseJwt(ServerHttpRequest request) {
        request.getHeaders()
            .containsKey(HttpHeaders.AUTHORIZATION);

        return request.getHeaders()
            .get(HttpHeaders.AUTHORIZATION)
            .get(INDEX_OF_AUTHORIZATION)
            .replace(TYPE_OF_TOKEN, BLANK_SPACE);
    }
}
