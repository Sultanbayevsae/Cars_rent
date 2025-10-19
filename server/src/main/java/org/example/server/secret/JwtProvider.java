package org.example.server.secret;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.example.server.entity.Role;
import org.example.server.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.secret.expired-date}")
    private long expirationMillis;

    private Key getSigningKey() {
        try {
            byte[] keyBytes;
            if (secretKey.length() < 64) {
                keyBytes = secretKey.getBytes();
            } else {
                keyBytes = Decoders.BASE64.decode(secretKey);
            }
            return Keys.hmacShaKeyFor(keyBytes);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid JWT secret key", e);
        }
    }

    public String generateToken(@NonNull User user) {
        List<String> roleNames = user.getRoles().stream()
                .map(Role::getRoleName)
                .toList();

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuer("carshop.uz")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setId(UUID.randomUUID().toString())
                .claim("roles", roleNames)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(@NonNull String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("❌ Token expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.warn("❌ Unsupported token: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.warn("❌ Malformed token: {}", e.getMessage());
        } catch (SignatureException e) {
            log.warn("❌ Invalid signature: {}", e.getMessage());
        } catch (Exception e) {
            log.warn("❌ Token validation failed: {}", e.getMessage());
        }
        return false;
    }

    public String extractUserId(@NonNull String token) {
        try {
            return getClaims(token).getSubject();
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token", e);
        }
    }

    public List<String> extractRoles(@NonNull String token) {
        Claims claims = getClaims(token);
        Object rolesObj = claims.get("roles");
        if (rolesObj instanceof List<?> roles) {
            return roles.stream().map(Object::toString).toList();
        }
        return List.of();
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
