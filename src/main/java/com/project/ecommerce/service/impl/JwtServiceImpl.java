package com.project.ecommerce.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.project.ecommerce.entity.AdminEntity;
import com.project.ecommerce.entity.RoleEntity;
import com.project.ecommerce.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Override
    public String generateAccessToken(AdminEntity adminEntity) {

        if (adminEntity == null) {
            throw new IllegalArgumentException("AdminEntity must not be null");
        }
        Date issueTime = new Date();
        Date expiredTime = Date.from(issueTime.toInstant().plus(accessTokenExpiration, ChronoUnit.MINUTES));

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(adminEntity.getUsername())
                .issuer(issuer)
                .issueTime(issueTime)
                .expirationTime(expiredTime)
                .jwtID(UUID.randomUUID().toString())
                .claim("type", "access")
                .claim(
                        "scope",
                        adminEntity.getRoles()
                                .stream()
                                .map(RoleEntity::getName)
                                .collect(Collectors.joining(" "))
                )
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException("Cannot generate access token", e);
        }
        return jwsObject.serialize();
    }

    @Override
    public String generateRefreshToken(AdminEntity adminEntity) {

        if (adminEntity == null) {
            throw new IllegalArgumentException("AdminEntity must not be null");
        }

        Date issueTime = new Date();
        Date expiredTime = Date.from(
                issueTime.toInstant().plus(refreshTokenExpiration, ChronoUnit.DAYS));


        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(adminEntity.getUsername())
                .issuer(issuer)
                .issueTime(issueTime)
                .expirationTime(expiredTime)
                .jwtID(UUID.randomUUID().toString())
                .claim("type", "refresh")
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException("Cannot generate refresh token", e);
        }
        return jwsObject.serialize();
    }
}
