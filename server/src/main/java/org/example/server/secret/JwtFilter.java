package org.example.server.secret;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.example.server.entity.User;
import org.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider provider;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        System.out.println(request.getServerName());

        if (token == null || !provider.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Noto'g'ri yoki muddati o'tgan token");
            return;
        }

        User user = getUserFromToken(token);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Foydalanuvchi topilmadi");
            return;
        }

        if (!user.isEnabled()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Foydalanuvchi hisobi aktiv emas");
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private User getUserFromToken(String token) {
        String userIdFromToken = provider.extractUserId(token);
        return userRepository.findById(UUID.fromString(userIdFromToken)).orElse(null);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header != null ? header.substring(7) : null;
    }
}
