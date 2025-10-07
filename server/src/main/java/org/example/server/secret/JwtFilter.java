package org.example.server.secret;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.server.entity.User;
import org.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider provider;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null && provider.validateToken(token)) {
            User user = getUserFromToken(token);
            if (user != null) {
                if (user.isAccountNonExpired()) {
                    if (user.isAccountNonLocked()) {
                        if (user.isCredentialsNonExpired()) {
                            if (user.isEnabled()) {
                                UsernamePasswordAuthenticationToken authenticationToken =
                                        new UsernamePasswordAuthenticationToken(
                                                user,
                                                null,
                                                user.getAuthorities()
                                        );
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                            } else {
                                System.err.println("User Disabled");
                            }
                        } else {
                            System.err.println("User Credentials Expired");
                        }
                    } else {
                        System.err.println("User Locked");
                    }
                } else {
                    System.err.println("User Expired");
                }
            }
            else {
//                response.sendRedirect("/login");
//                response.sendRedirect("/login");
//                response.setStatus(401);
            }
        }

        filterChain.doFilter(request, response);
    }

    //TODO===============================
    // Token dan USER ni olish
    private User getUserFromToken(String token) {
        boolean validateToken = provider.validateToken(token);
        if (validateToken){
            String usernameFromToken = provider.getUsernameFromToken(token);
            return userRepository.findUserByEmail(usernameFromToken/*UUID.fromString(userIdFromToken)*/).get();
        }
        return null;
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header!=null?header.substring(7):null;
    }
}
