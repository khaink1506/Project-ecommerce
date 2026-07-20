package com.project.ecommerce.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.ecommerce.constants.JwtConstants;
import com.project.ecommerce.enums.ErrorCode;
import com.project.ecommerce.enums.TokenType;
import com.project.ecommerce.exception.InvalidJwtException;
import com.project.ecommerce.service.JwtVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Service
public class JwtVerifierImpl implements JwtVerifier {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Override
    public Jwt verifyToken(String token, TokenType expectedType) {
        SignedJWT signedJWT = parseSignedJwt(token);
        verifyAlgorithm(signedJWT);
        verifySignature(signedJWT);
        JWTClaimsSet claims = extractClaims(signedJWT);
        verifyIssuedAt(claims);
        verifyExpiration(claims);
        verifyIssuer(claims);
        verifyTokenType(claims, expectedType);
        return convertToSpringJwt(signedJWT, claims);
    }

    private Jwt convertToSpringJwt(SignedJWT signedJWT, JWTClaimsSet claims) {
        return new Jwt(signedJWT.serialize(),
                claims.getIssueTime().toInstant(),
                claims.getExpirationTime().toInstant(),
                Map.of("alg", signedJWT.getHeader().getAlgorithm().getName()),
                claims.getClaims());
    }

    // verify JWT

    private void verifyIssuedAt(JWTClaimsSet jwtClaimsSet){
        Date issuedAt = jwtClaimsSet.getIssueTime();
        if(issuedAt == null || issuedAt.after(new Date())){
            throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
        }
    }
    private SignedJWT parseSignedJwt(String token) {
        try {
            return SignedJWT.parse(token);
        } catch (ParseException e) {
            throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
        }
    }
    private void verifySignature(SignedJWT signedJWT) {
        try {
            JWSVerifier verifier = new MACVerifier(secretKey.getBytes(StandardCharsets.UTF_8));
            if (!signedJWT.verify(verifier)) {
                throw new InvalidJwtException(ErrorCode.INVALID_TOKEN_SIGNATURE);
            }
        } catch (JOSEException e) {
            throw new IllegalStateException("Cannot verify JWT signature", e);
        }
    }
    private JWTClaimsSet extractClaims(SignedJWT signedJWT) {
        try {
            return signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
        }
    }
    private void verifyExpiration(JWTClaimsSet claims) {
        Date expiration = claims.getExpirationTime();
        if (expiration == null || !expiration.after(new Date())) {
            throw new InvalidJwtException(ErrorCode.TOKEN_EXPIRED);
        }
    }
    private void verifyIssuer(JWTClaimsSet claims) {
        if (!issuer.equals(claims.getIssuer())) {
            throw new InvalidJwtException(ErrorCode.INVALID_ISSUER);
        }
    }
    private void verifyTokenType(JWTClaimsSet claims, TokenType expectedType) {
        try {
            String actualType = claims.getStringClaim(JwtConstants.CLAIM_TYPE);
            if(actualType == null || actualType.isBlank()){
                throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
            }
            if (!expectedType.name().equalsIgnoreCase(actualType)) {
                throw new InvalidJwtException(ErrorCode.INVALID_TOKEN_TYPE);
            }
        } catch (ParseException e) {
            throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
        }
    }
    private void verifyAlgorithm(SignedJWT signedJWT) {
        if (!JWSAlgorithm.HS512.equals(signedJWT.getHeader().getAlgorithm())) {
            throw new InvalidJwtException(ErrorCode.INVALID_ALGORITHM);
        }
    }
}
