package com.Service.Students.Security;

import com.Service.Students.Client.AuthClient;
import com.Service.Students.Dto.UserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthFilter extends OncePerRequestFilter {
    private static final String SECRET_KEY = "y4t+5XSkBM6i7ElmMe+M1hPcx7PwM8jmr8OfcXGekuY=";
    private final AuthClient authClient;

    public JwtAuthFilter(AuthClient authClient) {
        this.authClient = authClient;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 🔍 Bypass Swagger and API documentation endpoints
        if (requestURI.startsWith("/swagger-ui") ||
                requestURI.startsWith("/v3/api-docs") ||
                requestURI.startsWith("/swagger-resources") ||
                requestURI.startsWith("/webjars/")) {

            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            System.out.println("❌ No valid Authorization header found.");
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7); // ✅ Remove "Bearer " prefix
        System.out.println("✅ Extracted Token: " + token);

        try {
            // 🔍 Step 1: Decode token claims locally
            Claims claims = getClaims(token);
            System.out.println("✅ Decoded Claims: " + claims);

            // Extract roles from claims
            Object rolesObject = claims.get("roles");
            if (rolesObject == null || !(rolesObject instanceof List)) {
                System.out.println("❌ No roles found in token.");
                filterChain.doFilter(request, response);
                return;
            }

            List<String> roles = (List<String>) rolesObject;
            System.out.println("✅ Roles from token: " + roles);

            // 🔍 Step 2: Validate token with Authentication microservice
            ResponseEntity<UserResponse> responseEntity = authClient.validateToken("Bearer " + token);
            System.out.println("✅ Response from AuthClient: " + responseEntity);

            if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
                System.out.println("❌ Token validation failed in Auth Service");
                filterChain.doFilter(request, response);
                return;
            }

            UserResponse user = responseEntity.getBody();
            System.out.println("✅ User from AuthClient: " + user);

            // 🔍 Step 3: Extract roles and authenticate user
            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role))
                    .collect(Collectors.toList());

            System.out.println("✅ Authorities assigned: " + authorities);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("✅ Authenticated User: " + SecurityContextHolder.getContext().getAuthentication());

        } catch (Exception e) {
            System.out.println("❌ Invalid Token: " + e.getMessage());
        }

        // Ensure the request continues in the filter chain
        filterChain.doFilter(request, response);
    }
}
