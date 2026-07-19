package com.project.ecommerce.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.project.ecommerce.constants.JwtConstants;
import com.project.ecommerce.entity.UserEntity;
import com.project.ecommerce.enums.TokenType;
import com.project.ecommerce.service.JwtGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtGeneratorImpl implements JwtGenerator {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;


    @Override
    public String generateAccessToken(UserEntity user) {
        return generateToken(user, accessTokenExpiration, ChronoUnit.MINUTES, TokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(UserEntity user) {
        return generateToken(user, refreshTokenExpiration, ChronoUnit.DAYS, TokenType.REFRESH);
    }
    private String generateToken(UserEntity user, long expiration, ChronoUnit unit, TokenType tokenType) {
        validateUser(user);
        Instant now = Instant.now();
        JWTClaimsSet claims = buildClaims(user, tokenType, Date.from(now), Date.from(now.plus(expiration, unit)));
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS512), new Payload(claims.toJSONObject()));
        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes(StandardCharsets.UTF_8)));
        } catch (JOSEException e) {
            throw new IllegalStateException("Cannot generate JWT token", e);
        }
        return jwsObject.serialize();
    }

    private JWTClaimsSet buildClaims(UserEntity user, TokenType tokenType, Date issueTime, Date expirationTime) {
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer(issuer)
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .jwtID(UUID.randomUUID().toString())
                .claim(JwtConstants.CLAIM_USER_ID, user.getId())
                .claim(JwtConstants.CLAIM_TYPE, tokenType.name().toLowerCase());
        if (tokenType == TokenType.ACCESS) {
            builder.claim(
                    JwtConstants.CLAIM_SCOPE,
                    user.getRole().getCode()
            );
        }
        return builder.build();

    }

    private void validateUser(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
    }
}
