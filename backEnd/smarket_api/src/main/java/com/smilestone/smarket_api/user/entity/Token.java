package com.smilestone.smarket_api.user.entity;


import com.fasterxml.uuid.Generators;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    private static final String UUID_SPLIT_REGEX = "-";
    @Column(name = "refresh_token")
    private String token;
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    public Token(String token) {
        this.token = token;
        this.uuid = createUUID();
    }

    public static Token of(String refreshToken) {
        return new Token(refreshToken);
    }

    public UUID uuid() {
        return uuid;
    }

    public String token() {
        return token;
    }

    private UUID createUUID() {
        UUID uuid = Generators.timeBasedGenerator().generate();
        String[] uuidArr = uuid.toString().split(UUID_SPLIT_REGEX);
        String uuidStr = uuidArr[2] + uuidArr[1] + uuidArr[0] + uuidArr[3] + uuidArr[4];
        StringBuffer sb = new StringBuffer(uuidStr);
        sb.insert(8, UUID_SPLIT_REGEX);
        sb.insert(13, UUID_SPLIT_REGEX);
        sb.insert(18, UUID_SPLIT_REGEX);
        sb.insert(23, UUID_SPLIT_REGEX);
        return UUID.fromString(sb.toString());
    }

}
