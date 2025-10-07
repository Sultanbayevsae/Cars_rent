package org.example.server.secret;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import org.example.server.entity.User;
import org.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.secret.expired-date}")
    private Long EXPIRED_DATE;

    private final UserRepository userRepository;

    public JwtProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(@NonNull String username) {
        System.out.println("Generating token for user: " + username);
        Optional<User> optionalUser = userRepository.findUserByEmail(username);

        System.out.println("User found: " + optionalUser.isPresent());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("User ID: " + user.getId());
            return Jwts.builder().setSubject(username).setIssuer("carshop.uz")
                    .setIssuedAt(new Date())
                    .claim("roles", user.getRoles())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_DATE))
                    .signWith(signKey(), SignatureAlgorithm.HS512)
                    .compact();
        }
        throw new BadCredentialsException("User not found");
    }

    public boolean validateToken(@org.springframework.lang.NonNull String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(signKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("Token muddati o'tgan");
            return false;
        } catch (MalformedJwtException e) {
            System.err.println("Token buzilgan");
            return false;
        } catch (SignatureException e) {
            System.err.println("Token secret key xato");
            return false;
        } catch (UnsupportedJwtException e) {
            System.err.println("Token format xato");
            return false;
        } catch (Exception e) {
            System.out.println("Token xato");
            return false;
        }

    }

    public Key signKey() {
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public String getUsernameFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
