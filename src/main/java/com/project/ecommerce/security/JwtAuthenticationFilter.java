package com.project.ecommerce.security;

import com.project.ecommerce.enums.ErrorCode;
import com.project.ecommerce.enums.TokenType;
import com.project.ecommerce.exception.InvalidJwtException;
import com.project.ecommerce.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private static final WebAuthenticationDetailsSource DETAILS_SOURCE = new WebAuthenticationDetailsSource();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws IOException, ServletException {

        String token = resolveToken(request);
        if (token != null) {
            authenticate(token, request);
        }
        filterChain.doFilter(request, response);
    }

    // Lấy JWT từ rquest
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)){
            return null;
        }
        String token = bearerToken.substring(BEARER_PREFIX.length()) .trim();
        return token.isEmpty() ? null : token;
    }

    // Tạo Authentication
    private UsernamePasswordAuthenticationToken createAuthentication(UserDetails userDetails, HttpServletRequest request){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authentication.setDetails(DETAILS_SOURCE.buildDetails(request));
        return authentication;
    }

    private void authenticate(String token, HttpServletRequest request){
        SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication() != null){
            return;
        }
        Jwt jwt = jwtService.verifyToken(token, TokenType.ACCESS);
        String username = jwt.getSubject();
        if(username == null || username.isBlank()){
            throw new InvalidJwtException(ErrorCode.INVALID_TOKEN);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = createAuthentication(userDetails, request);
        context.setAuthentication(authentication);
    }
}